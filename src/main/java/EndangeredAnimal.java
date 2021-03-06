import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;
  public static final String HORRIBLE_HEALTH = "nearly deceased";
  public static final String BAD_HEALTH = "looking ill";
  public static final String GOOD_HEALTH = "healthy";
  public static final String INFANT = "infant";
  public static final String YOUNG = "young";
  public static final String ADULT = "adult";

  public EndangeredAnimal(String name, String health, String age) {
    super(name);
    endangered = true;
    this.health = health;
    this.age = age;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public static String getHorribleHealth() {
    return HORRIBLE_HEALTH;
  }

  public static String getBadHealth() {
    return BAD_HEALTH;
  }

  public static String getGoodHealth() {
    return GOOD_HEALTH;
  }

  public static String getInfant() {
    return INFANT;
  }

  public static String getYoung() {
    return YOUNG;
  }

  public static String getAdult() {
    return ADULT;
  }

@Override
public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("endangered", this.endangered)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .executeUpdate()
      .getKey();
      //Override public save function to save to database
  }
}

public static List<EndangeredAnimal> getEndangeredAnimals() {
  try(Connection runnerman = DB.sql2o.open()) {
    String sequel = "SELECT * FROM animals WHERE endangered = true";
    return runnerman.createQuery(sequel).throwOnMappingFailure(false)
           .executeAndFetch(EndangeredAnimal.class);
    //get function to grab specifically endangered animals from database
  }
}

public static EndangeredAnimal findEndangeredAnimal(int id) {
  try(Connection runnerman = DB.sql2o.open()) {
    String sql = "SELECT * FROM animals WHERE id = :id";
    EndangeredAnimal endangeredanimal = runnerman.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetchFirst(EndangeredAnimal.class);
    return endangeredanimal;
  } catch (IndexOutOfBoundsException exception) {
    return null;
    //Find function to find endangered animals in database
  }
}

public void updateHealth(String health) {
  try(Connection runnerman = DB.sql2o.open()) {
    String sql = "UPDATE animals SET health = :health WHERE id=:id";
    runnerman.createQuery(sql)
             .addParameter("id", id)
             .addParameter("health", health)
             .executeUpdate();
    //function to update animal's health
  }
}

public void updateAge(String age) {
  try(Connection runnerman = DB.sql2o.open()) {
    String sql = "UPDATE animals SET age = :age WHERE id = :id";
    runnerman.createQuery(sql)
             .addParameter("id", id)
             .addParameter("age", age)
             .executeUpdate();
    //function to update animal's age
    }
  }
}
