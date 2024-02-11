package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class BalanceManager {

    public BalanceManager(){
    }
    Scanner scanner = new Scanner(System.in);
    public double getPrice(String tablename, String choice) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String buySql = "select price from " + tablename + " where indexkey= " + choice;
        ResultSet resultSet = statement.executeQuery(buySql);
        while (resultSet.next()){
            return resultSet.getDouble("price");
        }
        return 0;
    }

    public  double getBalance() throws SQLException {
        String balanceData = "select userbalance from userinfo where isRegistered = true";
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(balanceData);
        while (resultSet.next()){
            return  resultSet.getDouble("userbalance");
        }
        return 0;
    }

    public void addBalance(double amount) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String addBal = "update userinfo set userbalance = userbalance + " + amount;
        Statement statement = connection.createStatement();
        statement.executeUpdate(addBal);
        statement.close();
        connection.close();
    }

    public void balanceMenu() throws SQLException {
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("Your balance:"+getBalance());
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.Top up your balance");
        System.out.println("2.Back");
        System.out.print("Option:");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.print("Enter card number:");
            long cardNum = scanner.nextLong();
            System.out.print("Enter card expiration date MM/YY:");
            int expirationDate = scanner.nextInt();
            System.out.print("CVV:");
            int cvv = scanner.nextInt();


            System.out.print("Enter balance:");
            int amount = scanner.nextInt();

            if (amount>0){
                addBalance(amount);
                System.out.println("Balance updated");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            else{
                System.out.println("OH MANN YOU WANNA DECREASE YOUR BALANCE??? ITS NOT POSSIBLE");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }

        }
        else if(choice == 2){
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
    }
    public void pay(double price) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String paySql = "update userinfo set userbalance = userbalance - " + price;
        Statement statement = connection.createStatement();
        statement.executeUpdate(paySql);
        statement.close();
        connection.close();
        System.out.println("Successful purchase");
    }
}

