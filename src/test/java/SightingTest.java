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
    Sighting testSighting = new Sighting(testAnimal.getId(), "Antarctica", "Pete Jones");
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void equals_savesAndReturnsTrueWhenTwoInstancesAreEqual_true() {
    Animal testAnimal = new Animal("Penguin");
    testAnimal.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), "Antarctica", "Pete Jones");
    Sighting otherSighting = new Sighting(testAnimal.getId(), "Antarctica", "Pete Jones");
    assertTrue(testSighting.equals(otherSighting));
  }

  @Test
  public void all_returnsAllSightings_true() {
    Sighting testSighting = new Sighting(0, "Antarctica", "Pete Jones");
    testSighting.save();
    Sighting anotherTestSighting = new Sighting(1, "Africa", "Pete Jones");
    anotherTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void find_returnsEquivalentFindings_otherSighting() {
    Sighting testSighting = new Sighting (0, "Antarctica", "Pete Jones");
    testSighting.save();
    Sighting otherSighting = new Sighting (1, "Africa", "Pete Jones");
    otherSighting.save();
    assertEquals(Sighting.find(otherSighting.getId()), otherSighting);
  }
}
