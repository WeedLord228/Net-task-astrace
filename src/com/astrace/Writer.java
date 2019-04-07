package com.astrace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
    public static void write(String text,String filename)
    {
    try(FileOutputStream fos=new FileOutputStream(filename))
    {
        // перевод строки в байты
        byte[] buffer = text.getBytes();

        fos.write(buffer, 0, buffer.length);
    }
        catch(IOException ex){

        System.out.println(ex.getMessage());
    }
        System.out.println("The file has been written");
}
}
