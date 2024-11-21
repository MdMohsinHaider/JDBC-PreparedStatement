package com.infosys.jdbc_prepared_statetment_curd.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Laptop implements Serializable {
    private int id;
    private String name;
    private String color;
    private double price;
    private LocalDate mfd;

    public Laptop(){
        super();
    }

    public Laptop(int id, String name, String color, double price, LocalDate mfd) {
        super();
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.mfd = mfd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getMfd() {
        return mfd;
    }

    public void setMfd(LocalDate mfd) {
        this.mfd = mfd;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", mfd=" + mfd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return id == laptop.id && Double.compare(price, laptop.price) == 0 && Objects.equals(name, laptop.name) && Objects.equals(color, laptop.color) && Objects.equals(mfd, laptop.mfd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, price, mfd);
    }
}
