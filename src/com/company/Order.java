package com.company;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Order{
    public static void NewOrder() throws Exception
    {
        int i = 0;
        File myFile = new File(Main.DataBase);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        System.out.println("Give me ID.");
        int ID = Selling.ScanInt();
        Iterator<Row> rowIterator = mySheet.iterator();
        Row row = rowIterator.next();
        while (rowIterator.hasNext()) {
            ++i;
            row = rowIterator.next();
            Cell cell = row.getCell(0);
            if (ID == cell.getNumericCellValue()) {
                System.out.print(row.getCell(1).getStringCellValue() + ":\t");
                System.out.println("How much u want?");
                int Qn = Selling.ScanInt();
                row.getCell(3).setCellValue(row.getCell(3).getNumericCellValue() + Qn);
                FileOutputStream fos = null;
                fos = new FileOutputStream(new File(Main.DataBase));
                myWorkBook.write(fos);
                fos.close();
                System.out.println("Done.");
                break;
            }
            else if (rowIterator.hasNext()==false)
            {
                System.out.println("Unknown ID");
                row = mySheet.createRow(i);
                System.out.println("what product is it?");
                Scanner name = new Scanner(System.in);
                String Name = name.nextLine();
                System.out.println("How much it costs?");
                Scanner cost = new Scanner(System.in);
                double Cost = cost.nextDouble();
                System.out.println("How much u want?");
                Scanner qnt = new Scanner(System.in);
                int Qnt = qnt.nextInt();
                row.createCell(0).setCellValue(ID);
                row.createCell(1).setCellValue(Name);
                row.createCell(2).setCellValue(Cost);
                row.createCell(3).setCellValue(Qnt);
                FileOutputStream fos = new FileOutputStream(new File(Main.DataBase));
                myWorkBook.write(fos);
                fos.close();
                System.out.println("Order completed");
                break;
            }
        }
        fis.close();
    }

}
