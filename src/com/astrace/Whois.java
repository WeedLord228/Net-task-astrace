package com.astrace;
import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Whois {
    public static String getWhoisRipe(String address) throws Exception {
        int c;
        StringBuilder tempRes = new StringBuilder();

        try (Socket s = new Socket("whois.ripe.net", 43)) {

            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            String query = ("-B " + address + "\n");

            byte buf[] = query.getBytes();

            out.write(buf);

            while ((c = in.read()) != -1) {
                tempRes.append((char) c);
            }
        }

        String queryResult = tempRes.toString();
        if (queryResult.contains("IPv4 address block not managed by the RIPE NCC"))
            return "-1";

        return RegexWorker.findAS(queryResult);
    }

    public static String getWhoisArin(String address) throws Exception {
        int c;
        StringBuilder tempRes = new StringBuilder();

        try (Socket s = new Socket("whois.arin.net", 43)) {

            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            String query = ("z + " + address + "\n");
//            String query = (address + "\n");


            byte buf[] = query.getBytes();

            out.write(buf);

            while ((c = in.read()) != -1) {
                tempRes.append((char) c);
            }
        }

        String queryResult = tempRes.toString();
        if (queryResult.contains("These addresses have been further assigned to users in") || queryResult.contains("These addresses are in use by many millions")
                || queryResult.contains("APNIC") || queryResult.contains("AfriNIC") || queryResult.contains("LACNIC"))
            return "-1";

        return RegexWorker.findAS(queryResult);
    }
}