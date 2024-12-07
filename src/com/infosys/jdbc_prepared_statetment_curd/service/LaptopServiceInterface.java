package com.infosys.jdbc_prepared_statetment_curd.service;

import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;

import java.util.List;

public interface LaptopServiceInterface {

    /**
     * Saves a Laptop object to the database after performing the necessary validations.
     *
     * @param laptop The Laptop object to be saved.
     * @return They saved a Laptop object if successful, otherwise null.
     */
    Laptop saveLaptopService(Laptop laptop);

    /**
     * Filters laptops based on their price using a stream.
     *
     * @param price The maximum price threshold for filtering laptops.
     * @return A list of laptops that have a price less than or equal to the specified threshold.
     */
    List<Laptop> filterLaptopByPriceService(double price);

    /**
     * Saves multiple Laptop objects in a batch process.
     *
     * @param laptops A list of Laptop objects to be saved.
     * @return A list of saved Laptop objects if successful, otherwise null.
     */
    List<Laptop> saveMultipleLaptopsService(List<Laptop> laptops);
}
