package Applications;

import Applications.ApplicationMain;
import ModelsEntities.UserRepository;

import java.sql.*;
import java.util.Scanner;
import Helpers.Checker;

public class Application extends ApplicationMain {

    UserRepository reg = new UserRepository();


    Scanner scanner = new Scanner(System.in);

    public void start() throws SQLException {
        Checker temp = new Checker();
        while(true){
            if (!temp.checkRegister()){
                System.out.println("You arent registered , please register");
                System.out.println("-----------------------------------------------");
                System.out.print("Exit/Register:");
                String exit = scanner.next();
                if( exit.equalsIgnoreCase("Exit")) break;
                reg.reg();
                if (startAppMain()) break;}
            else {
                reg.login();
                if (startAppMain()) break;

            }
        }

    }
}


