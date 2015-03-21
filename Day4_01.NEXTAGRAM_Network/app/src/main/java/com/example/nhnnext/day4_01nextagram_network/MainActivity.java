package com.example.nhnnext.day4_01nextagram_network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements OnClickListener, AdapterView.OnItemClickListener {

    public static String SERVER_ADDRESS = "http://127.0.0.1:5009/";
    private Button mButtonWrite;
    private Button mButtonRefresh;
    private ListView mainListView;
    private ArrayList<Article> articleList;

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
    public void onResume() {
        super.onResume();

        refreshData();
        listView();
    }

    private void listView() {
        // DB로부터 게시글 리스트를 받아옴
        Dao dao = new Dao(getApplicationContext());
        articleList = dao.getArticleList();

        // CustomAdapter를 적용함
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
        mainListView.setAdapter(customAdapter);
        mainListView.setOnItemClickListener(this);
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    private void refreshData() {

        client.get("http://127.0.0.1:5009/loadData", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                // DB에 JSON데이터를 저장
                String jsonData = new String(bytes);
                Log.i("test", "jsonData: " + jsonData);

                Dao dao = new Dao(getApplicationContext());
                dao.insertJsonData(jsonData);

                listView();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
            }
        });
    }


// 아래 주석처리 된 코드는 flask와 LoopJ를 사용하지 않았을 때 진행하는 내용입니다.
//
//    private void refreshData() {
//
//        new Thread() {
//            public void run () {
//                // 서버로부터 JSON 데이터를 가져옴
//                Proxy proxy = new Proxy();
//                String jsonData = proxy.getJSON();
//
//                // DB에 JSON데이터를 저장함
//                Dao dao = new Dao(getApplicationContext());
//                dao.insertJsonData(jsonData);
//
//                //listView();
//                handler.post(new Runnable(){
//                    public void run() {
//                        listView();
//                    }
//                });
//            }
//        }.start();
//    }

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
            case R.id.activity_main_button2:
                refreshData();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ArticleView.class);
        intent.putExtra("ArticleNumber", articleList.get(position).getArticleNumber() + "");
        startActivity(intent);
    }
}

