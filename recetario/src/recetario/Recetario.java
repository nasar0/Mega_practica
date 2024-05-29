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
        boolean BREC=false;
        
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
            System.out.println("3.Ver");
            System.out.println("4.Salir");
            do {
                try {
                    op = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Debe elejir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine();
                }
            } while (op < 1 || op > 4);
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
                            op=menu(tipo);
                            
                            switch (op) {
                                case 1:
                                    System.out.println("Buscar recetas mediante nombre o usuario");
                                    char brec=sc.nextLine().toLowerCase().charAt(0);
                                    if (brec=='n') {
                                        BREC=true;
                                        do {
                                            System.out.println("Dime la recetas que quieres buscar: ");
                                            rece = sc.nextLine();
                                            rece = rece.split("\n")[0];
                                            a = bd.mostrarRecetas(rece, BREC, user);
                                        } while (!a);
                                    }else if(brec=='u'){
                                        BREC=false;
                                        a = bd.mostrarRecetas(rece, BREC, user);
                                    }
                                    

                                    break;
                                case 2:                               
                                        System.out.println("Cual de sea modificar ");
                                        rece = sc.nextLine();
      
                                        if (a) {
                                            do {
                                                System.out.print("Que desea modificar: ");
                                                modf=sc.nextLine().toUpperCase();
                                                comprobar=bd.comprobar(modf);
                                            } while (!comprobar);
   
                                        }else{
                                            System.out.println("No encontrada");
                                        }
                                        bd.modificarRecetas(modf,user,rece,pass);
                                            
                                    break;
                                case 3:
                                    bd.buscarRecetas(user);
                                    break;
                                case 4:
                                    bd.mostrarUsuarios(); 
                                    break;
                                case 5:
                                    System.out.println("Cerrando sesion......");
                                    break;
                            }
                        } else if (tipo == 2) {
                            
                            op=menu(tipo);
                            switch (op) {
                                case 1:
                                    System.out.println("Buscar recetas mediante nombre o usuario");
                                    char brec=sc.nextLine().toLowerCase().charAt(0);
                                    if (brec=='n') {
                                        BREC=true;
                                    }else if(brec=='u'){
                                        BREC=false;
                                    }
                                    if (BREC) {
                                       do {

                                        System.out.println("Dime la recetas que quieres buscar: ");
                                        rece = sc.nextLine();
                                        rece = rece.split("\n")[0];
                                        a = bd.mostrarRecetas(rece,BREC,user);
                                        } while (!a); 
                                    }else{
                                        a = bd.mostrarRecetas(rece,BREC,user);
                                    }

                                    break;
                                case 2:                               
                                        System.out.println("Cual de sea modificar ");
                                        rece = sc.nextLine();
                                        
                                        if (a) {
                                            do {
                                                System.out.print("Que desea modificar: ");
                                                modf=sc.nextLine().toUpperCase();
                                                comprobar=bd.comprobar(modf);
                                            } while (!comprobar);
   
                                        }else{
                                            System.out.println("No encontrada");
                                        }
                                        bd.modificarRecetas(modf,user,rece,pass);
                                            
                                    break;
                                case 3:
                                    bd.buscarRecetas(user);
                                    break;
                                case 4:
                                    bd.crearReceta(user);
                                    break;
                                case 5:
                                    System.out.println("Cerrando sesion......");
                                    break;
                            }
                        }
                    } while (op!=5);
                    
                    break;
                case 2:
                    System.out.println("Bienvenido ");
                    bd.Registro();
                    break;
                case 3:
                    int r=menu(3);
                    if(r==1){
                        System.out.println("Dime la receta que quieres buscar¿?");
                        String nom=sc.nextLine();
                        bd.mostrarRecetas(nom, true, null);
                    }else if(r==2){
                        bd.buscarRecetas(null);
                    }
                    break;
                case 4:
                    System.out.println("Hasta pronto <<<<<<3");
                    break;
                default:
                    break;
            }

        } while (op!=4);
        bd.close();
    } 
 public static int menu(int a){
    Scanner sc =new Scanner (System.in);
    int op=0;
    if (a==1) {
       System.out.println("1.Ver recetas");
       System.out.println("2.Modificar recetas");
       System.out.println("3.Buscar recetas");
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
       } while (op < 1 || op > 5);
    }else if(a==2){
        System.out.println("1.Ver recetas");
        System.out.println("2.Modificar recetas");
        System.out.println("3.Buscar recetas");
        System.out.println("4.Crear receta");
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
        } while (op < 1 || op > 5);
    }else{
        System.out.println("1.Ver recetas");
        System.out.println("2.Buscar recetas");
        System.out.println("3.Salir");
        op=0;
        do {
            try {
                op = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
            } finally {
                sc.nextLine();
            }
        } while (op < 1 || op > 3);
    }
    
    return op;
 }  

}
