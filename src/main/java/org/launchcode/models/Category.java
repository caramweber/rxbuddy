package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    public int getId() {return id;}

    @NotNull
    @Size(min=3, max=15, message = "Description must be between 3-15 characters")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Category(){}
    public Category(String name){}

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Rx> rxes = new ArrayList<>();
}