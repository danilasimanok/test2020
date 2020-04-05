import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, JSONException, URISyntaxException {
        PublicCreator creator = new PublicCreator();
        Pair persons = creator.getPublic();
        Collection<String> passengers = persons.first,
                drivers = persons.second,
                suggestedDrivers;
        DriversSuggestor suggestor;
        for (String passenger : passengers) {
            System.out.println("Passenger point: " + passenger);
            suggestor = new DriversSuggestor(passenger);
            suggestedDrivers = suggestor.suggestDrivers(drivers);
            for (String driver: suggestedDrivers)
                System.out.println("\tDriver point: " + driver);
        }
    }
}
