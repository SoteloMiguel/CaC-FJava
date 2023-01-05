
// import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Miguel Angel Sotelo
 */

public class persistencia {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement prepareStatement;
//    private Statement statement;
    private ResultSetMetaData resultSetMetaData;
    
    public String servidor, baseDeDatos, usuario, clave, ejecutar;
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            servidor = "localhost:3306/";
            baseDeDatos = "pf-cac4";
            usuario = "root";
            clave = "";
                
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + servidor +
                baseDeDatos+"?autoReconnect=true&useSSL=false", usuario, clave);
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public ResultSet consultaSql(String busqueda){
        try {
            prepareStatement = conectar().prepareStatement(busqueda);
            resultSet = (ResultSet) prepareStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
           
        } catch (SQLException ex) {
            Logger.getLogger(persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet; //(ResultSet) resultSetMetaData;
    }
            
}
