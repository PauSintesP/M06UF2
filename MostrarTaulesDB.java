import java.sql.*;

public class MostrarTaulesDB {
    public static void main(String[] args) {

        // URL de conexión con el formato correcto
        String url = "jdbc:mariadb://localhost:3306/LigaFutbol";
        String usuari = "user"; // Sustituye por tu usuario
        String contrasenya = "123"; // Sustituye por tu contraseña

        // Cargar el controlador JDBC de MariaDB
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
            return;
        }

        // Usamos try-with-resources para gestionar la conexión
        try (Connection conn = DriverManager.getConnection(url, usuari, contrasenya)) {
            System.out.println("Connexió establerta correctament!");

            // Crear l'Statement
            try (Statement stmt = conn.createStatement()) {

                // Executar la consulta
                String query = "SHOW TABLES;";
                try (ResultSet rs = stmt.executeQuery(query)) {

                    // Mostrar el resultat
                    System.out.println("Taules a la base de dades:");
                    while (rs.next()) {
                        System.out.println(rs.getString(1)); // Primera columna: nom de la taula
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la connexió o consulta: " + e.getMessage());
        }
    }
}
