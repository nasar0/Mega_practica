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
        //VARIABLES PARA EL MAIN
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
        //MENU PRINCIPAL 
        do {
            // Menú principal
            System.out.println("1.Iniciar sesion");
            System.out.println("2.Registrarse");
            System.out.println("3.Ver");
            System.out.println("4.Salir");

            do {
                try {
                    op = sc.nextInt(); // Lee la opción del menú
                } catch (Exception e) {
                    System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine(); // Limpia el buffer del escáner
                }
            } while (op < 1 || op > 4); // Validación de la opción

            switch (op) {
                case 1:
                    // Iniciar sesión
                    do {
                        try {
                            System.out.print("Usuario: ");
                            user = sc.nextLine(); // Lee el usuario
                            System.out.print("Contraseña: ");
                            pass = sc.nextLine(); // Lee la contraseña
                            t = bd.inicioSesion(user, pass); // Verifica las credenciales
                        } catch (Exception e) {
                            System.out.println("Usuario o contraseña incorrectos");
                        }
                    } while (t != 1); // Repite hasta que el inicio de sesión sea exitoso

                    tipo = bd.tipoUsuario(user, pass); // Obtiene el tipo de usuario

                    do {
                        if (tipo == 1) {
                            // Menú para administrador
                            op = menu(tipo); // Muestra el menú y obtiene la opción

                            switch (op) {
                                case 1:
                                    // Buscar recetas
                                    System.out.println("Buscar recetas mediante nombre o usuario");
                                    char brec = sc.nextLine().toLowerCase().charAt(0); // Lee opción de búsqueda
                                    if (brec == 'n') {
                                        BREC = true;
                                        do {
                                            System.out.println("Dime la recetas que quieres buscar: ");
                                            rece = sc.nextLine(); // Lee el nombre de la receta
                                            rece = rece.split("\n")[0]; // Elimina salto de línea
                                            a = bd.mostrarRecetas(rece, BREC, user); // Muestra recetas por nombre
                                        } while (!a); // Repite hasta encontrar la receta
                                    } else if (brec == 'u') {
                                        BREC = false;
                                        a = bd.mostrarRecetas(rece, BREC, user); // Muestra recetas por usuario
                                    }
                                    break;

                                case 2:
                                    // Modificar recetas
                                    System.out.println("Cuál desea modificar ");
                                    rece = sc.nextLine(); // Lee el nombre de la receta

                                    if (a) {
                                        do {
                                            System.out.print("Qué desea modificar: ");
                                            modf = sc.nextLine().toUpperCase(); // Lee modificación
                                            comprobar = bd.comprobar(modf); // Comprueba la modificación
                                        } while (!comprobar);
                                    } else {
                                        System.out.println("No encontrada");
                                    }
                                    bd.modificarRecetas(modf, user, rece, pass); // Modifica la receta
                                    break;

                                case 3:
                                    // Buscar recetas del usuario
                                    bd.buscarRecetas(user); // Muestra las recetas del usuario
                                    break;

                                case 4:
                                    // Mostrar usuarios
                                    bd.mostrarUsuarios(); // Muestra todos los usuarios
                                    break;

                                case 5:
                                    // Cerrar sesión
                                    System.out.println("Cerrando sesión......");
                                    break;
                            }
                        } else if (tipo == 2) {
                            // Menú para usuario estándar
                            op = menu(tipo); // Muestra el menú y obtiene la opción

                            switch (op) {
                                case 1:
                                    // Buscar recetas
                                    System.out.println("Buscar recetas mediante nombre o usuario");
                                    char brec = sc.nextLine().toLowerCase().charAt(0); // Lee opción de búsqueda
                                    if (brec == 'n') {
                                        BREC = true;
                                    } else if (brec == 'u') {
                                        BREC = false;
                                    }
                                    if (BREC) {
                                        do {
                                            System.out.println("Dime la recetas que quieres buscar: ");
                                            rece = sc.nextLine(); // Lee el nombre de la receta
                                            rece = rece.split("\n")[0]; // Elimina salto de línea
                                            a = bd.mostrarRecetas(rece, BREC, user); // Muestra recetas por nombre
                                        } while (!a); // Repite hasta encontrar la receta
                                    } else {
                                        a = bd.mostrarRecetas(rece, BREC, user); // Muestra recetas por usuario
                                    }
                                    break;

                                case 2:
                                    // Modificar recetas
                                    System.out.println("Cuál desea modificar ");
                                    rece = sc.nextLine(); // Lee el nombre de la receta

                                    if (a) {
                                        do {
                                            System.out.print("Qué desea modificar: ");
                                            modf = sc.nextLine().toUpperCase(); // Lee modificación
                                            comprobar = bd.comprobar(modf); // Comprueba la modificación
                                        } while (!comprobar);
                                    } else {
                                        System.out.println("No encontrada");
                                    }
                                    bd.modificarRecetas(modf, user, rece, pass); // Modifica la receta
                                    break;

                                case 3:
                                    // Buscar recetas del usuario
                                    bd.buscarRecetas(user); // Muestra las recetas del usuario
                                    break;

                                case 4:
                                    // Crear receta
                                    bd.crearReceta(user); // Crea una nueva receta
                                    break;

                                case 5:
                                    // Cerrar sesión
                                    System.out.println("Cerrando sesión......");
                                    break;
                            }
                        }
                    } while (op != 5); // Repite hasta que el usuario decida cerrar sesión
                    break;

                case 2:
                    // Registrarse
                    System.out.println("Bienvenido ");
                    bd.Registro(); // Llama al método de registro
                    break;

                case 3:
                    // Ver recetas
                    int r = menu(3); // Muestra el menú y obtiene la opción
                    if (r == 1) {
                        System.out.println("Dime la receta que quieres buscar¿?");
                        String nom = sc.nextLine(); // Lee el nombre de la receta
                        bd.mostrarRecetas(nom, true, null); // Muestra la receta por nombre
                    } else if (r == 2) {
                        bd.buscarRecetas(null); // Muestra todas las recetas
                    }
                    break;

                case 4:
                    // Salir
                    System.out.println("Hasta pronto <<<<<<3");
                    break;

                default:
                    break;
            }
        } while (op != 4); // Repite hasta que el usuario decida salir

        bd.close(); // Cierra la conexión a la base de datos
    } 
    public static int menu(int a) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        if (a == 1) {
            // Menú para administrador
            System.out.println("1.Ver recetas");
            System.out.println("2.Modificar recetas");
            System.out.println("3.Buscar recetas");
            System.out.println("4.Ver usuarios");
            System.out.println("5.Cerrar sesión");
            do {
                try {
                    op = sc.nextInt(); // Lee la opción del menú
                } catch (Exception e) {
                    System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine(); // Limpia el buffer del escáner
                }
            } while (op < 1 || op > 5); // Validación de la opción
        } else if (a == 2) {
            // Menú para usuario estándar
            System.out.println("1.Ver recetas");
            System.out.println("2.Modificar recetas");
            System.out.println("3.Buscar recetas");
            System.out.println("4.Crear receta");
            System.out.println("5.Cerrar sesión");
            do {
                try {
                    op = sc.nextInt(); // Lee la opción del menú
                } catch (Exception e) {
                    System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine(); // Limpia el buffer del escáner
                }
            } while (op < 1 || op > 5); // Validación de la opción
        } else {
            // Menú general
            System.out.println("1.Ver recetas");
            System.out.println("2.Buscar recetas");
            System.out.println("3.Salir");
            do {
                try {
                    op = sc.nextInt(); // Lee la opción del menú
                } catch (Exception e) {
                    System.out.println("Debe elegir uno de los valores que aparecen en pantalla");
                } finally {
                    sc.nextLine(); // Limpia el buffer del escáner
                }
            } while (op < 1 || op > 3); // Validación de la opción
    }
    
    return op;
 }  

}
