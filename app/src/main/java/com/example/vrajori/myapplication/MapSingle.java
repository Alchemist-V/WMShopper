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


public class MapSingle extends Activity {

    GoogleMap googleMap;

    String[] selectedItem = new CreateListAct().selectionList;
    int size = new CreateListAct().count;


    WMItemInventory inventory = new WMItemInventory();
    public WMStore store1 = new WMStore("Walmart Redding", 40.1860423, -118.1785392, inventory.getStore1());
    public WMStore store2 = new WMStore("Walmart Chico", 39.7578464, -121.8059006, inventory.getStore2());
    public WMStore store3 = new WMStore("Walmart Whitehorn", 40.0231331, -123.9414739, inventory.getStore3());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_single);
        createMapView();

        for(int i =0; i<size; i++){
            System.out.print(selectedItem[i]);
        }
        addMarker(store1.storeX, store1.storeY, store1.storeName);
        addMarker(store2.storeX, store2.storeY, store2.storeName);
        addMarker(store3.storeX, store3.storeY, store3.storeName);
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
