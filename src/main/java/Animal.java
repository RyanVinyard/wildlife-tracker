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
  }
}

@Override
public boolean equals(Object otherAnimal) {
  if(!(otherAnimal instanceof Animal)) {
    return false;
  } else {
    Animal newAnimal = (Animal) otherAnimal;
    return this.getName().equals(newAnimal.getName()) && this.isEndangered() == (newAnimal.isEndangered());
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
  }
}
}
