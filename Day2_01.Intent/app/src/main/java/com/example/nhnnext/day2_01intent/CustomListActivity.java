package com.example.nhnnext.day2_01intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class CustomListActivity extends ActionBarActivity {

    private ArrayList<ListData> listDataArray = new ArrayList<ListData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        ListData data1 = new ListData("1 - 첫 번째 줄", "1 - 두 번째 줄", "01.jpg");
        listDataArray.add(data1);

        ListData data2 = new ListData("2 - 첫 번째 줄", "2 - 두 번째 줄", "02.jpg");
        listDataArray.add(data2);

        ListData data3 = new ListData("3 - 첫 번째 줄", "3 - 두 번째 줄", "03.jpg");
        listDataArray.add(data3);

        ListData data4 = new ListData("4 - 첫 번째 줄", "4 - 두 번째 줄", "04.jpg");
        listDataArray.add(data4);

        ListData data5 = new ListData("5 - 첫 번째 줄", "5 - 두 번째 줄", "05.jpg");
        listDataArray.add(data5);

        ListView listView = (ListView)findViewById(R.id.custom_list_listView);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
        listView.setAdapter(customAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_list, menu);
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


