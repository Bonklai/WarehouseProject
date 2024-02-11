package Database;

import ModelsEntities.BalanceManager;
import Utilities.DatabaseUtil;

import java.sql.*;
import java.util.Scanner;

public class DBController extends BalanceManager {
    private static String tablename1 = "";

    Scanner scanner = new Scanner(System.in);


    public void printTable(String tablename) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Select * from " + tablename;
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
                        else if( i == 5 ){
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
        buyPanel(tablename);


    }

    public void buyPanel(String tablename) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Enter index:");
        System.out.println("0.Back");
        String choice = scanner.next();
        if(choice.equals("0")){

        }
        else{
            String buySql = "select * from " + tablename + " where indexkey= " + choice;
            ResultSet resultSet = statement.executeQuery(buySql);
            if(resultSet.next()){
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                System.out.println("|Model                 Quantity    price       Indexkey     Detail             |");
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                System.out.printf("|%-20s|", resultSet.getString("model"));
                System.out.printf("%10d  |", resultSet.getInt("quantity"));
                System.out.printf("%8.2f  |", resultSet.getDouble("price"));
                System.out.printf("%10d|",resultSet.getInt("indexkey"));
                System.out.printf("%-22s|", resultSet.getString("item"));
                System.out.println();
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }
            System.out.print("Balance:"); System.out.println(getBalance());
            System.out.print("Price:"); System.out.println(getPrice(tablename,choice));
            System.out.print("Are you sure you want to add this product to cart? y/n:");
            String choice2 = scanner.next();

            if(choice2.equals("y")){
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
                addToCart(tablename,choice);

            }
            else{
                System.out.println("————————————————————————————————————————————————————————————————————————————————");
            }


            resultSet.close();
            statement.close();
            connection.close();

        }
    }
    public void addToCart(String tablename,String choice) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "Select * from " + tablename +" where indexkey = " + choice;
        String cartSql = "INSERT into cart (modelincart,quantityincart,priceincart,indexkeyincart,item) values (?,?,?,?,?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        PreparedStatement preparedStatement2 = connection.prepareStatement(cartSql);
        ResultSet resultSet =   preparedStatement1.executeQuery();
        if(resultSet.next()){
            preparedStatement2.setString(1,resultSet.getString("model"));
            preparedStatement2.setDouble(2,resultSet.getDouble("quantity"));
            preparedStatement2.setInt(3,resultSet.getInt("price"));
            preparedStatement2.setInt(4,resultSet.getInt("indexkey"));
            preparedStatement2.setString(5,resultSet.getString("item"));
            preparedStatement2.executeUpdate();
        }

        System.out.println("Your product has been successfully added to the shopping cart");
        System.out.println("————————————————————————————————————————————————————————————————————————————————");
    }

    public void afterPurchase() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sqlgetInxdex = "Select indexkeyincart from cart";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlgetInxdex);

        while(resultSet.next()){
            int index = resultSet.getInt("indexkeyincart");
            String tablename ="";
            if(index>=100 && index<=199) tablename = "batteriesforiphone";
            else if (index>=200 && index<=299) tablename = "displayforiphone";
            else if (index>=300 && index<=399) tablename = "glassforiphone3in1";
            else if(index>=400 && index<=499) tablename = "touchglassipad";
            else if(index>=500 && index<=599) tablename = "displayonipad";
            else if(index>=600 && index<=699) tablename = "batteryforipad";
            else if(index>=700 && index<=799) tablename = "batteryformacbook";
            else if(index>=800 && index<=899) tablename = "matricesformacbook";
            else if(index>=900 && index<=999) tablename = "keyboardsformacbook";


            String updateSQL = "UPDATE " + tablename + " set quantity = quantity-1 where indexkey =" + String.valueOf(index);
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();
        }
        String clearSQL = "truncate table cart";
        statement.executeUpdate(clearSQL);
    }


}
