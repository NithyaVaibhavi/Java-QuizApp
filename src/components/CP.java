package components;

import java.sql.Connection;
import java.sql.DriverManager;

public class CP {
        static Connection con;
        public static Connection createC(){
                //load the driver
                try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //create the connection
                        String user="root";
                        String password="PFH#23kgrw9";
                        String url="jdbc:mysql://localhost:3306/quiz_app";
                        con= DriverManager.getConnection(url,user,password);
                }catch(Exception e){
                        System.out.println(e);
                }
                return con;
        }
}
