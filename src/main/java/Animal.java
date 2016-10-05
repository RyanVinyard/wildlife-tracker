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
}
