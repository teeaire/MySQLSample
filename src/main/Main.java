package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
        // 変数
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String databaseName = "mydatabase";
        String user = "root";
        String pass = "admin";
//        try {
//        	// JDBCドライバの読み込み
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
        	// properties
        	Properties properties = new Properties();
        	properties.setProperty("user", user);
        	properties.setProperty("password", pass);
        	properties.setProperty("useSSL", "false");
        	properties.setProperty("autoReconnect", "true");
        	properties.setProperty("useUnicode", "true");
        	properties.setProperty("useJDBCCompliantTimezoneShift", "true");
        	properties.setProperty("useLegacyDatetimeCode", "false");
        	properties.setProperty("serverTimezone", "UTC");

        	// connect to db
        	con = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, properties);
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from sampletable");

            // show results
            System.out.println("id\tfirstname\tlastname\tage\t");
            while (rs.next()) {
            	int id = rs.getInt("id");
            	String firstname = rs.getString("firstname");
            	String lastname = rs.getString("lastname");
            	int age = rs.getInt("age");
                System.out.printf("%d\t%s\t%s\t%d", id, firstname, lastname, age);
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
