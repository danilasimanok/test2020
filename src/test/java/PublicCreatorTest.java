import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PublicCreatorTest {
    @Test
    public void testCreation() {
        try {
            PublicCreator creator = new PublicCreator();
            Pair pair = creator.getPublic();
            Assertions.assertFalse(pair.first.isEmpty());
            Assertions.assertFalse(pair.second.isEmpty());
        } catch (Exception e) {
            Assertions.fail();
        }

    }
}
