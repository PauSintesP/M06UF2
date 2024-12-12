package com.iticbcn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LigaFutbol", "usuariProva", "12345")) {
            int opcion;
            do {
                System.out.println("1. Inserir jugador");
                System.out.println("2. Mostrar jugadors (paginació)");
                System.out.println("3. Sortir");
                System.out.print("Tria una opció: ");
                opcion = Integer.parseInt(Entrada.readLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Nom: ");
                        String nom = Entrada.readLine();
                        System.out.print("Cognoms: ");
                        String cognoms = Entrada.readLine();
                        System.out.print("ID de l'equip: ");
                        int idEquip = Integer.parseInt(Entrada.readLine());
                        Utils.insertarJugador(conn, nom, cognoms, idEquip);
                        break;
                    case 2:
                        System.out.print("Offset: ");
                        int offset = Integer.parseInt(Entrada.readLine());
                        System.out.print("Limit: ");
                        int limit = Integer.parseInt(Entrada.readLine());
                        Utils.mostrarJugadores(conn, offset, limit);
                        break;
                    case 3:
                        System.out.println("Sortint...");
                        break;
                    default:
                        System.out.println("Opció no vàlida.");
                }

            } while (opcion != 3);
        } catch (SQLException e) {
            System.err.println("Error de connexió: " + e.getMessage());
        }
    }
}