/**
 * 
 */
package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import models.Group;
import models.ProgrammingBox;
import play.Logger;
import play.db.DB;

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
public class GroupDAO extends DAO {

	/**
	 * @param group
	 * @return
	 */
	public static boolean create(Group group) {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_group_collections_CREATE(?,?,?,?,?)");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, group.getCreationYear());
			cst.setString(3, group.getName());
			cst.setTimestamp(4, Timestamp.valueOf(group.getStarDate()));
			cst.setTimestamp(5, Timestamp.valueOf(group.getExpirationDate()));
			created = cst.executeUpdate() > 0;
			if (created) {
				group.setId(cst.getInt(1));
				created = group.getId() > 0;
			}
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining created the groupCollection.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return created;
	}

	public static boolean update(Group group) {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_groups_collections_UPDATE(?,?,?,?,?)");
			cst.setInt(1, group.getId());
			cst.setInt(2, group.getCreationYear());
			cst.setString(3, group.getName());
			cst.setTimestamp(4, Timestamp.valueOf(group.getStarDate()));
			cst.setTimestamp(5, Timestamp.valueOf(group.getExpirationDate()));
			updated = cst.executeUpdate() > 0;
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining update the group.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return updated;
	}

	public static boolean load(ProgrammingBox programmingBox) {
		Boolean loaded = false;
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		programmingBox.setGroups(new ArrayList<Group>());
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_group_collections_LOAD()");
			rs = cst.executeQuery();
			if (rs.next()) {
				do {
					programmingBox.getGroups().add(new Group(rs.getInt(1), rs.getString(2)));
				} while (rs.next());
			}
			loaded = true;
		} catch (Exception e) {
			Logger.error("An error has been occurred tryning loading the entity.\n" + e.getMessage(), e);
		} finally {
			close(rs, cst, conn);
		}
		return loaded;
	}

}
