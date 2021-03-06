package com.example.nhnnext.day4_01nextagram_network;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import org.apache.http.Header;
import java.io.File;

public class FileDownloader {
    private final Context context;

    public FileDownloader(Context context) {
        this.context = context;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();
    public void downFile(String fileUrl, String fileName) {
        final File filePath = new File(context.getFilesDir().getPath() + "/" + fileName);

        if(!filePath.exists()) {
            client.get(fileUrl, new FileAsyncHttpResponseHandler(context) {
                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, File file) {

                }

                @Override
                public void onSuccess(int i, Header[] headers, File file) {
                    Log.i("test", "responsePath: " + file.getAbsolutePath());
                    Log.i("test", "originalPath: " + filePath.getAbsolutePath());
                    file.renameTo(filePath);
                }
            });
        }
    }
}

// 아래 주석처리 된 코드는 flask와 LoopJ를 사용하지 않았을 때 진행하는 내용입니다.
//
//    public void downFile(String fileUrl, String fileName) {
//        File filePath = new File(context.getFilesDir().getPath() + "/" + fileName);
//        // 파일이 이미 존재하는 경우 다운로드를 진행하지 않는다.
//        if (!filePath.exists()) {
//            try {
//                // URL에는 서버의 파일경로를 입력합니다.
//                URL url = new URL(fileUrl);
//
//                // HttpURLConnection로 url의 주소를 연결합니다.
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                // 서버 접속시의 Time out(ms)
//                conn.setConnectTimeout(10 * 1000);
//                // Read시의 Time out(ms)
//                conn.setConnectTimeout(10 * 1000);
//
//                // 요청 방식 선택
//                conn.setRequestMethod("GET");
//                // 연결을 지속하도록 함
//                conn.setRequestProperty("Connection", "Keep-Alive");
//                // 캐릭터셋을 UTF-8로 요청
//                conn.setRequestProperty("Accept-Charset", "UTF-8");
//
//                // 캐시된 데이터를 사용하지 않고 매번 서버로부터 다시 받음
//                conn.setRequestProperty("Cache-Control", "no-cache");
//                // 서버로부터 형식에 상관없이 아무 데이터나 모조리 요청
//                conn.setRequestProperty("Accept-Charset", "application/json");
//
//                // InputStream으로 서버로부터 응답을 받겠다는 옵션
//                conn.setDoInput(true);
//
//                conn.connect();
//                int status = conn.getResponseCode();
//
//                switch (status) {
//                    // 정상 연결 상태
//                    case 200:
//                    case 201:
//                        InputStream is = conn.getInputStream();
//
//                        BufferedInputStream bis = new BufferedInputStream(is);
//                        ByteArrayBuffer baf = new ByteArrayBuffer(50);
//
//                        int current = 0;
//
//                        while((current = bis.read()) != -1) {
//                            baf.append((byte) current);
//                        }
//
//                        FileOutputStream fos = context.openFileOutput(fileName, 0);
//                        fos.write(baf.toByteArray());
//
//                        fos.close();
//                        bis.close();
//                        is.close();
//                }
//            } catch (Exception e) {
//                Log.e("test", "Fiel Download Error:" + e);
//            }
//        }
//    }
//}