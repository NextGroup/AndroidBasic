package com.example.nhnnext.day2_01intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class SimpleList1Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list1);

        ListView listView = (ListView) findViewById(R.id.simple_List1_listView);
        ArrayList<String> arrayList1 = new ArrayList<String>();

        arrayList1.add("데이터1");
        arrayList1.add("데이터2");
        arrayList1.add("데이터3");
        arrayList1.add("데이터4");
        arrayList1.add("데이터5");
        arrayList1.add("데이터6");

        ArrayAdapter<String> simpleAdapter1;
        simpleAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList1);
        listView.setAdapter(simpleAdapter1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_list1, menu);
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
