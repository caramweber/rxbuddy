package org.launchcode.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();


    // add
    public static void add(User newUser){
        users.add(newUser);
    }

    //getAll

    public static ArrayList<User> getAll() {
        return users;
    }

    //getById
    public static User getById(int id){
        User theUser = null;

        for(User userCandidate : users) {
            if (userCandidate.getUserId() == id) {
                theUser = userCandidate;
            }
        }
        return theUser;

    }
}