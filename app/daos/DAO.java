/**
 * 
 */
package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.Logger;

/**
 * user : yonatan<br/>
 * update date : Jun 20, 2015<br/>
 * update by : Yonatan Alexis Quintero Rodriguez<br/>
 * 
 * @created : Jun 20, 2015<br/>
 * @version : 0.1 <br/>
 * @author Yonatan Alexis Quintero Rodriguez
 * 
 */
public class DAO {
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			Logger.error("Error tryining to close to database. \n" + e.getMessage(), e);
		}
	}

	/**
	 * @param callableStatement
	 * @param Connection
	 */
	public static void close(CallableStatement callableStatement, Connection Connection) {
		try {
			if (callableStatement != null) {
				callableStatement.close();
			}
			Connection.close();
		} catch (SQLException e) {
			Logger.error("Error tryining to close to database. \n" + e.getMessage(), e);
		}
	}

	/**
	 * @param resulSet
	 * @param callableStatement
	 * @param Connection
	 */
	public static void close(ResultSet resulSet, CallableStatement callableStatement, Connection Connection) {
		try {
			if (resulSet != null) {
				resulSet.close();
			}
			if (callableStatement != null) {
				callableStatement.close();
			}
			Connection.close();
		} catch (SQLException e) {
			Logger.error("Error tryining to close to database. \n" + e.getMessage(), e);
		}
	}
}
