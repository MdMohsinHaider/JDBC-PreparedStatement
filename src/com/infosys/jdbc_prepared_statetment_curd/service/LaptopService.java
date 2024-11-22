package com.infosys.jdbc_prepared_statetment_curd.service;

import com.infosys.jdbc_prepared_statetment_curd.dao.LaptopDao;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopService {
    LaptopDao dao = new LaptopDao();

    // .....  1.Insert serves ..........
    public Laptop saveLaptopService(Laptop laptop) {

        // check laptop object not null
        if (laptop == null) System.err.println("Laptop object cannot be null.");

        // Store data
        String name = laptop.getName();
        String color = laptop.getColor();
        double price = laptop.getPrice();

        // validation of some data before save in databases.
        if (name != null && color != null && price >=0){
            return dao.saveLaptopDao(laptop);
        }
        else {
            System.err.println(" Check Details insert ");
            return null;
        }
    }




    // filter method by using Price;
    public List<Laptop> filterLaptopByPriceService(double price){
        List<Laptop> laptops = dao.getAllLaptopDao();
        return laptops
                .stream()
                .filter(a->a.getPrice()<=price)
                .collect(Collectors.toList());
    }
}
