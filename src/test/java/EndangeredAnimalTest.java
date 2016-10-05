import org.junit.*;
import org.sql2o.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Animal_instantiatesAsEndangeredAnimalCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Dodo", "healthy", "adult");
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getHealth_returnsAnimalHealth_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Dodo", "healthy", "adult");
    assertEquals("healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void save_ObjectInstantiatesAndSavesWithId_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Dodo", "healthy", "adult");
    testEndangeredAnimal.save();
    assertTrue(testEndangeredAnimal.getId() > 0);
  }
}
