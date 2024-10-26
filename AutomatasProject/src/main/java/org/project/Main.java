package org.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Automata automata = new Automata();
        Scanner scanner = new Scanner(System.in);
        String cadena;
        while (true) {
            System.out.print("Ingrese un token (o 'salir' para terminar): ");
            cadena = scanner.nextLine();
            if (cadena.equalsIgnoreCase("salir")) {
                break;
            }
            automata.iniciar(cadena);
        }
        scanner.close();
    }
}