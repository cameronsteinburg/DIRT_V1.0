package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Initializes JDBC connection to the MySQL database
 *
 */
public class DBAccessor {

    private Connection conn;

    /**
     * default DBAccessor constructor
     */
    public DBAccessor() {
    }

    /**
     * creates MySQL connection and JDBCCommands instance
     *
     * @return true if no errors occur connecting to the database
     */
    public boolean connectToMySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIRT?user=root&password=password");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InstantiationException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * disconnects MySQL connection and JDBCCommands instance
     *
     * @return true if disconnect is successful
     */
    public boolean disconnectToMySQL() {

        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Connection getConn() {
        return conn;
    }

}
