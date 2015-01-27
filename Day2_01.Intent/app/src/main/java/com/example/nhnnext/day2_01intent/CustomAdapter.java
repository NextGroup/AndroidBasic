package com.example.nhnnext.day2_01intent;

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

public class CustomAdapter extends ArrayAdapter<ListData>{
        private Context context;
        private int layoutResourceId;
        private ArrayList<ListData> listData;

        public CustomAdapter(Context context, int layoutResourceId, ArrayList<ListData> listData){
            super(context, layoutResourceId, listData);
            this.context = context;
            this.layoutResourceId = layoutResourceId;
            this.listData = listData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        // row.findViewById로 row안의 레이아웃을 구성합니다.
        TextView tvText1 = (TextView) row.findViewById(R.id.custom_row_textView1);
        TextView tvText2 = (TextView) row.findViewById(R.id.custom_row_textView2);

        // position은 listData 리스트의 순서값(index)입니다.
        // listData(어레이리스트)에서 ListData(객체)를 가져와
        // get으로 순서값을 불러온 후 setText 해줍니다.
        tvText1.setText(listData.get(position).getText1());
        tvText2.setText(listData.get(position).getText2());

        ImageView imageView = (ImageView) row.findViewById(R.id.custom_row_imageView1);

        // 이미지를 읽어와 리스트에 표시하기 위해
        // 이번 예제에서는 assets 폴더에 사진을 넣어두고 불러오는 방법을 이용합니다.
        try {
            // 이미지 파일의 이름을 불러옵니다.
            InputStream is = context.getAssets().open(listData.get(position).getImgName());
            // 이미지를 불러와 Drawable로 만들고
            Drawable d = Drawable.createFromStream(is, null);
            // 이미지뷰에 표시합니다.
            imageView.setImageDrawable(d);
        } catch(IOException e){
            Log.e("ERROR", "ERROR: " + e);
        }
        return row;
    }
}











