package Repositories;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeliveryRepository {
    Scanner scanner = new Scanner(System.in);

    public void reg() throws SQLException {
        System.out.println("Please add your city , address , Post Index");
        System.out.println();
        System.out.print("City:");
        String city = scanner.next();
        System.out.println();
        System.out.print("Address:");
        String address = scanner.next();
        System.out.println();
        System.out.print("Post Index:");
        int postindex = scanner.nextInt();
        String sql = "Update userinfo set city = ? , address=? , postindex = ? , regdelivery = true where isregistered = ?"; ;
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,city);
        preparedStatement.setString(2,address);
        preparedStatement.setInt(3,postindex);
        preparedStatement.setBoolean(4,true);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public  void printDeliveryData() throws SQLException {
        String sql3 = "select city,address,postindex from userinfo ";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()){

            String city = resultSet3.getString("city");
            String address = resultSet3.getString("address");
            int postindex = resultSet3.getInt("postindex");

            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("|City:"+city);
            System.out.println("|Address:"+address);
            System.out.println("|PostIndex:"+ postindex);
            System.out.println("————————————————————————————————————————————————————————————————————————————————");

        }
    }
    public boolean deliveryMethod(){
        System.out.println("Please choose the shipping method:\n" +
                "1.Home delivery . Inside the city +2000\n" +
                "2. Delivery to KazPost. Free shipping");
        System.out.print("Option:");
        int choiceDelivery = scanner.nextInt();
        if(choiceDelivery==1) return true;
        else return false;
    }
}
