package com.example.nhnnext.day4_01nextagram_network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.File;
import java.io.FileNotFoundException;

public class ProxyUP {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void uploadArticle(Article article, String filePath, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("title", article.getTitle());
        params.put("writer", article.getTitle());
        params.put("id", article.getTitle());
        params.put("content", article.getTitle());
        params.put("writeDate", article.getTitle());
        params.put("imgName", article.getTitle());

        try {
            params.put("uploadedfile", new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        client.post("http://127.0.0.1:5009/upload", params, responseHandler);
    }
}

// 아래 주석처리 된 코드는 flask와 LoopJ를 사용하지 않았을 때 진행하는 내용입니다.
//
//    private String lineEnd = "\r\n";
//    private String twoHyphens = "--";
//    private String boundary = "*****";
//
//    public String uploadArticle(Article article, String filePath) {
//
//        // 안드로이드 베이직 수업에서 자세히 다룰 내용입니다.
//        // 네트워크 선행이 필요한 이유입니다.
//
//        try {
//
//            FileInputStream fis = new FileInputStream(filePath);
//
//            URL url = new URL("127.0.0.1:5009/upload");
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestProperty("Accept-Charset", "UTF-8");
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Cache-Control", "no-cache");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//
//            //응답 데이터가 많을 때에는 일정양 씩 묶어서 보내겠다는 의미
//            conn.setRequestProperty("Transfer-Encoding", "chunked");
//
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//
//            //Content는 multipart형식이고 데이터의 끝을 boundary(*****)로 표시를 하겠다.(content-length이 없으므로...)
//            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//
//            // write data
//            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//
//            dos.write(getPostData("title", article.getTitle()).getBytes("UTF-8"));
//            dos.write(getPostData("writer", article.getWriter()).getBytes("UTF-8"));
//            dos.write(getPostData("id", article.getId()).getBytes("UTF-8"));
//            dos.write(getPostData("content", article.getContent()).getBytes("UTF-8"));
//            dos.write(getPostData("writeDate", article.getWriteDate()).getBytes("UTF-8"));
//            dos.write(getPostData("imgName", article.getImgName()).getBytes("UTF-8"));
//
//            dos.writeBytes(twoHyphens + boundary + lineEnd);
//            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
//                    + article.getImgName() + "\"" + lineEnd);
//            dos.writeBytes(lineEnd);
//
//            int bytesAvailable = fis.available();
//            int maxBufferSize = 1024;
//
//            // 버퍼사이즈 조정
//            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//
//            byte[] buffer = new byte[bufferSize];
//            int bytesRead = fis.read(buffer, 0, bufferSize);
//
//            // read image
//            while (bytesRead > 0) {
//                dos.write(buffer, 0, bufferSize);
//                bytesAvailable = fis.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead = fis.read(buffer, 0, bufferSize);
//            }
//
//            dos.writeBytes(lineEnd);
//            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//            // close streams
//            Log.i("Test", "File is written");
//
//            fis.close();
//            dos.flush();
//            dos.close();
//
//            int status = conn.getResponseCode();
//            Log.i("test", "statusUP:" + status);
//
//            switch (status) {
//                case 200:
//                case 201:
//                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuilder sb = new StringBuilder();
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line + "\n");
//                    }
//                    br.close();
//                    return sb.toString();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i("test", "UPLOAD ERROR:" + e);
//        }
//        return null;
//    }
//
//
//    // post방식에 맞게 데이터 형식을 추가
//    private String getPostData(String key, String value) {
//        String result = twoHyphens + boundary + lineEnd;
//        result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
//        result += lineEnd;
//        result += value;
//        result += lineEnd;
//        return result;
//    }
//}