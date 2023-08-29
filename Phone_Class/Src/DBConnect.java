package Src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null){
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            conn = DriverManager.getConnection(url,props);
        }
        return  conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn !=null){
            conn.close();
        }

    }

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("dp.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return  props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
