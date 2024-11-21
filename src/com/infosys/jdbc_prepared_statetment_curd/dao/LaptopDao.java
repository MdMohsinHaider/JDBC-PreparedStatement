package com.infosys.jdbc_prepared_statetment_curd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.infosys.jdbc_prepared_statetment_curd.connection.LaptopConnection;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;


public class LaptopDao {

    Connection connection = LaptopConnection.getLaptopConnection();

    private final String INSERTLAPTOPQUERY = "insert into laptop(id,name,color,price,mfd ) value(?,?,?,?,?)";
    private final String DELETELAPTOPQUERY = "delete from laptop where id=?";
    private final String UPDATELAPTOPQUERY = "update laptop set color = ? where id=?";
    private final String DISPLAYLAPTOPQUERY = "select * from laptop";
    private final String DISPALYSINGLELAPTOPQUERY = "select * from laptop where id=?";



    // ........1. insert method .....
    public Laptop saveLaptopDao(Laptop laptop) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERTLAPTOPQUERY);
            ps.setInt(1,laptop.getId());
            ps.setString(2, laptop.getName());
            ps.setString(3, laptop.getColor());
            ps.setDouble(4, laptop.getPrice());
            ps.setObject(5, laptop.getMfd());

            int a = ps.executeUpdate(); // if save return 0< return
            return a != 0?laptop:null;

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }




    // ....... 2. Delete method .....
    public int deleteLaptopByIdDao(int laptopId) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETELAPTOPQUERY);
            ps.setInt(1,laptopId);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }



    // ------3. Update ---
    public int updateLaptopByIdDao(int laptopId){
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATELAPTOPQUERY);
            ps.setInt(1,laptopId);
            return ps.executeUpdate();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return 0;
        }
    }




    // .......4. Display all the data ........
    public List<Laptop> getAllLaptopDao() {
        try {
            PreparedStatement ps = connection.prepareStatement(DISPLAYLAPTOPQUERY);
            ResultSet result_set = ps.executeQuery();
            List<Laptop> laptops_list = new ArrayList<>();

            while (result_set.next()){
                // data store from a database
                int id = result_set.getInt("id");
                String name = result_set.getString("name");
                String color = result_set.getString("color");
                double price = result_set.getDouble("price");
                LocalDate mfd = result_set.getDate("mfd").toLocalDate();

                // data will store in laptop variable or object and data type is Laptop
                Laptop laptop = new Laptop(id,name,color,price,mfd);

                // data add in List and data type is also Laptop
                laptops_list.add(laptop);
            }
            // Return List, which is store all laptop details.
            return laptops_list;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Display Single Laptop data.
    public Laptop getLaptopByIdDao(int laptop_id) {
        try {
            // Prepare the statement with the query
            PreparedStatement ps = connection.prepareStatement(DISPALYSINGLELAPTOPQUERY);
            ps.setInt(1, laptop_id);

            // Execute the query
            ResultSet result_set = ps.executeQuery();

            // Ensure result_set has data before accessing it
            if (result_set.next()) {
                int id = result_set.getInt("id");
                String name = result_set.getString("name");
                String color = result_set.getString("color");
                double price = result_set.getDouble("price");
                LocalDate mfd = result_set.getDate("mfd").toLocalDate();

                // Return the constructed Laptop object
                return new Laptop(id, name, color, price, mfd);
            } else {
                System.err.println("No laptop found with ID: " + laptop_id);
                return null;
            }
        } catch (Exception e) {
            // Print detailed error for debugging
            System.err.println("Error fetching laptop data: " + e.getMessage());
            return null;
        }
    }

}
