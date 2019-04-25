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
//        String ip_domain = args[0];,000
,000
        String domainAddress = "twitch.tv";,000
        List<String> ipArray = getIpList(domainAddress);,000
,000
        System.out.printf("%-30s%-10s%n","ip address","Номер AS");,000
        System.out.println("---------------------------------------------");,000
        for (String str : ipArray) {,000
            String address = str;,000
            String ripeAnswer = Whois.getWhoisRipe(address);,000
            String arinAnswer = Whois.getWhoisArin(address);,000
            if (ripeAnswer == "-1") {,000
                if (arinAnswer == "-1") {
                    arinAnswer = "";
                    ripeAnswer = "";
                    System.out.printf("%-30s%-10s%n",address,"");
                }
               else System.out.printf("%-30s%-10s%n",address,arinAnswer);
            } else System.out.printf("%-30s%-10s%n",address,ripeAnswer);
        }
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

