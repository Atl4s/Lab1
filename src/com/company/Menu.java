package com.company;

import java.util.Scanner;

public class Menu{
    public static void Menu1() throws Exception{
        while(true)
        {
            System.out.println("write 1 to show product sheet \nwrite 2 to order products \nwrite 3 to show sales list \n 9 to exit\n");
            int a = Selling.ScanInt();
            switch (a) {
                case 1: {
                    Selling.Data();
                    break;
                }
                case 2: {
                    Order.NewOrder();
                    break;
                }
                case 3: {
                    Sales.List();
                    break;
                }
                case 9: {
                    return;
                }
                default:
            }
        }
    }
}
