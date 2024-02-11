package Controllers;

import java.sql.*;
import java.util.Scanner;
import Database.DBController;
import ModelsEntities.BalanceManager;
import Repositories.DeliveryRepository;
import Helpers.Checker;
import Utilities.DatabaseUtil;

public class Cartcontroller extends DBController {
    Scanner scanner = new Scanner(System.in);

    BalanceManager balanceManager = new BalanceManager();
    DeliveryRepository deliveryRepository = new DeliveryRepository();
    Checker checker = new Checker();


    public void cartMenu() throws SQLException {
        System.out.println("Your Cart:");
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Select * from cart " ;
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("|Model                 Quantity    price       Indexkey     Detail             |");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        while (resultSet.next()) {
            int colCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                String formattedValue = "";
                switch (metaData.getColumnType(i)) {
                    case java.sql.Types.INTEGER:
                        if (i == colCount) {
                            formattedValue = String.format("%10d|", resultSet.getInt(i));
                        } else {
                            formattedValue = String.format("%10d  |", resultSet.getInt(i));
                        }
                        break;
                    case java.sql.Types.DOUBLE:
                        formattedValue = String.format("%8.2f  |", resultSet.getDouble(i));
                        break;
                    default:
                        if (i == 1) {
                            formattedValue = String.format("|%-20s|", resultSet.getString(i));
                        }
                        else if(i==5){
                            formattedValue = String.format("%-20s|", resultSet.getString(i)) ;
                        }
                        else {
                            formattedValue = String.format("%-20s|", resultSet.getString(i));
                        }
                        break;
                }
                System.out.print(formattedValue);
            }
            System.out.println();
            System.out.println("————————————————————————————————————————————————————————————————————————————————");


        }
        totalMenu();

    }
    public void totalMenu() throws SQLException {
        double total = getTotalPrice();
        if(total>0){
            System.out.println("Total price");
            System.out.println(total);
            System.out.print("Your balance:");
            System.out.println(balanceManager.getBalance());
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("1.Purchase");
            System.out.println("2.Delete a detail");
            System.out.println("3.Back");

            System.out.print("Option:"); int choiceMain = scanner.nextInt();
            System.out.println("————————————————————————————————————————————————————————————————————————————————");

            if(choiceMain == 1){
                System.out.print("Are you sure you want to order this?y/n:");
                String choice = scanner.next();

                if (choice.equals("y")){
                    if (deliveryRepository.deliveryMethod()){
                        if(checker.checkDelivery()){
                            total+=2000;
                            if(balanceManager.getBalance()>=total){
                                System.out.print("Total:");
                                System.out.println(total);
                                balanceManager.pay(total);

                                System.out.println("Successful purchase");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                                afterPurchase();
                            }
                            else{
                                System.out.println("You dont have enough money :( Top up your balance");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                            }
                        }
                        else{
                            System.out.println("You arent registered your delivery.Please go back and register");
                            System.out.println("————————————————————————————————————————————————————————————————————————————————");
                        }
                    }
                    else{
                        if(checker.checkDelivery()){
                            if(balanceManager.getBalance()>=total){
                                balanceManager.pay(total);

                                System.out.println("Successful purchase");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                                afterPurchase();
                            }
                            else{
                                System.out.println("You dont have enough money :( Top up your balance");
                                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                            }
                        }
                        else{
                            System.out.println("You arent registered your delivery.Please go back and register");
                            System.out.println("————————————————————————————————————————————————————————————————————————————————");
                        }
                    }

                }
                else{
                    totalMenu();
                }
            }
            else if(choiceMain == 2){
                System.out.print("Enter indexkey to delete:");
                int key = scanner.nextInt();
                deleteDetail(key);
            }



        }
        else{
            System.out.println("Your cart is empty");
            System.out.println();
            System.out.println();

        }
    }
    public double getTotalPrice() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String getSumSql = "select sum(priceincart) from cart";
        ResultSet resultSet = statement.executeQuery(getSumSql);
        if (resultSet.next()){
            return resultSet.getDouble("sum");
        }
        return 0;
    }

    public void deleteDetail(int key) throws SQLException {
        String deleteSql = "delete from cart where indexkeyincart = ?";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1,key);
        int affected = preparedStatement.executeUpdate();
        if(affected>0){
            System.out.println("Successfully deleted");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        else {
            System.out.println("An entry with this indexkey was not found.Try again");
            totalMenu();
            System.out.println();
            System.out.println();
        }
    }





}
