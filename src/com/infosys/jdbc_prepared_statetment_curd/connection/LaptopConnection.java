package com.infosys.jdbc_prepared_statetment_curd.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class LaptopConnection {
    public static Connection getLaptopConnection() {

        try {
            // Step: 1 load/register driver
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            //  Step: 2 connection
            String url= "jdbc:mysql://localhost:3306/ DATA BASES NAME ";
            String user_name = "USER NAME";
            String passwordString = "USER PASSWORD";
            return DriverManager.getConnection(url,user_name,passwordString);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
