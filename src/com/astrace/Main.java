package com.astrace;

import com.sun.source.tree.WhileLoopTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

    public static void main(String[] args) throws Exception {
//        String ip_domain = args[0];
//        String fileName = "out.txt";
//        String out = "";
//        StringBuilder outBuilder = new StringBuilder();
//        System.out.println("Выполняю трассировку до " + ip_domain +". Это займет всего пару десятков секунд.");
//        for (String s : getIpList(ip_domain))
//        {
//            String[] tempArr = http.sendGet(s).split(",");
//            if(tempArr.length == 1)
//                outBuilder.append(String.format("%-30s%n",tempArr[0]));
//            if(tempArr.length == 2)
//                outBuilder.append(String.format("%-30s%-10s%n",tempArr[0],tempArr[1]));
//            if (tempArr.length == 3)
//                outBuilder.append(String.format("%-30s%-10s%-30s%n",tempArr[0],tempArr[1],tempArr[2]));
//        }
//        out = outBuilder.toString();
//        System.out.printf("%-30s%-10s%-30s%n","ip address","Страна","Номер автономной системы и провайдер");
//        System.out.println("-------------------------------------------------------------------------------------");
//        System.out.println(out);
//        Whois.getWhoisInfo();

//        Scanner sc = new Scanner(System.in);
        String address = "";
        String ripeAnswer = Whois.getWhoisRipe(address);
        String arinAnser = Whois.getWhoisArin(address);
        if ( ripeAnswer == "-1") {
            if (arinAnser == "-1")
                System.out.println("Не нашел");
            else System.out.println(arinAnser);
        }
        else System.out.println(ripeAnswer);

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
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    allMatches.add(matcher.group());
                }
            }
            allMatches.remove(0);
            return allMatches;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

