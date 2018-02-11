package persistence;

/**
 * Initializes JDBC connection to the MySQL database
 *
 */
public class DBAccessor {

	/**
	 * default DBAccessor constructor
	 */
	public DBAccessor(){
		
	}
	
	
	/**
	 * creates MySQL connection and JDBCCommands instance
	 * @return
	 */
	public boolean connectToMySQL(){
		return false;
	}
	
	/**
	 * disconnects MySQL connection and JDBCCommands instance
	 * @return
	 */
	public boolean disconnectToMySQL(){
		return false;
	}
	
}
