package se.maokei.mserver.model;

import java.util.HashMap;

public class UsersStatus {
    String name;
    HashMap<String, Object> data;

    public UsersStatus(String name, HashMap<String, Object> data) {
        this.name = name;
        this.data = data;
    }

    public UsersStatus() {}
}
