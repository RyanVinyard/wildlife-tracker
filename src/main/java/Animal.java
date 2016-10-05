import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Animal {
  public String name;
  public int id;
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

  
