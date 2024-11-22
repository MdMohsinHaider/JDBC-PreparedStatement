package com.infosys.jdbc_prepared_statetment_curd.service;

import com.infosys.jdbc_prepared_statetment_curd.dao.LaptopDao;
import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;

public class LaptopService {
    LaptopDao dao = new LaptopDao();

    // .....  1.Insert serves ..........
    public Laptop saveLaptopService(Laptop laptop) {

        // check laptop object not null
        if (laptop == null) System.err.println("Laptop object cannot be null.");

        assert laptop != null;
        int id = laptop.getId();
        String name = laptop.getName();
        String color = laptop.getColor();
        double price = laptop.getPrice();

        if (id <= 0 && name == null || name.trim().isEmpty() && color == null || color.trim().isEmpty() && price <= 0){
            System.err.println(" Check Details insert ");
            return null;
        }
        else {
            return dao.saveLaptopDao(laptop);
        }

    }
}
