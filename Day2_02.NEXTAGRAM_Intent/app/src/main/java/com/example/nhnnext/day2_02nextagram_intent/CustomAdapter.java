package com.example.nhnnext.day2_02nextagram_intent;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Article> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Article> articleData;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Article> articleData) {
        super(context, layoutResourceId, articleData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.articleData = articleData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        // row.findViewById로 row안의 레이아웃을 구성합니다.
        TextView tvTitle = (TextView) row.findViewById(R.id.custom_row_textView1);
        TextView tvContent = (TextView) row.findViewById(R.id.custom_row_textView2);

        // position은 articleData 리스트의 순서값(index)입니다.
        // articleData(어레이리스트)에서 ListData(객체)를 가져와
        // get으로 순서값을 불러온 후 setText 해줍니다.
        tvTitle.setText(articleData.get(position).getTitle());
        tvContent.setText(articleData.get(position).getContent());

        ImageView imageView = (ImageView) row.findViewById(R.id.custom_row_imageView);

        // 이미지를 읽어와 리스트에 표시하기 위해
        // 이번 예제에서는 assets 폴더에 사진을 넣어두고 불러오는 방법을 이용합니다.
        try {
            // 이미지 파일의 이름을 불러옵니다.
            InputStream is = context.getAssets().open(articleData.get(position).getImgName());
            // 이미지를 불러와 Drawable로 만들고
            Drawable d = Drawable.createFromStream(is, null);
            // 이미지뷰에 표시합니다.
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            Log.e("ERROR", "ERROR: " + e);
        }
        return row;
    }
}











