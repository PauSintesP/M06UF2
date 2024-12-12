package com.iticbcn;
import java.sql.*;

public class MostrarTaulesDB {
    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/ligafutbol";
        String usuari = "root";
        String contrasenya = "123";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, usuari, contrasenya)) {
            System.out.println("Connexió establerta correctament!");

            try (Statement stmt = conn.createStatement()) {

                String query = "SHOW TABLES;";
                try (ResultSet rs = stmt.executeQuery(query)) {

                    System.out.println("Que vols fer?");
                    System.out.println("1. Mostrar taules de la base de dades");
                    System.out.println("2. Fer un insert a la taula 'jugador'");
                    System.out.println("3. Sortir");
                    int resp = Integer.parseInt(Entrada.readLine());
                    switch (resp) {
                        case 1:
                            Utils.mostrarJugadores(conn, resp, resp);
                            break;
                        case 2:

                        
                        default:
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la connexió o consulta: " + e.getMessage());
        }
    }
}
