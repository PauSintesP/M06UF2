package com.iticbcn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    public static void crearBaseDeDatos(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Crear base de datos
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ligaFutbol;");
            stmt.executeUpdate("USE ligaFutbol;");

            // Crear tabla 'lliga'
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS lliga (
                    id_lliga INT AUTO_INCREMENT PRIMARY KEY,
                    nom_lliga VARCHAR(100) NOT NULL,
                    temporada VARCHAR(20) NOT NULL
                );
            """);

            // Crear tabla 'equip'
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS equip (
                    id_equip INT AUTO_INCREMENT PRIMARY KEY,
                    nom_equip VARCHAR(100) NOT NULL,
                    ciutat VARCHAR(100) NOT NULL,
                    id_lliga INT NOT NULL,
                    FOREIGN KEY (id_lliga) REFERENCES lliga(id_lliga) ON DELETE CASCADE
                );
            """);

            // Crear tabla 'jugador'
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS jugador (
                    id_jugador INT AUTO_INCREMENT PRIMARY KEY,
                    nom VARCHAR(100) NOT NULL,
                    cognoms VARCHAR(100) NOT NULL,
                    id_equip INT NOT NULL,
                    FOREIGN KEY (id_equip) REFERENCES equip(id_equip) ON DELETE CASCADE
                );
            """);

            // Crear tabla 'partit'
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS partit (
                    id_partit INT AUTO_INCREMENT PRIMARY KEY,
                    data_partit DATE NOT NULL,
                    id_equip_local INT NOT NULL,
                    id_equip_visitant INT NOT NULL,
                    gols_local INT NOT NULL,
                    gols_visitant INT NOT NULL,
                    FOREIGN KEY (id_equip_local) REFERENCES equip(id_equip) ON DELETE CASCADE,
                    FOREIGN KEY (id_equip_visitant) REFERENCES equip(id_equip) ON DELETE CASCADE
                );
            """);

            // Crear tabla 'classificacio'
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS classificacio (
                    id_classificacio INT AUTO_INCREMENT PRIMARY KEY,
                    id_equip INT NOT NULL,
                    punts INT NOT NULL,
                    partits_jugats INT NOT NULL,
                    victories INT NOT NULL,
                    FOREIGN KEY (id_equip) REFERENCES equip(id_equip) ON DELETE CASCADE
                );
            """);

            System.out.println("Base de dades i taules creades correctament.");

            // Insertar datos iniciales
            insertarDatosIniciales(conn);

        } catch (SQLException e) {
            System.err.println("Error al crear la base de dades: " + e.getMessage());
        }
    }

    private static void insertarDatosIniciales(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO lliga (nom_lliga, temporada) VALUES ('Primera División', '2023/24');");
            stmt.executeUpdate("INSERT INTO equip (nom_equip, ciutat, id_lliga) VALUES ('FC Barcelona', 'Barcelona', 1);");
            stmt.executeUpdate("INSERT INTO jugador (nom, cognoms, id_equip) VALUES ('Lionel', 'Messi', 1);");

            System.out.println("Dades inicials inserides correctament.");
        } catch (SQLException e) {
            System.err.println("Error al inserir dades inicials: " + e.getMessage());
        }
    }

    public static void mostrarJugadores(Connection conn) {
        String query = "SELECT * FROM jugador LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_jugador") + 
                                       ", Nom: " + rs.getString("nom") + 
                                       ", Cognoms: " + rs.getString("cognoms"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar els jugadors: " + e.getMessage());
        }
    }

    public static void insertarJugador(Connection conn, String nombre, String apellidos, int idEquipo) {
        String query = "INSERT INTO jugador (nom, cognoms, id_equip) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setInt(3, idEquipo);
            stmt.executeUpdate();
            System.out.println("Jugador inserit correctament.");
        } catch (SQLException e) {
            System.err.println("El jugador ja existeix " + e.getMessage());
        }
    }
}