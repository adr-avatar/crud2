/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crud2;

/**
 *
 * @author adr
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Crud2 {

        private static final String FILE_NAME = "personas.dat";
    
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();
        cargarDatos(personas);

        while (true) {
            System.out.println("1. Agregar Persona");
            System.out.println("2. Leer Personas");
            System.out.println("3. Modificar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarPersona(scanner, personas);
                    break;
                case 2:
                    leerPersonas(personas);
                    break;
                case 3:
                    modificarPersona(scanner, personas);
                    break;
                case 4:
                    eliminarPersona(scanner, personas);
                    break;
                case 5:
                    guardarDatos(personas);
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarPersona(Scanner scanner, List<Persona> personas) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Género: ");
        String genero = scanner.nextLine();

        personas.add(new Persona(id, nombre, edad, genero));
    }

    private static void leerPersonas(List<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }

    private static void modificarPersona(Scanner scanner, List<Persona> personas) {
        System.out.print("Ingrese el ID de la persona a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (Persona persona : personas) {
            if (persona.getId() == id) {
                System.out.print("Nuevo Nombre: ");
                persona.setNombre(scanner.nextLine());
                System.out.print("Nueva Edad: ");
                persona.setEdad(scanner.nextInt());
                scanner.nextLine(); // Consumir el salto de línea
                System.out.print("Nuevo Género: ");
                persona.setGenero(scanner.nextLine());
                return;
            }
        }
        System.out.println("Persona no encontrada.");
    }

    private static void eliminarPersona(Scanner scanner, List<Persona> personas) {
        System.out.print("Ingrese el ID de la persona a eliminar: ");
        int id = scanner.nextInt();
        personas.removeIf(persona -> persona.getId() == id);
    }

    private static void cargarDatos(List<Persona> personas) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            personas.addAll((List<Persona>) ois.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo, comenzando nuevo registro.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void guardarDatos(List<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}