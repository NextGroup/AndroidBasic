package com.example.taejun.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private TextView textView1;
    private EditText editText1;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(this);

        textView1 = (TextView) findViewById(R.id.textView1);
        editText1 = (EditText) findViewById(R.id.editText1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(getApplicationContext(), "버튼을 터치하셨습니다.", Toast.LENGTH_SHORT).show();
                textView1.setText("입력하신 내용은 " + editText1.getText().toString() + " 입니다.");
                imageView1.setImageResource(R.drawable.gyunbin);
                break;
            case R.id.imageButton1:
                Toast.makeText(getApplicationContext(), "이미지 버튼을 터치하셨습니다.", Toast.LENGTH_SHORT).show();
                textView1.setText("입력하신 내용은 " + editText1.getText().toString() + " 입니다.");
                imageView1.setImageResource(R.drawable.singer);
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
