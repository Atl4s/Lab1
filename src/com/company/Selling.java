package com.company;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Selling {
    public static void Data() throws Exception {
        File myFile = new File(Main.DataBase);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "|    \t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "|    \t");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "|    \t");
                            break;
                        default:
                    }
                }
                System.out.println("");
            }
            System.out.println("Do you wanna sell something?\n0 if you don't");
            int sw = ScanInt();
            if(sw == 0){
                fis.close();
                Menu.Menu1();
            }
            System.out.println("write ID");
            int ID = ScanInt();
            Iterator<Row> rowIterator1 = mySheet.iterator();
            Row row1 = rowIterator1.next();
            while (rowIterator1.hasNext()) {
                row1 = rowIterator1.next();
                Cell cell = row1.getCell(0);
                if (ID == cell.getNumericCellValue()) {
                    Cell cellN = row1.getCell(1);
                    System.out.print(cellN.getStringCellValue() + ":\t");
                    System.out.println("Product exists\n\nhow much you want to sell?\n");
                    int Quantity = ScanInt();
                    if (Quantity <= row1.getCell(3).getNumericCellValue()) {
                        row1.getCell(3).setCellValue(row1.getCell(3).getNumericCellValue() - Quantity);
                        Sale(row1.getCell(0).getNumericCellValue(),row1.getCell(1).getStringCellValue(),row1.getCell(2).getNumericCellValue(),Quantity);
                        FileOutputStream fos = new FileOutputStream(new File(Main.DataBase));
                        myWorkBook.write(fos);
                        fos.close();
                        System.out.println("Done");
                    } else System.out.println("No amount\n");
                    break;
                }
            }
        fis.close();
        }
    public static void Sale(double ID, String name, double cost, int quantity) throws Exception
    {
        int i = 0;
        File myFile = new File(Main.SalesList);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ++i;
            if (rowIterator.hasNext()==false) {
                row = mySheet.createRow(i);
                row.createCell(0).setCellValue(ID);
                row.createCell(1).setCellValue(name);
                row.createCell(2).setCellValue(cost);
                row.createCell(3).setCellValue(quantity);
                row.createCell(4).setCellValue(quantity*cost);
                FileOutputStream fos = new FileOutputStream(new File(Main.SalesList));
                myWorkBook.write(fos);
                fos.close();
            }

        }
        fis.close();
    }

    public static int ScanInt()
    {
        Scanner in = new Scanner(System.in);
        int value = in.nextInt();
        return value;

    }
}
