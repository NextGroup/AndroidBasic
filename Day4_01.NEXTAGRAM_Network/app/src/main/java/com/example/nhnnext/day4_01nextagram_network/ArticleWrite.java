package com.example.nhnnext.day4_01nextagram_network;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.provider.MediaStore;
import android.os.Handler;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArticleWrite extends ActionBarActivity implements OnClickListener{

    private EditText etWriter;
    private EditText etTitle;
    private EditText etContent;
    private ImageButton ibPhoto;
    private Button buUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_write);

        etWriter    = (EditText)    findViewById(R.id.write_article_editText_writer);
        etTitle     = (EditText)    findViewById(R.id.write_article_editText_title);
        etContent   = (EditText)    findViewById(R.id.write_article_editText_content);
        ibPhoto     = (ImageButton) findViewById(R.id.write_article_imageButton_photo);
        buUpload    = (Button)      findViewById(R.id.write_article_button_upload);

        ibPhoto.setOnClickListener(this);
        buUpload.setOnClickListener(this);
    }

    private String filePath;
    private String fileName;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if(requestCode == REQUEST_PHOTO_ALBOM) {
                Uri uri = getRealPathUri(data.getData());
                filePath = uri.toString();
                fileName = uri.getLastPathSegment();

                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                ibPhoto.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            Log.e("test", "onActivityResult ERROR:" + e);
        }
    }

    private Uri getRealPathUri(Uri uri) {
        Uri filePathUri = uri;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null,null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePathUri = Uri.parse(cursor.getString(column_index));
            }
        }
        return filePathUri;
    }

    private static final int REQUEST_PHOTO_ALBOM = 1;

    private ProgressDialog progressDialog;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.write_article_imageButton_photo:

                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PHOTO_ALBOM);
                break;

            case R.id.write_article_button_upload:

                final Handler handler = new Handler();
                progressDialog = ProgressDialog.show(ArticleWrite.this, "","업로드 중 입니다.");

                String ID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(new Date());

                Article article = new Article(
                                0,          // 게시글 번호. 서버에서 붙여주는 번호이므로 숫자는 중요하지 않음
                                etTitle.getText().toString(),   // 게시글 정보
                                etWriter.getText().toString(),  // 글쓴이 이름
                                ID,         // 글쓴이 아이디
                                etContent.getText().toString(), // 글 내용
                                DATE,       // 작성 시각
                                fileName);  // 사진 파일명

                ProxyUP.uploadArticle(article, filePath,
                        new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                Log.e("test", "up onSuccess:" + i);
                                progressDialog.cancel();
                                Toast.makeText(getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                Log.e("test", "up onFailure:" + i);
                                progressDialog.cancel();
                                Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}


// 아래 주석처리 된 코드는 flask와 LoopJ를 사용하지 않았을 때 진행하는 내용입니다.
//
//          case R.id.write_article_button_upload:
//
//              final Handler handler = new Handler();
//
//                new Thread(){
//                    public void run() {
//
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {                                 // "타이틀", "메시지"
//                                progressDialog = ProgressDialog.show(ArticleWrite.this, "","업로드 중 입니다.");
//                            }
//                        });
//
//                        String ID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                                Settings.Secure.ANDROID_ID);
//                        String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(new Date());
//                        Article article = new Article(
//                                0,          // 게시글 번호. 서버에서 붙여주는 번호이므로 숫자는 중요하지 않음
//                                etTitle.getText().toString(),   // 게시글 정보
//                                etWriter.getText().toString(),  // 글쓴이 이름
//                                ID,         // 글쓴이 아이디
//                                etContent.getText().toString(), // 글 내용
//                                DATE,       // 작성 시각
//                                fileName);  // 사진 파일명
//
//                        ProxyUP proxyUP = new ProxyUP();
//                        proxyUP.uploadArticle(article, filePath);
//
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressDialog.cancel();
//                                finish();
//                            }
//                        });
//                    }
//                }.start();
//                break;
//        }
//    }
//}
