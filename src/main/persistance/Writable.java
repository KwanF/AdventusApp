package persistance;
import org.json.JSONObject;

// Code referenced from JsonSerializationDemo project
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
