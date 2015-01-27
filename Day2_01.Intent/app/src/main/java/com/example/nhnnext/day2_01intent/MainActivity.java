package com.example.nhnnext.day2_01intent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private Button mButtonSimpleList1;
    private Button mButtonSimpleList2;
    private Button mButtonCustomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSimpleList1 = (Button) findViewById(R.id.main_button_simple_list1);
        mButtonSimpleList2 = (Button) findViewById(R.id.main_button_simple_list2);
        mButtonCustomList = (Button) findViewById(R.id.main_button_custom_list);

        mButtonSimpleList1.setOnClickListener(this);
        mButtonSimpleList2.setOnClickListener(this);
        mButtonCustomList.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.main_button_simple_list1:
                Intent intentSimpleList1 = new Intent(this, SimpleList1Activity.class);
                startActivity(intentSimpleList1);
                break;
            case R.id.main_button_simple_list2:
                Intent intentSimpleList2 = new Intent(this, SimpleList2Activity.class);
                startActivity(intentSimpleList2);
                break;
            case R.id.main_button_custom_list:
                Intent intentCustomList = new Intent(this, CustomListActivity.class);
                startActivity(intentCustomList);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
