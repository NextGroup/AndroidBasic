package com.example.nhnnext.day4_01nextagram_network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;

public class ArticleView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        TextView tvTitle = (TextView) findViewById(R.id.activity_article_view_textView_title);
        TextView tvWriter = (TextView) findViewById(R.id.activity_article_view_textView_writer);
        TextView tvContent = (TextView) findViewById(R.id.activity_article_view_textView_content);
        TextView tvWriteDate = (TextView) findViewById(R.id.activity_article_view_textView_time);
        ImageView ivImage = (ImageView) findViewById(R.id.activity_article_view_imageView_photo);

        String articleNumber = getIntent().getExtras().getString("ArticleNumber");

        // Dao 초기화
        Dao dao = new Dao(getApplicationContext());
        // Dao의 데이터 타입이 int이므로 String을 int로 반환
        Article article = dao.getArticleByArticleNumber(Integer.parseInt(articleNumber));

        tvTitle.setText(article.getTitle());
        tvWriter.setText(article.getWriter());
        tvContent.setText(article.getContent());
        tvWriteDate.setText(article.getWriteDate());

        String img_path = getApplicationContext().getFilesDir().getPath() + "/" + article.getImgName();
        File img_load_path = new File(img_path);

        if(img_load_path.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(img_path);
            ivImage.setImageBitmap(bitmap);
        }
    }
}