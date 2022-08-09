package sg.edu.nus.iss.D17W.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.D17W.models.Boardgame;
import sg.edu.nus.iss.D17W.repositories.BoardgameRepository;

@Service
public class BoardgameService {
    
    // Call the repository
    @Autowired
    private BoardgameRepository bgRepo;

    // Count keys from repo
    public Integer count() {
        return bgRepo.count();
    }

    // Get keys from repo
    public List<String> keys() {
        return bgRepo.keys();
    }

    // Obtain Box which might contain Boardgame
    public Optional<Boardgame> getBoardgameById(String id) {
        String result = bgRepo.get(id);
        if (null == result) {
            return Optional.empty();
        }
        return Optional.of(Boardgame.create(result));
    }

    // Calling method above to obtain Box which might contain Boardgame
    public Optional<Boardgame> getBoardgameById(Integer id) {
        return getBoardgameById(id.toString());
    }

}
