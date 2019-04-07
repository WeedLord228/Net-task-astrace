package com.astrace;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class Main {

    public static void main(String[] args) throws Exception {
        String ip_domain = "jockbutt.com";
        HTTPWorker http = new HTTPWorker();
        String fileName = "out.txt";
        String out = "";
        StringBuilder outBuilder = new StringBuilder();

        System.out.println("Результат работы getIpList");
        for (String s : getIpList(ip_domain))
        {
            outBuilder.append(http.sendGet(s).replaceAll(","," ")+ "\n");
            //System.out.println
            //outBuilder.append(String.format("%-30s%-20s%-25s%n",tempArr[0],tempArr[1],tempArr[2]));
        }
        out = outBuilder.toString();
        System.out.println(out);
        Writer.write(out, fileName);
    }

    public static List<String> getIpList(String ip_domain) {

        BufferedReader in;

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("tracert " + ip_domain);

            in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            String a = "";
            String regexp = "([0-9]{1,3}[\\.]){3}[0-9]{1,3}";
            List<String> allMatches = new ArrayList<String>();
            Pattern pattern = Pattern.compile(regexp);
            int i = 0;

            if (p == null)
                System.out.println("could not connect");

            while ((line = in.readLine()) != null) {
                //a += Arrays.toString(line.split("([0-9]{1,3}[\\.]){3}[0-9]{1,3}")) + "\n";
                //FileWrite.WriteFile(line);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    allMatches.add(matcher.group());
                    //a += matcher.group() + "\n";
                }
                //in.close();
            }
            allMatches.remove(0);
            return allMatches;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

