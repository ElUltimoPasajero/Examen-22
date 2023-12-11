package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    /* Completar consultas */
    static final String SAVE = "INSERT INTO USUARIO (nombre, apellidos, dni) VALUES (?, ?, ?)";;
    static final String LIST_QUERY="SELECT * FROM USUARIO";
    static final String GET_BY_DNI="SELECT * FROM USUARIO WHERE DNI = ?";
    
    
    public void connect(){
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
            }
            /* completar código de conexión */
            
            System.out.println("Base de datos conectada");
        }catch(Exception ex){
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }     
    }

    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");
        }
    }

    public void save(Usuario user){
        try {
            try (PreparedStatement pstmt = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, user.getNombre());
                pstmt.setString(2, user.getApellidos());
                pstmt.setString(3, user.getDni());

                pstmt.executeUpdate();

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Error al obtener el ID del usuario");
                }
            }

            System.out.println("Usuario guardado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al guardar el usuario");
            System.out.println(ex);
        }
    }

    public ArrayList<Usuario> list(){
        var usuarios = new ArrayList<Usuario>();

        try {
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(LIST_QUERY);

                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setDni(rs.getString("dni"));

                    usuarios.add(usuario);
                }
            }

            System.out.println("Lista de usuarios obtenida correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de usuarios");
            System.out.println(ex);
        }

        return usuarios;
    }

    public Usuario getByDNI(String dni){
        Usuario usuario = new Usuario();

        try {
            try (PreparedStatement pstmt = connection.prepareStatement(GET_BY_DNI)) {
                pstmt.setString(1, dni);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    usuario.setId(rs.getLong("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setDni(rs.getString("dni"));
                }
            }

            System.out.println("Usuario obtenido por DNI correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al obtener el usuario por DNI");
            System.out.println(ex);
        }

        return usuario;
    }
}
