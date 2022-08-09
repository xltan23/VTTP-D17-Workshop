package sg.edu.nus.iss.D17W.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Boardgame {

    // Setting up members
    private Integer id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    // Generate getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    // Creating Boardgame object with Signature jsonstring
    public static Boardgame create(String jsonStr) {
        // Creating Json object from Json String (Read String -> Create Json Reader -> Create Json Object)
        StringReader strReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(strReader);
        // Call the below method to create Boardgame
        return create(reader.readObject());
    }
    
    // Creating Boardgame object with Signature jsonobject
    public static Boardgame create(JsonObject jo) {
        // Defining boardgame attributes (Use getInt/getString method to retrieve valuje of specific keys in JsonObject)
        Boardgame bg = new Boardgame();
        bg.setId(jo.getInt("id"));
        bg.setName(jo.getString("name"));
        bg.setYear(jo.getInt("year"));
        bg.setRanking(jo.getInt("ranking"));
        bg.setUsersRated(jo.getInt("users_rated"));
        bg.setUrl(jo.getString("url"));
        bg.setImage(jo.getString("image"));
        return bg; 
    }

    // Creating JsonObject using Boardgame attributes
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                // name: How it will be shown as key of Json
                // value: The value given for the key (we take from Boardgame attribute)
                .add("id", id)
                .add("name", name)
                .add("year", year)
                .add("ranking", ranking)
                .add("users_rated", usersRated)
                .add("url", url)
                .add("image", image)
                .build();
    }
}
