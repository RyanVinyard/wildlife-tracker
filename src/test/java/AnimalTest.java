import org.junit.*;
import org.sql2o.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class AnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Penguin");
    assertEquals(true, testAnimal instanceof Animal);
  }
}
