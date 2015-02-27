package com.example.nhnnext.day2_02nextagram_intent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnClickListener { // , AdapterView.OnItemClickListener

    private Button mButtonWrite;
    private Button mButtonRefresh;
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButtonWrite  = (Button) findViewById(R.id.activity_main_button1);
        Button mButtonRefresh  = (Button) findViewById(R.id.activity_main_button2);
        mButtonWrite.setOnClickListener(this);
        mButtonRefresh.setOnClickListener(this);

        mainListView = (ListView) findViewById(R.id.main_listView);

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

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.activity_main_button1:
                Intent intentWrite = new Intent(this, ArticleWrite.class);
                startActivity(intentWrite);
                break;
        }
    }
}
