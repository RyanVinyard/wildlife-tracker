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

  
