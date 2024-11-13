package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Windows
 */
public class Conexion {

    //Conexion local
    public static Connection conectar() throws ClassNotFoundException {
        try {

            //Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:1433/bd_Abarrote?useSSL=false&serverTimezone=UTC", "sa", "admin");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                    + "databaseName=bd_biblioteca_Inteligente;"
                    + "user=sa;"
                    + "password=admin;"
                    + "trustServerCertificate=True;");

            // Verificar si la conexión es exitosa
            if (cn != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("La conexión es nula. No se puede realizar la operación.");
            }

            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return null;
    }

}
