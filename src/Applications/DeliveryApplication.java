package Applications;

import Repositories.DeliveryRepository;

import java.sql.SQLException;
import java.util.Scanner;
import Helpers.Checker;
import ModelsEntities.User;


public class DeliveryApplication extends User {
    Checker checker = new Checker();
    Scanner scanner = new Scanner(System.in);
    DeliveryRepository deliveryRepository = new DeliveryRepository();

    public void startApp() throws SQLException {
        if(!checker.checkDelivery()){
            System.out.println("Please register delivery");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            deliveryRepository.reg();
        }
        else{
            deliveryRepository.printDeliveryData();
            System.out.print("You have already registered your delivery. Do you want edit? y/n:");
            String edit = scanner.next();
            if(edit.equals("y")){
                deliveryRepository.reg();
                System.out.println("Your data has been updated");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            else{
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
        }
    }
}
