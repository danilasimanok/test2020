import com.google.common.collect.Maps;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class DriversSuggestor implements Comparator<String> {

    private static final String ORIGIN = "59.98055,30.32412",
        BASE_URL = "https://maps.googleapis.com/maps/api/directions/json";
    public static final String KEY = "AIzaSyAfeB_N3etwS9nnISWWkVnLruQLWqWCyZQ";

    String destination;
    int durationOfBringingUp;

    private static String encodeParams(Map<String, String> params) {
        LinkedList<String> pairs = new LinkedList<>();
        StringBuffer buffer;
        for (Map.Entry<String, String> entry: params.entrySet()){
            try {
                buffer = new StringBuffer();
                buffer.append(entry.getKey());
                buffer.append("=");
                buffer.append(URLEncoder.encode(entry.getValue(), "utf-8"));
                pairs.add(buffer.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return String.join("&", pairs);
    }

    public DriversSuggestor(String passenger) {
        this.destination = passenger;
        this.durationOfBringingUp = this.getDuration(DriversSuggestor.ORIGIN, this.destination);
    }

    public Collection<String> suggestDrivers(Collection<String> drivers) {
        ArrayList<String> copy = new ArrayList<>(drivers);
        copy.sort(this);
        return copy;
    }

    private int getDuration(String origin, String destination) {
        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("sensor", "false");
            params.put("language", "ru");
            params.put("mode", "driving");
            params.put("origin", origin);
            params.put("destination", destination);
            params.put("key", DriversSuggestor.KEY);
            String url = DriversSuggestor.BASE_URL + '?' +DriversSuggestor.encodeParams(params);
            JSONObject response = JsonReader.read(url);
            JSONObject location = response.getJSONArray("routes").getJSONObject(0);
            location = location.getJSONArray("legs").getJSONObject(0);
            return location.getJSONObject("duration").getInt("value");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private float computeConvenience(String driver) {
        return (float)this.getDuration(DriversSuggestor.ORIGIN, driver) / (this.durationOfBringingUp + this.getDuration(this.destination, driver));
    }

    @Override
    public int compare(String o1, String o2) {
        return this.computeConvenience(o1) > this.computeConvenience(o2) ? -1 : 1;
    }
}
