package com.example.vrajori.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Map;


public class MapSingle extends Activity {

    GoogleMap googleMap;

    CreateListAct t = new CreateListAct();
    int size = t.count;

    WMItemInventory inventory = new WMItemInventory();
    public WMStore store1 = new WMStore("Walmart Redding", 40.1860423, -118.1785392, inventory.getStore1());
    public WMStore store2 = new WMStore("Walmart Chico", 39.7578464, -121.8059006, inventory.getStore2());
    public WMStore store3 = new WMStore("Walmart Whitehorn", 40.0231331, -123.9414739, inventory.getStore3());
    public WMStore store4 = new WMStore("Walmart NoWhere", 45.0231331, -160.9414739, inventory.getStore4());

    public boolean find(String s, String[] set, int sizeOfArray){
        for (int i=0; i<sizeOfArray; i++){
            if (s.toLowerCase().equalsIgnoreCase(set[i].toLowerCase())){
                return true;
            }
        }
        return false;
    }

    int[] locatedList = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_single);
        createMapView();

        for(int i =0; i<size; i++){
            if (!find (t.selectionList[i], store1.store_inventory, store1.store_inventory.length)){
                if (!find (t.selectionList[i], store2.store_inventory, store2.store_inventory.length)){
                    if (!find (t.selectionList[i], store3.store_inventory, store3.store_inventory.length)){
                        if (!find (t.selectionList[i], store4.store_inventory, store4.store_inventory.length)){
                            locatedList[i]=-1;
                        }
                        else{
                            locatedList[i]=4;
                        }
                    }
                    else{
                        locatedList[i]=3;
                    }
                }
                else{
                    locatedList[i]=2;
                }

            }
            else{
                locatedList[i]=1;
            }
        }

        boolean setStore1 = false;
        boolean setStore2 = false;
        boolean setStore3 = false;
        boolean setStore4 = false;


        for (int k =0; k<locatedList.length; k++){
            if (locatedList[k]==1){
                setStore1=true;
                store1.storeName = store1.storeName + "\n" + t.selectionList[k];
            }
            if (locatedList[k]==2){
                setStore2=true;
                store2.storeName = store2.storeName + "\n" + t.selectionList[k];
            }
            if (locatedList[k]==3){
                setStore3=true;
                store3.storeName = store3.storeName + "\n" + t.selectionList[k];
            }
            if (locatedList[k]==4){
                setStore4=true;
                store4.storeName = store4.storeName + "\n" + t.selectionList[k];
            }
        }
        adjustMap(googleMap);

        if (setStore1)
            addMarker(store1.storeX, store1.storeY, store1.storeName);
        if (setStore2)
            addMarker(store2.storeX, store2.storeY, store2.storeName);
        if (setStore3)
            addMarker(store3.storeX, store3.storeY, store3.storeName);
        if(setStore4)
            addMarker(store4.storeX, store4.storeY, store4.storeName);
    }

    public void adjustMap(GoogleMap gm ){
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(39.7578464, -121.8059006));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(5);

        gm.moveCamera(center);
        gm.animateCamera(zoom);
    }

    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }


            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }}


    /**
     * Adds a marker to the map
     */
    private void addMarker(double X, double Y, String storeName){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(X, Y))
                            .title(storeName)
                            .draggable(true)
            );
        }
    }


//    @Override
//    public void onMapReady(){
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
