package controllers;

import models.Dessert;
import models.Temp;
import play.mvc.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import play.data.DynamicForm;
import play.data.Form;

public class HomeController extends Controller {

     //static Form<Temp> tempForm = Form.form(Temp.class);
    public ArrayList  fetch_data() throws SQLException {
        ArrayList<Dessert> desserts = new ArrayList<Dessert>();
        ResultSet rs;
        Dessert d = new Dessert();
        d.connection();
        rs = d.view();
        int i=0;
        while (rs.next())
        {
            i++;
            desserts.add(new Dessert(rs.getInt("dessert_id"),rs.getString("dessert_name"),rs.getFloat("dessert_price"),rs.getInt("dessert_quantity"),rs.getString("dessert_category_name"),rs.getString("dessert_flavour_name"),rs.getString("dessert_image"),i));
        }
        return  desserts;
    }

    public  Result index() throws SQLException {
        ArrayList desserts = fetch_data();
        return ok(views.html.index.render(desserts));
    }
//    @Inject
//    public HomeController(final FormFactory formFactory) {
//        this.formFactory = formFactory;
//    }

    public Result add_dessert() {

        System.out.println("Heyy");
        return ok("ok, I recived POST data. That's all...");
    }


    public Result add() {

        return ok(views.html.add.render());
    }
    public Result modify(int dessert_id) {

        return ok(views.html.modify.render());
    }
    public Result remove(String dessert_id) throws SQLException {

        String id_replace = dessert_id.replace(":","0");
        int id = Integer.parseInt(id_replace);
        Dessert d =  new Dessert();
        d.remove(id);
        ArrayList desserts = fetch_data();
        return ok(views.html.index.render(desserts));
    }
    public Result others() {
        return ok(views.html.others.render());
    }

}
