package com.example.vrajori.myapplication;


import android.app.ListActivity;
import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import com.example.vrajori.myapplication.WMItemInventory;
import android.view.Menu;
import android.view.MenuItem;


public class CreateListAct extends ListActivity {


    public String[] selectionList = new String[20];

    int count =0;

    WMItemInventory inventory = new WMItemInventory();
    String[] items = inventory.getInventory();

//    public WMStore store1 = new WMStore("Walmart Redding", 40.1860423, -118.1785392, inventory.getStore1());
//    public WMStore store2 = new WMStore("Walmart Chico", 39.7578464, -121.8059006, inventory.getStore2());
//    public WMStore store3 = new WMStore("Walmart Whitehorn", 40.0231331, -123.9414739, inventory.getStore3());


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        count =0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        ListView listView = getListView();
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);

        //Listview adapter
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, items));
    }

    public void callMapSingleActivity(View view){
        Intent intent = new Intent(this, MapSingle.class);
        startActivity(intent);
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
        Toast.makeText(this, "You have added  : " + items[position],
                Toast.LENGTH_SHORT).show();
        selectionList[count] = items[position];
        count ++;
    }
}
