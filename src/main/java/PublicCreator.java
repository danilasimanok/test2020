import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PublicCreator {
    List<String> coordinatesList;

    public PublicCreator() throws URISyntaxException, IOException {
        this.coordinatesList = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("latlos").toURI()));
    }

    public Pair getPublic() {
        ArrayList<String> drivers = new ArrayList<>(10),
                passengers = new ArrayList<>(10);
        int i = 0;
        for (String coordinates : coordinatesList){
            if (i++ < 10)
                passengers.add(coordinates.replace(" ", ""));
            else
                drivers.add(coordinates.replace(" ", ""));
        }
        return new Pair(passengers, drivers);
    }
}
