package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    static String DataBase = "/home/n3m0/IdeaProjects/com.nazarenko/DataBase.xlsx";
    static String SalesList = "/home/n3m0/IdeaProjects/com.nazarenko/SalesList.xlsx";
    public static void main(String[] args) throws Exception {
        if(test()==false)return;
        Menu.Menu1();
    }
    public static boolean test() throws Exception
    {
        File File1 = new File(Main.DataBase);
        FileInputStream fis1 = null;
        try{fis1 = new FileInputStream(File1);}
        catch(FileNotFoundException e){ System.out.println("File not found or files name has been generated randomly.");return false;}
        fis1.close();

        File File2 = new File(Main.SalesList);
        FileInputStream fis2 = null;
        try{fis2 = new FileInputStream(File2);}
        catch(FileNotFoundException e){ System.out.println("File not found. Maybe it was stolen by a ninja.");return false;}
        fis2.close();
        return true;
    }

}