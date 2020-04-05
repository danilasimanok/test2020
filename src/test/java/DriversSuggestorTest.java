import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DriversSuggestorTest {
    @Test
    public void testComparator() {
        String passanger = "60.012772,30.302589",
                driver1 = "60.019475,30.297782",
                driver2 = "59.876186,30.262420";
        DriversSuggestor suggestor = new DriversSuggestor(passanger);
        Assertions.assertEquals(-1, suggestor.compare(driver1, driver2));
    }
}
