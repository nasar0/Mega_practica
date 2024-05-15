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
public class Recetario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Basededatos bd=new Basededatos();
        Scanner sc =new Scanner (System.in);
        int op=0;
        int t=0;
        int tipo;
        
        String user="";
        String pass="";
        String rece="";
        String modf="";
        
        boolean cb=false;
        boolean a=false;
        boolean comprobar=false;
        
        char c='a';
        /*
        ResultSet rs = bd.consulta("select * from recetas");
        ResultSet nC = bd.consulta("select count(*) from recetas");
        nC.next();
        while(rs.next()){
            System.out.println(rs.getString(2));
        }*/
        do {
            System.out.println("1.Iniciar sesion");
            System.out.println("2.Registrarse");
            System.out.println("3.Ver recetas");
            System.out.println("4.Salir");
            do {
                try {
                    op = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Debe elejir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine();
                }
            } while (op > 1 && op < 4);
            switch (op) {
                case 1:
                    
                        do {
                            try {
                                System.out.print("Usuario: ");
                                user = sc.nextLine();
                                System.out.print("Contraseña: ");
                                pass = sc.nextLine();
                                t = bd.inicioSesion(user, pass);
                            } catch (Exception e) {
                                System.out.println("Usuario o contraseña incorrectos");
                            }
                        } while (t != 1);
                        tipo = bd.tipoUsuario(user, pass);
                        do {
                        if (tipo == 1) {
                            System.out.println("1.Buscar recetas");
                            System.out.println("2.Modificar recetas");
                            System.out.println("3.Ver recetas");
                            System.out.println("4.Ver usuarios");
                            System.out.println("5.Cerrar sesion");
                            op=0;
                            do {
                                try {
                                    op = sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
                                } finally {
                                    sc.nextLine();
                                }
                            } while (op < 1 && op > 5);
                            
                            switch (op) {
                                case 1:
                                    do {
                                        System.out.println("Dime la recetas que quieres buscar: ");
                                        rece = sc.nextLine();
                                        rece = rece.split("\n")[0];
                                        sc.nextLine();
                                        System.out.println("Quieres ver la informacion de la receta¿?(Si/No)");
                                        c=sc.next().toLowerCase().charAt(0);
                                        if (c=='s') {
                                            cb=true;
                                        }else if (c=='n'){
                                            cb=false;
                                        }
                                        a = bd.buscarRecetas(rece,cb);
                                    } while (!a);

                                    break;
                                case 2:                               
                                        System.out.println("Cual de sea modificar ");
                                        rece = sc.nextLine();
                                        cb=false;
                                        a = bd.buscarRecetas(rece,cb);
                                        if (a) {
                                            do {
                                                System.out.print("Que desea modificar: ");
                                                modf=sc.nextLine().toUpperCase();
                                                comprobar=bd.comprobar(modf);
                                            } while (!comprobar);
   
                                        }else{
                                            System.out.println("No encontrada");
                                        }
                                        bd.modificarRecetas(modf,user,rece);
                                            
                                    break;
                                case 3:
                                    cb=false;
                                    bd.mostrarRecetas(user);
                                    break;
                                case 4:

                                    break;
                                case 5:
                                    System.out.println("Cerrando sesion......");
                                    break;
                            }
                        } else if (tipo == 2) {

                        }
                    } while (op!=5);
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Hasta pronto <<<<<<3");
                    break;
                default:
                    break;
            }

        } while (op!=4);
        
    } 
   

}
