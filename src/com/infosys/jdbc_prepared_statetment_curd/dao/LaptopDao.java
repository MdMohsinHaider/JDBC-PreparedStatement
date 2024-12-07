package com.infosys.jdbc_prepared_statetment_curd.dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.infosys.jdbc_prepared_statetment_curd.connection.LaptopConnection;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;


public class LaptopDao implements LaptopDaoInterface {

    Connection connection = LaptopConnection.getLaptopConnection();

    // .................................... 1. insert method ...................................
    @Override
    public Laptop saveLaptopDao(Laptop laptop) {
        try {
            String INSERT_LAPTOP_QUERY = "insert into laptop(id,name,color,price,mfd ) value(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(INSERT_LAPTOP_QUERY);
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
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }




    // ............................ 2. Delete method ......................................
    @Override
    public int deleteLaptopByIdDao(int laptopId) {
        try {
            String DELETE_LAPTOP_QUERY = "delete from laptop where id=?";
            PreparedStatement ps = connection.prepareStatement(DELETE_LAPTOP_QUERY);
            ps.setInt(1,laptopId);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }






    // --------------------3. Update -----------------------------------------------------------
    @Override
    public Laptop updateLaptopByIdDao(int laptopId, Laptop laptop){
        try {
            String UPDATE_LAPTOP_QUERY = "update laptop set name=?,color=?,price=?,mfd=? where id=?";
            PreparedStatement ps = connection.prepareStatement(UPDATE_LAPTOP_QUERY);
            ps.setInt(5,laptopId);
            ps.setString(1,laptop.getName());
            ps.setString(2, laptop.getColor());
            ps.setDouble(3,laptop.getPrice());
            ps.setObject(4,laptop.getMfd());

            int a = ps.executeUpdate(); // if save return 0< return
            System.out.println("..Successfully update values..");
            return a != 0?laptop:null;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }






    // .......4. Display all the data .........................................................
    @Override
    public List<Laptop> getAllLaptopDao() {
        try {
            String DISPLAY_LAPTOP_QUERY = "select * from laptop";
            PreparedStatement ps = connection.prepareStatement(DISPLAY_LAPTOP_QUERY);
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
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }








    //............. 5. Display Single Laptop data..........................................
    @Override
    public Laptop getLaptopByIdDao(int laptop_id) {
        try {
            // Prepare the statement with the query
            String DISPLAY_SINGLE_LAPTOP_QUERY = "select * from laptop where id=?";
            PreparedStatement ps = connection.prepareStatement(DISPLAY_SINGLE_LAPTOP_QUERY);
            ps.setInt(1, laptop_id);

            // Execute the query
            ResultSet result_set = ps.executeQuery();

//            result_set.next(); // it return boolean true/false;

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
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    // ................................. 1. insert Multiple laptop  method .................................
    @Override
    public List<Laptop> saveMultipleLaptopsDao(List<Laptop> laptops){
        String INSERT_LAPTOP_QUERY = "insert into laptop(id,name,color,price,mfd ) value(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LAPTOP_QUERY);
            for (Laptop laptop :laptops){
                preparedStatement.setInt(1,laptop.getId());
                preparedStatement.setString(2, laptop.getName());
                preparedStatement.setString(3, laptop.getColor());
                preparedStatement.setDouble(4, laptop.getPrice());
                preparedStatement.setObject(5, laptop.getMfd());
                preparedStatement.addBatch();
            }
            assert preparedStatement != null;
            int[] a = preparedStatement.executeBatch();
            return laptops;
        }
        catch (Exception e) {
            // Print detailed error for debugging
            System.err.println("Error Multiple laptops save data: " + e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }


}
