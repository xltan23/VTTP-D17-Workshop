package sg.edu.nus.iss.D17W.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.D17W.services.BoardgameService;

@RestController
@RequestMapping(path = "/boardgames", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardgamesRestController {
    
    // Call the service
    @Autowired
    private BoardgameService bgSvc;

    // localhost:8080/boardgames?offset=__&limit=__
    @GetMapping
    ResponseEntity<String> getBoardgames(
        @RequestParam(name = "offset", defaultValue = "0") Integer offset,
        @RequestParam(name = "limit", defaultValue = "5") Integer limit) {
        // Retrieve keys using service method and cropping the range
        List<String> allKeys = bgSvc.keys().subList(offset, offset + limit);
        // {"/boardgame/1", "/boardgame/2" ...}
        List<String> keyRange = allKeys.stream()
            .map(v -> "/boardgame/%s".formatted(v))
            .toList();
        // Convert list to an array
        JsonArray arr = Json.createArrayBuilder(keyRange).build();
        return ResponseEntity.ok(arr.toString());
    }

    // localhost:8080/boardgames/count => Shows the count 
    @GetMapping(path = "count")
    ResponseEntity<String> getBoardgameCount() {
        // Call service method to count
        Integer count = bgSvc.count();
        // Create Json object storing the count 
        JsonObject payload = Json.createObjectBuilder()
            .add("count", count)
            .build();
        return ResponseEntity.ok(payload.toString());
    }
}
