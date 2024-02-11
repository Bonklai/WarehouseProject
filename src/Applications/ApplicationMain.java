package Applications;

import Controllers.CatalogController;
import ModelsEntities.BalanceManager;
import ModelsEntities.UserRepository;
import Database.DBController;
import Controllers.Cartcontroller;

import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationMain extends DBController {
    UserRepository userRepository = new UserRepository();
    Scanner scanner = new Scanner(System.in);
    BalanceManager balanceManager = new BalanceManager();
    CatalogController catalogController = new CatalogController();
    Cartcontroller cartcontroller = new Cartcontroller();
    DeliveryApplication deliveryApplication = new DeliveryApplication();


    public boolean startAppMain() throws SQLException {
        while (true) {
            System.out.println("1.Profile");
            System.out.println("2.Balance");
            System.out.println("3.Catalog");
            System.out.println("4.Cart");
            System.out.println("5.Change delivery address");
            System.out.println("6.Exit");

            System.out.println("0.Delete user");
            System.out.print("Enter option:");


            int userChoice = scanner.nextInt();
            if (userChoice == 0) {
                userRepository.deleteUser();
                break;
            }
            else if (userChoice == 1){
                userRepository.printProfile();
            }
            else if (userChoice == 2){
                balanceManager.balanceMenu();
            }
            else if (userChoice == 3){
                catalogController.startCatalog();
            }
            else if(userChoice == 6){
                return true;
            } else if (userChoice == 4){
                cartcontroller.cartMenu();
            }
            else if(userChoice == 5){
                deliveryApplication.startApp();
            }
            else{
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                System.out.println("Wrong option. Try again");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
        }
        return false;
    }


}

