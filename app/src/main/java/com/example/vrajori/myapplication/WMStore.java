package com.example.vrajori.myapplication;

/**
 * Created by vrajori on 6/21/15.
 */
public class WMStore {

    //public int store_id;
    public String storeName;
    public double storeX;
    public double storeY;
    public String[] store_inventory;

    public WMStore(String storeName, double storeX, double storeY, String[] store_inventory) {
        this.storeName = storeName;
        this.storeX = storeX;
        this.storeY = storeY;
        this.store_inventory = store_inventory;
    }
}
