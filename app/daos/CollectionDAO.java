package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import models.Collection;
import models.Group;
import models.MasterValue;
import models.ProgrammingBox;
import play.Logger;
import play.db.DB;

public class CollectionDAO extends DAO {

	public static boolean create(Group groupCollection) {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		Collection collection = groupCollection.getCollection();
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_collections_CREATE(?,?,?,?,?,?,?)");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, groupCollection.getId());
			cst.setInt(3, collection.getType().getId());
			cst.setInt(4, collection.getRow());
			cst.setString(5, collection.getName());
			cst.setString(6, collection.getBrand());
			cst.setInt(7, collection.getColor().getId());
			created = cst.executeUpdate() > 0;
			if (created) {
				collection.setId(cst.getInt(1));
				created = collection.getId() > 0;
			}
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining created the collection.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return created;
	}

	public static boolean update(Group groupCollection) {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		Collection collection = groupCollection.getCollection();
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_collections_UPDATE(?,?,?,?,?,?)");
			cst.setInt(1, collection.getId());
			cst.setInt(2, groupCollection.getId());
			cst.setInt(3, collection.getRow());
			cst.setString(4, collection.getName());
			cst.setString(5, collection.getBrand());
			cst.setInt(6, collection.getColor().getId());
			updated = cst.executeUpdate() > 0;
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining update the collection.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return updated;
	}

	public static boolean delete(Collection collection) {
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_collections_DELETE(?)");
			cst.setInt(1, collection.getId());
			deleted = cst.executeUpdate() > 0;
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining delete the collection.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return deleted;
	}

	public static boolean load(Collection collection) {
		boolean loaded = false;
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_collections_LOAD(?)");
			cst.setInt(1, collection.getId());
			rs = cst.executeQuery();
			if (rs.next()) {
				collection.setRow(rs.getInt(1));
				collection.setName(rs.getString(2));
				collection.setBrand(rs.getString(3));
				collection.setColor(new MasterValue(rs.getInt(4), 0));
				collection.setType(new MasterValue(rs.getInt(5), 0));
			}
			loaded = true;
		} catch (Exception e) {
			Logger.error("An error has been occurred tryning loading the collection.\n" + e.getMessage(), e);
		} finally {
			close(rs, cst, conn);
		}
		return loaded;
	}

	public static boolean loadByGroup(Group groupCollection) {
		boolean loaded = false;
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_collections_LOAD_BY_GROUP(?)");
			cst.setInt(1, groupCollection.getId());
			rs = cst.executeQuery();
			if (rs.next()) {
				do {
					Collection collection = new Collection(rs.getInt(1));
					collection.setRow(rs.getInt(2));
					collection.setName(rs.getString(3));
					collection.setBrand(rs.getString(4));
					collection.setColor(new MasterValue(rs.getInt(5)));
					collection.getColor().setValue2(rs.getString(6));

					groupCollection.getCollections().set(collection.getRow(), collection);
				} while (rs.next());
			}
			loaded = true;
		} catch (Exception e) {
			Logger.error("An error has been occurred tryning loading the groupCollection.\n" + e.getMessage(), e);
		} finally {
			close(rs, cst, conn);
		}
		return loaded;
	}

	public static boolean changeGroup(ProgrammingBox programmingBox) {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			for (Group group : programmingBox.getGroups()) {
				Collection collection = group.getCollection();
				cst = conn.prepareCall("CALL sp_collections_CHANGE_GROUP(?,?,?)");
				cst.setInt(1, group.getId());
				cst.setInt(2, collection.getId());
				cst.setInt(3, collection.getRow());
				updated = cst.executeUpdate() > 0;
			}
			updated = true;
		} catch (Exception e) {
			Logger.error("An error has been ocurred tryining change the group.\n" + e.getMessage(), e);
		} finally {
			close(cst, conn);
		}
		return updated;
	}

}
