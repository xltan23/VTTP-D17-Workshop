package sg.edu.nus.iss.D17W.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.D17W.models.Boardgame;
import sg.edu.nus.iss.D17W.services.BoardgameService;

@RestController
@RequestMapping(path = "/boardgame", produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardgameRestController {
    
    // Call the service 
    @Autowired
    private BoardgameService bgSvc;

    // localhost:8080/boardgame/{gid} => Gets the boardgame of that id
    @GetMapping(value = "{gid}")
    public ResponseEntity<String> getBoardgame(@PathVariable String gid) {
        // Obtain Optional<Boardgame> Box 
        Optional<Boardgame> opt = bgSvc.getBoardgameById(gid);

        // If empty, build a Json object that say ID not found
        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("error", "ID %s not found\n".formatted(gid))
                .build();
            // Convert to JsonString
            String payload = err.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(payload);
        }
        // Retrieve boardgame from the Box
        Boardgame boardgame = opt.get();
        // Convert Json and to String for display
        return ResponseEntity.ok(boardgame.toJson().toString());
    }

}
