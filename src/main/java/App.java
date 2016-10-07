import java.util.Map;
import java.util.List;
import java.util.HashMap;
import spark.ModelAndView;
import java.util.ArrayList;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.getEndangeredAnimals());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      int animalId = Integer.parseInt(request.queryParams("animal"));
      String location = request.queryParams("location");
      if(rangerName.equals("") || location.equals("")) {
        response.redirect(String.format("/failure"));
        throw new UnsupportedOperationException("The form was not filled out correctly");
      }
      Sighting sighting = new Sighting(animalId, location, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", Animal.all());
      String animal = Animal.find(animalId).getName();
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("Poor", EndangeredAnimal.HORRIBLE_HEALTH);
      model.put("Ok", EndangeredAnimal.BAD_HEALTH);
      model.put("Excellent", EndangeredAnimal.GOOD_HEALTH);
      model.put("Infant", EndangeredAnimal.INFANT);
      model.put("Young", EndangeredAnimal.YOUNG);
      model.put("Adult", EndangeredAnimal.ADULT);
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.getEndangeredAnimals());
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean endangered = request.queryParamsValues("endangered")!=null;
      if (endangered) {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        if(name.equals("")) {
          response.redirect(String.format("/failure"));
          throw new UnsupportedOperationException("Form was not completed correctly");
        }
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
        endangeredAnimal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.getEndangeredAnimals());
      } else {
        String name = request.queryParams("name");
        if(name.equals("")) {
          response.redirect(String.format("/failure"));
          throw new UnsupportedOperationException("Form was not completed correctly");
        }
        Animal animal = new Animal(name);
        animal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.getEndangeredAnimals());
      }
      response.redirect("/");
       return null;
     });

     get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params("id")));
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.findEndangeredAnimal(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/failure", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("template", "templates/failure.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());
  }
}
