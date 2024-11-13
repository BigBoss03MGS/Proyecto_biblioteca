
package Controlador;

import Conexion.Conexion;

import Modelo.Usuario;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author Windows
 */
public class Ctrl_Usuario {
    
    //Metodo para registrar un nuevo usuario
    public boolean guardar(Usuario objeto) throws ClassNotFoundException {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono, estado) VALUES (?, ?, ?, ?, ?, ?);");

            //consulta.setInt(1, 0);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);

        }
        return respuesta;
    }

    //Metodo para consultar que existe usuario
    public boolean existeUsuario(String telefono) throws ClassNotFoundException {
        boolean respuesta = false;
        String sql = "select telefono from tb_usuario where telefono = '" + telefono + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);

        }
        return respuesta;
    }
    

    // Método para iniciar sesión
    public boolean loginUser(Usuario objeto) {

        boolean respuesta = false;

        try {
            Connection cn = Conexion.conectar();
            String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "';";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador JDBC");
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e);
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
        }

        return respuesta;
    }
    
    //Metodo para actualizar usuario
    public boolean actualizar(Usuario objeto, int idUsuario) throws ClassNotFoundException {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_usuario set nombre = ?, apellido = ?, usuario = ?, password = ?, telefono = ?,  estado = ? where idUsuario = '" + idUsuario + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());
            

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);

        }
        return respuesta;
    }
    
    //Metodo para eliminar usuario
    public boolean eliminar(int idUsuario) throws ClassNotFoundException {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("delete from tb_usuario where idUsuario = '" + idUsuario + "'");
            consulta.executeUpdate();
            

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);

        }
        return respuesta;
    }
}
