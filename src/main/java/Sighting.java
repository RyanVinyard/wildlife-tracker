import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;

public class Sighting {

  private int id;
  private int animal_id;
  private String location;
  private String ranger_name;
  private Timestamp time;

  public Sighting(int animal_id, String location, String ranger_name) {
    if (location.equals("") || ranger_name.equals("")) {
      throw new IllegalArgumentException("This form is not complete!");
    }
    if(location.equals(ranger_name)){
      throw new IllegalArgumentException("You accidentially filled out the same info in both name and location fields!");
    }
    this.animal_id = animal_id;
    this.location = location;
    this.ranger_name = ranger_name;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  public Timestamp getTime() {
    return time;
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO sightings (animal_id, location, ranger_name, time) VALUES (:animal_id, :location, :ranger_name, now());";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("animal_id", this.animal_id)
      .addParameter("location", this.location)
      .addParameter("ranger_name", this.ranger_name)
      .throwOnMappingFailure(false)
      .executeUpdate()
      .getKey();
      //Save function for saving to Postgres database
    }
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) &&
      this.getLocation().equals(newSighting.getLocation()) &&
      this.getRangerName().equals(newSighting.getRangerName());
      //Overrides the equals function already in use due to being public
    }
  }

  public static List<Sighting> all() {
    try(Connection runnerman = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings";
      return runnerman.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
        //All function to query all entries of sighting(in this case) from database
    }
  }

  public static Sighting find(int id) {
    try(Connection runnerman = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id = :id";
      Sighting sighting = runnerman.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
      //Find function to grab specific entries from database by Id
    }
  }

  public void delete() {
    try(Connection runnerman = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings WHERE id=:id";
      runnerman.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
