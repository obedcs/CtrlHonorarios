/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrlhonorarios;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Sistemas1
 */

public class conecxionbd {
    Connection con=null;
    
    public String[] SQLData(String filename) {
        String SQLData[] = new String[5];  
        try{
            Properties p = new Properties();
            p.load(new FileInputStream(filename+".ini"));
            SQLData[0]=p.getProperty("Host");
            SQLData[1]=p.getProperty("Password");
            SQLData[2]=p.getProperty("DB");
            SQLData[3]=p.getProperty("User");
            SQLData[4]=p.getProperty("Port");
        }catch (Exception e) {System.out.println("error al leer el archivo .ini:"+ e.getMessage());}
        return SQLData;
    }
    
    public Connection Conecta(){
        con=null;
        String getSQLData[] = SQLData("ConfigDBSQLServer");
        System.out.println();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://"+getSQLData[0]+":"+getSQLData[4]+";databaseName="+getSQLData[2]+";user="+getSQLData[3]+";password="+getSQLData[1]+";");
            //con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TCADBHSU;user=sa;password=OK2506obed;");
            //String connectionUrl = "jdbc:sqlserver://192.168.1.67:1433;databaseName=TCADBHSU;user=sa;password=OK2506obed;";
            //con = DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e);}
        return con;
    }
    
    public Connection ConectaMySQL(){
        con=null;
        String getMySQLData[] = SQLData("ConfigDBMySQL");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection( "jdbc:mysql://"+getMySQLData[0]+":"+getMySQLData[4]+"/"+getMySQLData[2],getMySQLData[3],getMySQLData[1]);
            if(con != null){
                System.out.println("conectado a MySQL");
            }
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e);}
        return con;
    }
}
