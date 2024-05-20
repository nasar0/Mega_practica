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
    
    final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "aula";
    final String pass = "aula";
   /*
    final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "nasaro";
    final String pass = "1234";
   */
    private Connection con;
    
    public Basededatos() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, username, pass);
        System.out.println("Conexión establecida");
    }
    
    public void Registro()throws SQLException {
        Scanner sc =new Scanner (System.in);
        String a=null;
        String pas=null;
        String email=null;
        int res = 0;
        Statement st = con.createStatement();
        do {
            try{
                System.out.print("Introduzca el nombre de usuario: ");
                a=sc.nextLine();
                System.out.print("Introduzca la contraseña: ");
                pas=sc.nextLine();
                System.out.print("Introduce el email ");
                email=sc.nextLine();
                
                res = st.executeUpdate("insert into usuarios values('"+a+"','"+pas+"','"+email+"','2')"); 
            }catch(Exception e){
                System.out.println("Los valores introducidos no son validos o son existentes");
            }
        } while (res == 0);
 
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
    
    public boolean buscarRecetas(String a,boolean b,String us) throws SQLException {
        int num = 0;
        if (b) {
            try {
                ResultSet nC = consulta("SELECT distinct count(*) FROM Recetas r JOIN etiquetas e ON r.id = e.id WHERE r.nombre='"+a+"' OR e.nombre ='"+a+"'");
                nC.next();
                num = nC.getInt(1);
            } catch (Exception e) {
                System.out.println("no existe raceta");
            }   
        }else{
            num=1;
        }
        
        ResultSet rs;
        if (num != 0) {
            if (b) {
                rs = consulta("select distinct * from recetas r,etiquetas e where e.id=r.id and e.nombre='"+a+"' or r.nombre= '" + a+"'");
            }else{
                rs = consulta("select distinct r.* from recetas r,usuarios u where r.usuario=u.usuario and u.usuario='"+us+"'");
            }
            
            skip();
            int numC=rs.getMetaData().getColumnCount();
            System.out.println("Descripcion de la receta: ");
            while(rs.next()){
                for (int i = 2; i <=numC ; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    if (columnName.equals("TIEMPO")) {
                        System.out.println(columnName + ": " + rs.getString(i)+" minutos");
                    }else{
                        System.out.println(columnName + ": " + rs.getString(i));
                    }
                }
                
                int rId = rs.getInt(1);
                System.out.println("");
                System.out.println("Ingredientes: ");
                ResultSet rs1=consulta("select distinct i.* from ingredientes i where "+ rId +" =i.id" );
                numC=rs1.getMetaData().getColumnCount();
                while (rs1.next()) {
                    for (int i = 1; i < numC; i++) {
                        String columnName = rs1.getMetaData().getColumnName(i);
                        System.out.println(columnName+": "+rs1.getString(i));
                    }
                }
                System.out.println("");
                System.out.println("Pasos de preparacion");
                ResultSet rs2=consulta("select distinct p.* from pasospreparacion p where "+ rId +" =p.id");
                numC=rs2.getMetaData().getColumnCount();
                while (rs2.next()) {
                    for (int i = 2; i <= numC-1; i++) {
                        String columnName = rs2.getMetaData().getColumnName(i);
                        System.out.println(columnName + ": " + rs2.getString(i));
                    }
                }
            skip(); 
            } 
            
            
            /****
            
            
            
            
            *       YA TE FUNCIONA, AHORA TE LO MIRAS Y ME LO COMENTAS
            
            
            
            
            
            
            ****/
                       
            return true;
        } else {
            System.out.println("No existe dicha receta. Si no conoce las recetas existentes vaya al apartado de buscar recetas");
            return false;
        }
    }
    
    public void mostrarRecetas(String user)throws SQLException{
        Scanner sc =new Scanner (System.in);
        int op;
        do {
            System.out.println("¿Desea ver todas las recetas o solo las suyas?(1/2)");
            op=sc.nextInt();
        } while (op!=1 && op!=2);
        
        if (op==1) {
            ResultSet rs = consulta("select * from recetas");
            /*ResultSet nC = consulta("select count(*) from recetas");
            nC.next();*/
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }  
        }else if (op==2) {
            ResultSet rs = consulta("select * from recetas WHERE usuario =" + user );
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
            System.out.println();
            System.out.println("Aqui estan sus respectivas etiquetas: ");
            rs = consulta("select e.* from etiquetas e,usuarios u,recetas r WHERE r.id=e.id and u.usuario=r.usuario and u.usuario="+user);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
        
        skip();
    }
    
    public void modificarRecetas(String a,String user,String rece,String pass)throws SQLException{
        Scanner sc =new Scanner (System.in);
        String nuevo;
        System.out.print("Introduce el nuevo "+a+": ");
        nuevo=sc.nextLine();
        Statement st = con.createStatement();
        int t=tipoUsuario(user,pass);
        if (t==1) {
            String sql = "UPDATE recetas SET " + a + " = '" + nuevo + "' WHERE nombre='"+rece+"'";
            int rowsUpdated = st.executeUpdate(sql);
            System.out.println(rowsUpdated + " filas actualizadas.");
        }else{
            String sql = "UPDATE recetas SET " + a + " = '" + nuevo + "' WHERE usuario = '" + user + "' and nombre='"+rece+"'";
            int rowsUpdated = st.executeUpdate(sql);
            if (rowsUpdated==0) {
                System.out.println("No puede modificar esa receta porque no es suya");
            }
        }
        
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
        }
        return t;
    
    }
    
    public void skip() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
    
    public void mostrarUsuarios()throws SQLException{
        ResultSet rs = consulta("select * from usuarios");
        ResultSet nC = consulta("select count(*) from recetas");
        nC.next();
        int nume=nC.getInt(1);
        int numC=rs.getMetaData().getColumnCount();
        int cont = 1;
        while (rs.next()) {
            System.out.println("usuario "+cont+":");
            System.out.println();
            String columnName = rs.getMetaData().getColumnName(1);
            System.out.println(columnName+": "+rs.getString(1));
            columnName = rs.getMetaData().getColumnName(3);
            System.out.println(columnName+": "+rs.getString(3));
            System.out.println();
            cont++;
        }  
    }
    public void crearReceta(String user)throws SQLException{
        Scanner sc = new Scanner(System.in);
        Statement st = con.createStatement();
        String dif ;
        String di;
        System.out.print("Nombre de la receta: ");
        String n = sc.nextLine();
        System.out.print("Descripcion de la receta: ");
        String de = sc.nextLine();
        System.out.println("Pertenece algun dibujo¿?");
        char a=sc.next().toLowerCase().charAt(0);
        if (a=='s') {
           System.out.print("Dibujo de la receta: ");
            di= sc.nextLine();
        }else{
            di = null;
        }
        
        System.out.print("Tiempo de la receta: ");
        String t = sc.nextLine();
        do {
            System.out.print("Dificultad de la receta: ");
            dif = sc.nextLine();
        } while (!dif.equals("Facil") && !dif.equals("Intermedia") && !dif.equals("Dificil") );

        System.out.print("Calorias de la receta: ");
        String c = sc.nextLine();
        try {
            st = con.createStatement();
            int res = st.executeUpdate("INSERT INTO recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario)VALUES('" + n + "','" + de + "','" + di + "','" + t + "','" + dif + "'," + c + ",'" + user + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            System.out.println("RECETA CREADA");
        }
        
    }
}
