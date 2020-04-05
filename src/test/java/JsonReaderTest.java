import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonReaderTest {
    @Test
    public void testReader(){
        try {
            String params = "sensor=false&language=ru&mode=driving&origin=59.98055,30.32412&destination=60.017412,30.447128&key=" +
                    DriversSuggestor.KEY,
                    baseUrl = "https://maps.googleapis.com/maps/api/directions/json";
            String url = baseUrl + '?' + params;
            JSONObject response = JsonReader.read(url);
            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
