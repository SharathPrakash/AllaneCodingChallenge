package com.sp.coding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "vin")
    private String vin;

    @Column(name = "price")
    private double price;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private LeasingContract leasingContract;


    public Vehicle(String brand, String model, int modelYear, double price) {
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.price = price;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LeasingContract getLeasingContract() {
        return leasingContract;
    }

    public void setLeasingContract(LeasingContract leasingContract) {
        this.leasingContract = leasingContract;
    }
}
