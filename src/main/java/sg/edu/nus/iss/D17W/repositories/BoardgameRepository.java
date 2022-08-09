package sg.edu.nus.iss.D17W.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardgameRepository {

    // Access Redis Data
    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String,String> redisTemplate;

    // Counting number of keys 
    public Integer count() {
        Set<String> keys = redisTemplate.keys("[0-9]*");
        return keys.size();
    }

    // Creating the list of keys (All numbers)
    public List<String> keys() {
        Set<String> keys = redisTemplate.keys("[0-9]*");
        List<String> result = new LinkedList<>(keys);
        return result.stream()
                // Convert to integer to perform mathematical sorting
                .map(v -> Integer.parseInt(v))
                .sorted()
                // Convert back to string to list
                .map(v -> v.toString())
                .toList();
    }

    // Method to retrieve data from redis
    public String get(String id) {
        ValueOperations<String,String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);
    }
}
