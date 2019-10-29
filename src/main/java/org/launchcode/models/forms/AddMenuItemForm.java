package org.launchcode.models.forms;

import org.launchcode.models.Rx;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    private Menu menu;

    public Iterable<Rx> getRxes() {
        return rxes;
    }

    public void setRxes(Iterable<Rx> rxes) {
        this.rxes = rxes;
    }

    private Iterable<Rx> rxes;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @NotNull
    private int menuId;

    public int getRxId() {
        return rxId;
    }

    @NotNull
    private int rxId;

    public AddMenuItemForm() {
    }

    public AddMenuItemForm(Iterable<Rx> rxes, Menu menu) {
        this.rxes = rxes;
        this.menu = menu;
    }

    public void setRxId(int rxId) {
        this.rxId = rxId;
    }
}