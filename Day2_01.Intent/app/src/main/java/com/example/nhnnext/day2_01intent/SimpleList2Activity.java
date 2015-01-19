package com.example.nhnnext.day2_01intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;


public class SimpleList2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list2);

        ListView listView = (ListView) findViewById(R.id.simple_list2_listView);
        ArrayList<HashMap<String, String>> hashMapList1 = new ArrayList<HashMap<String, String>>(2);

        for (int i = 0; i < 10; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("line1", "첫 번째 줄의" + i + "번");
            map.put("line2", "두 번째 줄의" + i + "번");
            hashMapList1.add(map);
        }
        String[] from = { "lin1", "lin2" };
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, hashMapList1, android.R.layout.simple_list_item_1, from, to);
        listView.setAdapter(simpleAdapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_list2, menu);
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
