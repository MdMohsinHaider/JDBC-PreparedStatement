package com.infosys.jdbc_prepared_statetment_curd.service;

import com.infosys.jdbc_prepared_statetment_curd.dao.LaptopDao;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopService implements LaptopServiceInterface{
    LaptopDao dao = new LaptopDao();

    /**
     * Saves a Laptop object to the database after performing the necessary validations.
     *
     * @param laptop The Laptop object to be saved.
     * @return They saved a Laptop object if successful, otherwise null.
     */
    @Override
    public Laptop saveLaptopService(Laptop laptop) {

        // Check if the Laptop object is null and log an error if so
        if (laptop == null) {
            System.err.println("Laptop object cannot be null.");
            return null; // Exit early as no further processing is possible
        }

        // Extract laptop properties for validation.
        String name = laptop.getName();
        String color = laptop.getColor();
        double price = laptop.getPrice();

        // Validate the data before saving to the database
        if (name != null && color != null && price >= 0) {
            // If validation passes, save the laptop object using the DAO and return it
            return dao.saveLaptopDao(laptop);
        } else {
            // If validation fails, log an error and return null
            System.err.println("Invalid laptop details. Please check the inputs.");
            return null;
        }
    }





    /**
     * Filters laptops based on their price using a stream.
     *
     * @param price The maximum price threshold for filtering laptops.
     * @return A list of laptops that have a price less than or equal to the specified threshold.
     */
    @Override
    public List<Laptop> filterLaptopByPriceService(double price) {
        // Fetch all laptops from the DAO (Data Access Object)
        List<Laptop> laptops = dao.getAllLaptopDao();

        // Use a stream to filter laptops based on the given price
        return laptops
                .stream() // Convert the list to a stream
                .filter(a -> a.getPrice() <= price) // Retain laptops with price <= specified price
                .collect(Collectors.toList()); // Collect the filtered laptops back into a list
    }



    @Override
    // Multiple Laptops batch Process
    public List<Laptop> saveMultipleLaptopsService(List<Laptop> laptops){
        return dao.saveMultipleLaptopsDao(laptops);
    }

}



