package models;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Dessert {

    public int dessert_id,dessert_quantity,srno;
    public String dessert_name,dessert_image,dessert_category_name,dessert_flavour_name;
    public float dessert_price;



    public Dessert(){

    }
    public Dessert(int dessert_id,String dessert_name, float dessert_price, int dessert_quantity, String dessert_category_name, String dessert_flavour_name, String dessert_image, int srno)
    {
        this.srno = srno;
        this.dessert_id = dessert_id;
        this.dessert_name = dessert_name;
        this.dessert_price = dessert_price;
        this.dessert_quantity = dessert_quantity;
        this.dessert_category_name = dessert_category_name;
        this.dessert_flavour_name = dessert_flavour_name;
        this.dessert_image = dessert_image;
 }


    public Connection connection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CAKESHOP", "root", "thinkit");
            return con;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public ResultSet view()throws SQLException
    {
        Connection con = connection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM DESSERT d,DESSERT_CATEGORY c, DESSERT_FLAVOUR f WHERE d.dessert_category_id = c.dessert_category_id and  d.dessert_flavour_id = f.dessert_flavour_id");
        return rs;
    }

    public void remove(int dessert_id) throws SQLException {
        Connection con = connection();
        Statement stmt = con.createStatement();
        int i = stmt.executeUpdate("DELETE FROM DESSERT WHERE DESSERT_ID="+dessert_id);
        con.close();
    }

    public ResultSet get_Dessert_Categories() throws SQLException {
        Connection con = connection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM DESSERT_CATEGORY");
        return rs;

    }
    public ResultSet get_Dessert_Flavours() throws SQLException {
        Connection con = connection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM DESSERT_FLAVOUR");
        return rs;

    }


}
