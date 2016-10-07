import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Animal {
  public int id;
  public String name;
  public boolean endangered;

  public Animal(String name) {
    this.name = name;
    this.id = id;
    endangered = false;
  }

  public String getName() {
    return name;
  }

  public boolean isEndangered() {
    return endangered;
  }

  public int getId() {
    return id;
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Animal.class);
    }
  }

public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("endangered", this.endangered)
      .throwOnMappingFailure(false)
      .executeUpdate()
      .getKey();
      //Save function saves to database
    }
  }

@Override
public boolean equals(Object otherAnimal) {
  if(!(otherAnimal instanceof Animal)) {
    return false;
  } else {
    Animal newAnimal = (Animal) otherAnimal;
    return this.getName().equals(newAnimal.getName()) && this.isEndangered() == (newAnimal.isEndangered());
    //Overrides already set public equals function
    }
  }

public static Animal find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM animals WHERE id=:id;";
    Animal animal = con.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetchFirst(Animal.class);
    return animal;
  } catch (IndexOutOfBoundsException exception) {
    return null;
    //Find function to find from database
  }
}

public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM animals WHERE id=:id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeUpdate();
      //Delete function to delete from database, remember what happens if you forget this!
    }
  }

public void update(String name) {
  try(Connection runnerman = DB.sql2o.open()) {
    String sequel = "UPDATE animals SET name = :name WHERE id=:id";
    runnerman.createQuery(sequel)
             .addParameter("id", id)
             .addParameter("name", name)
             .executeUpdate();
    //Update function for changing, in this case, name of animal
    }
  }

  public List<Sighting> getUniqueSighting() {
  try(Connection runnerman = DB.sql2o.open()) {
    String sql = "SELECT * FROM sightings WHERE animal_id=:id";
      List<Sighting> sightings = runnerman.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Sighting.class);
      return sightings;
    }
  }
}
