package Helpers;

import Utilities.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ModelsEntities.User;

public class Checker extends User {

    public Checker(){
    }

    public boolean checkRegister() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String rq1 = "Select isRegistered From userinfo";
        Statement statement = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery(rq1);
        if(resultSet1.next()){
            boolean temp = resultSet1.getBoolean("isregistered");
            if (!temp){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }

    public boolean checkDelivery() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "Select regdelivery from userinfo";
        Statement statement = connection.createStatement();
        ResultSet resultSet =statement.executeQuery(sql);
        if(resultSet.next()){
            boolean temp = resultSet.getBoolean("regdelivery");
            if(!temp) return false;
            else return true;
        }
        return false;
    }

}
