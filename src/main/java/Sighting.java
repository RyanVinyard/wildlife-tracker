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
  }
  }
}
