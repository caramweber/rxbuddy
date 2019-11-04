package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Rx {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Name has to be between 3 to 15 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    private String rxnum;

    @Size(min=1)
    private String docname;

    private String docnum;

    private String docweb;

    private String pharmname;

    private String pharmnum;

    private String pharmweb;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //    @Null(message = "Please enter in a Category before adding a prescription")
    @ManyToOne
    private Category category;

//    private RxType type;

    public Rx(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Rx() { }

    @ManyToMany(mappedBy = "rxes")
    private List<Menu> menus;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRxnum() {
        return rxnum;
    }

    public void setRxnum(String rxnum) {
        this.rxnum = rxnum;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocnum() {
        return docnum;
    }

    public void setDocnum(String docnum) {
        this.docnum = docnum;
    }

    public String getDocweb() {
        return docweb;
    }

    public void setDocweb(String docweb) {
        this.docweb = docweb;
    }

    public String getPharmname() {
        return pharmname;
    }

    public void setPharmname(String pharmname) {
        this.pharmname = pharmname;
    }

    public String getPharmnum() {
        return pharmnum;
    }

    public void setPharmnum(String pharmnum) {
        this.pharmnum = pharmnum;
    }

    public String getPharmweb() {
        return pharmweb;
    }

    public void setPharmweb(String pharmweb) {
        this.pharmweb = pharmweb;
    }
}