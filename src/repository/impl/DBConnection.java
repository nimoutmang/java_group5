package repository.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {

    public static Connection init(){
        try{
            return  DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/group_5",
                    "postgres",
                    "123"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }


}
