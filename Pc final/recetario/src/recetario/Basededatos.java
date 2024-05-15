/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recetario;
import java.io.*;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author EAG
 */
public class Basededatos {
    /*
    final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "aula";
    final String pass = "aula";
    */
    final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "nasaro";
    final String pass = "1234";
    
    private Connection con;
    
    public Basededatos() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, username, pass);
        System.out.println("Conexión establecida");
    }
    public void insertar(String a)throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("insert into registro values('"+a+"', s ,d,f,f");
        
    }
    public ResultSet consulta(String a) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(a);
        return rs;
    }
    public void cerrar() throws SQLException{
        con.close();
    }
    public int inicioSesion(String a,String b)throws SQLException{
        int num=0;
        ResultSet nC = consulta("select count(*) from usuarios where usuario='" + a + "' and pass='" + b + "'");
        nC.next();
        num = nC.getInt(1);
        return num;
    }
    public int tipoUsuario(String a,String b)throws SQLException{
        int num=0;
        ResultSet nC = consulta("select tipo from usuarios where usuario='" + a + "' and pass='" + b + "'");
        nC.next();
        num = nC.getInt(1);
        return num;
    }
    public boolean buscarRecetas(String a,boolean b) throws SQLException {
        int num = 0;
        try {
            ResultSet nC = consulta("select count(*) from Recetas where nombre='" + a+"'");
            nC.next();
            num = nC.getInt(1);
        } catch (Exception e) {
            
        }
        if (num == 1) {
            ResultSet rs = consulta("select * from recetas where nombre='" + a+"'");
            ResultSet nC = consulta("select count(*) from recetas");
            nC.next();
            if (b) {
                while(rs.next()){
                for (int i = 2; i <=8; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    System.out.println(columnName+": "+rs.getString(i));
                }
                skip();
                }   
            }            
            return true;
        } else {
            System.out.println("No existe dicha receta. Si no conoce las recetas existentes vaya al apartado de buscar recetas");
            return false;
            
        }
    }
    public void mostrarRecetas(String a)throws SQLException{
        ResultSet rs = consulta("select * from recetas");
        /*ResultSet nC = consulta("select count(*) from recetas");
        nC.next();*/
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
        skip();
    }
    public void modificarRecetas(String a,String user,String rece)throws SQLException{
        Scanner sc =new Scanner (System.in);
        String nuevo;
        System.out.print("Introduce el nuevo "+a+" : ");
        nuevo=sc.nextLine();
        Statement st = con.createStatement();
        String sql = "UPDATE recetas SET " + a + " = '" + nuevo + "' WHERE usuario = '" + user + "' and nombre='"+rece+"'";
        int rowsUpdated = st.executeUpdate(sql);
        System.out.println(rowsUpdated + " filas actualizadas.");
    }
    public boolean comprobar(String a)throws SQLException{
        
        ResultSet rs = consulta("select * from recetas ");
        boolean t=false;
        while (rs.next()) {
            for (int i = 2; i <= 8; i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                if (a.equals(columnName)) {
                    t=true;
                }
            }
            skip();
        }
        return t;
    
    }
    public void skip() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}
