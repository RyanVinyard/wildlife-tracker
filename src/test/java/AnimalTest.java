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

  @Test
  public void equals_returnsTrueWithSameName_true() {
    Animal testAnimal = new Animal("Penguin");
    Animal secondAnimal = new Animal("Penguin");
    assertTrue(testAnimal.equals(secondAnimal));
  }

  @Test
  public void all_returnsAllAnimals_true() {
    Animal testAnimal = new Animal("Penguin");
    testAnimal.save();
    Animal secondAnimal = new Animal("Two Headed Penguin");
    secondAnimal.save();
    assertEquals(2, Animal.all().size());
  }

  @Test
  public void find_returnsAnimalSharingSameId_otherAnimal() {
    Animal testAnimal = new Animal("Penguin");
    testAnimal.save();
    Animal otherAnimal = new Animal("Two Headed Penguin");
    otherAnimal.save();
    assertEquals(Animal.find(otherAnimal.getId()), otherAnimal);
  }
}
