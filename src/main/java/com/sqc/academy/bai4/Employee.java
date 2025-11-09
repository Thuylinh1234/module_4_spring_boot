package com.sqc.academy.bai4;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {
    private String id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private double salary;
    private String phone;


    public Employee() {
        this.id = UUID.randomUUID().toString(); // Tự sinh ID
    }


    public Employee(String id, String name, LocalDate dob, Gender gender, double salary, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }


    public Employee(String name, LocalDate dob, Gender gender, double salary, String phone) {
        this.id = UUID.randomUUID().toString(); // Tự sinh ID
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
