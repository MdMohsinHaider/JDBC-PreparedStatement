package com.infosys.jdbc_prepared_statetment_curd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    // .......4. Display ........
    public List<Laptop> getAllLaptopDao() {
        try {
            PreparedStatement ps = connection.prepareStatement(DISPLAYLAPTOPQUERY);
            ResultSet result_set = ps.executeQuery();
            List<Laptop> laptops_list = new ArrayList<>();

            while (result_set.next()){
                int id = result_set.getInt("id");
                String
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
