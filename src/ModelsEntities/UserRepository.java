package ModelsEntities;

import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class UserRepository extends User {

    Scanner scanner = new Scanner(System.in);

    public void reg() throws SQLException {

        System.out.print("Name:");
        setName(scanner.next());

        System.out.print("Surname:");
        setSurname(scanner.next());
        System.out.print("Age:");
        setAge(scanner.nextInt());
        System.out.print("Email:");
        setEmail(scanner.next());
        System.out.print("Password:");
        setPassword(scanner.next());
        String sql1 = "INSERT INTO userinfo (username , surname ,age, isregistered,useremail,userpass,userbalance) VALUES (?,?,?,?,?,?,0)";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.setString(1, getName());
        preparedStatement.setString(2, getSurname());
        preparedStatement.setInt(3, getAge());
        preparedStatement.setBoolean(4, true);
        preparedStatement.setString(5, getEmail());
        preparedStatement.setString(6, getPassword());
        setBalance(0.0);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("User created successfully");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        } else {
            System.out.println("User didn't created");
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        connection.close();
    }


    public void deleteUser() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql2 = "Truncate table userinfo";
        Statement statement = conn.createStatement();
        statement.execute(sql2);

        String sql3 = "Truncate table cart";
        statement.execute(sql3);
        conn.close();
        statement.close();
    }

    public void login() throws SQLException {



        System.out.println("Please login  | or create new account");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
        String response1 = scanner.next();
        if (response1.equals("login")) {
            while (true) {
                Connection connection = DatabaseUtil.getConnection();
                String sql2 = "SELECT useremail, userpass FROM userinfo WHERE isregistered = true";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql2);
                System.out.print("Email:");
                String emailInput = scanner.next();

                System.out.print("Password:");
                String passwordInput = scanner.next();
                boolean loginSuccess = false;
                while (resultSet.next()) {
                    String em = resultSet.getString("useremail");
                    String pass = resultSet.getString("userpass");
                    if (emailInput.equals(em) && passwordInput.equals(pass)) {
                        loginSuccess = true;
                        break;
                    }

                }
                if (loginSuccess) {
                    System.out.println("Login successful");
                    System.out.println("————————————————————————————————————————————————————————————————————————————————");
                    break;}
                else {
                    System.out.println("Login failed, try again");
                }
            }
        }
        else{
            deleteUser();
            reg();
        }

    }
    public void printProfile() throws SQLException {
        String sql3 = "select * from userinfo ";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()){
            String name = resultSet3.getString("username");
            String surname = resultSet3.getString("surname");
            String email = resultSet3.getString("useremail");
            int age = resultSet3.getInt("age");
            String city = resultSet3.getString("city");
            String address = resultSet3.getString("address");
            int postindex = resultSet3.getInt("postindex");


            System.out.println("————————————————————————————————————————————————————————————————————————————————");
            System.out.println("|Name:"+ name);
            System.out.println("|Surname:" + surname);
            System.out.println("|Email:"+email);
            System.out.println("|Age: "+age);
            System.out.println("|City:"+city);
            System.out.println("|Address:"+address);
            System.out.println("|PostIndex:"+ postindex);
            System.out.println("————————————————————————————————————————————————————————————————————————————————");
        }
        connection.close();
        preparedStatement3.close();

    }

}
