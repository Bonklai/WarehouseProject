package Controllers;

import java.sql.*;
import java.util.Scanner;
import Database.DBController;

public class CatalogController extends DBController {
    public CatalogController(){}
    Scanner scanner = new Scanner(System.in);


    public void startCatalog() throws SQLException {
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.Batteries for iPhone");
        System.out.println("2.Display for iPhone");
        System.out.println("3.Glass for iPhone");
        System.out.println("4.Touch glass for iPad");
        System.out.println("5.Display for iPad");
        System.out.println("6.Battery for iPad");
        System.out.println("7.Battery for Mac");
        System.out.println("8.Matrices for Mac");
        System.out.println("9.Keyboards for Mac");
        System.out.print("Option:");
        int option = scanner.nextInt();


        if(option == 1) {

            printTable("batteriesforiphone");

        }
        else if(option==2){
            printTable("displayforiphone");
        }
        else if(option == 3){
            printTable("glassforiphone3in1");
        }
        else if(option == 4){
            printTable("touchglassipad");
        }
        else if(option == 5){
            printTable("displayonipad");
        }
        else if(option == 6){
            printTable("batteryforipad");
        }
        else if(option == 7){
            printTable("batteryformacbook");
        }
        else if(option == 8){
            printTable("matricesformacbook");
        }
        else if(option == 9){
            printTable("keyboardsformacbook");
        }
    }
}


