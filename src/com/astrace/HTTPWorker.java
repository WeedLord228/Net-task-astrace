package com.astrace;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPWorker {
    private final String USER_AGENT = "Mozilla/5.0";
// SendGEt Посылает гет запрос на ипинфо  возвращает только поля IP, country и номер автоомной системы с провайдером
    protected String sendGet(String ip) throws Exception {

        String url = "https://ipinfo.io/"+ ip + "/json"+"?token=9f9a1845e234fc";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            // Тут можно вместо if{...} написать сразу response.appen(inmputLine) для того, чтобы вернуть полный вывод get запроса
            if (inputLine.contains("ip") || inputLine.contains("country") || inputLine.contains("org")) { ;
                String tempstr = inputLine.replaceAll("\"","");
                String out = tempstr.split(": ")[1];
                response.append(out);
            }
        }
        in.close();

        return response.toString();
    }
}
