package com.example.esthere.gett2.model.datasource;


import com.example.esthere.gett2.model.backend.IBackend;
import com.example.esthere.gett2.model.entities.Driver;

public class Globals {
    public static IBackend backend = new FireBase();//new Lists();
    public static Driver driver;
}
