package com.astrace;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWorker {
    public static String findAS (String input)
    {
        String regex = "(as[0-9]+)|(AS[0-9]+)";
        List<String> resList = new LinkedList<String>();

        Pattern pattern = Pattern.compile(regex);
        Matcher mathcer = pattern.matcher(input);

        if (mathcer.find()) {
            while (mathcer.find(mathcer.start() + 1)) {
                resList.add(mathcer.group());
            }
        }
        if (resList.isEmpty()) return "";
        return ((LinkedList<String>) resList).getFirst();
    }
}
