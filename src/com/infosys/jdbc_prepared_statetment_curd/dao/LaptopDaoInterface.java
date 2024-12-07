package com.infosys.jdbc_prepared_statetment_curd.dao;

import com.infosys.jdbc_prepared_statetment_curd.entity.Laptop;
import java.util.List;

public interface LaptopDaoInterface {
    Laptop saveLaptopDao(Laptop laptop);
    int deleteLaptopByIdDao(int laptopId);
    Laptop updateLaptopByIdDao(int laptopId, Laptop laptop);
    List<Laptop> getAllLaptopDao();
    Laptop getLaptopByIdDao(int laptopId);
    List<Laptop> saveMultipleLaptopsDao(List<Laptop> laptops);
}
