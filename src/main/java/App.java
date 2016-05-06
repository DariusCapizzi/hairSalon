import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //root
    get("/", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      if(Stylist.all().size() > 0){
        model.put("stylists", Stylist.all());
      }
      if(Client.all().size() > 0){
        model.put("clients", Client.all());
      }
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //CREATE
    post("/stylist/new", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String inStylistName = req.queryParams("stylist-name");
      Stylist newStylist = new Stylist(inStylistName);
      newStylist.save();
      System.out.println(req.uri());
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylist_id/client/new", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String inClientName = req.queryParams("client-name");
      Client newClient = new Client(inClientName, Integer.parseInt(req.params(":stylist_id")));
      newClient.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //READ
    get("/stylists/:stylist_id", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      if(Stylist.all().size() > 0){
        model.put("stylists", Stylist.all());
      }
      if(Client.all().size() > 0){
        model.put("clients", Client.all());
      }

      Stylist thisStylist = Stylist.find(Integer.parseInt(req.params(":stylist_id")));
      model.put("stylist", thisStylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/clients/:client_id", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      if(Stylist.all().size() > 0){
        model.put("stylists", Stylist.all());
      }
      if(Client.all().size() > 0){
        model.put("clients", Client.all());
      }
      Client thisClient = Client.find(Integer.parseInt(req.params(":client_id")));
      model.put("client",thisClient);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //UPDATE



    //DELETE
    post("/stylists/:stylist_id/remove", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist thisStylist = Stylist.find(Integer.parseInt(req.params(":stylist_id")));
      thisStylist.remove();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/:client_id/remove", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client thisClient = Client.find(Integer.parseInt(req.params(":client_id")));
      thisClient.remove();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }

}
