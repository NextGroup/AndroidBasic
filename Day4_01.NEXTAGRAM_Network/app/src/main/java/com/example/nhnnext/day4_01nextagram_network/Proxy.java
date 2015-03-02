package com.example.nhnnext.day4_01nextagram_network;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy {

    public String getJSON() {

        try {
            // JSON 데이터를 출력하는 페이지 주소를 입력합니다.
            URL url = new URL("http://10.73.44.93/~stu01/loadData.php");

            // HttpURLConnection로 url의 주소를 연결합니다.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 서버 접속시의 Time out(ms)
            conn.setConnectTimeout(10 * 1000);
            // Read시의 Time out(ms)
            conn.setConnectTimeout(10 * 1000);

            // 요청 방식 선택
            conn.setRequestMethod("GET");
            // 연결을 지속하도록 함
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 캐릭터셋을 UTF-8로 요청
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            // 캐시된 데이터를 사용하지 않고 매번 서버로부터 다시 받음
            conn.setRequestProperty("Cache-Control", "no-cache");
            // 서버로부터 JSON형식의 타입으로 데이터 요청
            conn.setRequestProperty("Accept-Charset", "application/json");

            // InputStream으로 서버로부터 응답을 받겠다는 옵션
            conn.setDoInput(true);

            conn.connect();

            int status = conn.getResponseCode();
            Log.i("test", "ProxyResponseCode:" + status);

            switch (status) {
                // 정상 연결 상태
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("test", "NETWORK ERROR:" + e);
        }
        return null;
    }
}
