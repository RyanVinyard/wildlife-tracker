import org.junit.*;
import org.sql2o.*;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.text.DateFormat;
import static org.junit.Assert.*;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Penguin");
    testAnimal.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), "Antarcica", "Pete Jones");
    assertEquals(true, testSighting instanceof Sighting);
  }
}
