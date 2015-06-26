package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import models.ProgrammingBox;
import play.Logger;
import play.db.DB;

public class ProgrammingBoxDAO extends DAO {

	public static boolean loadMaxRow(ProgrammingBox programmingBox) {
		boolean loaded = false;
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_programming_box_MAX_ROW()");
			rs = cst.executeQuery();
			if (rs.next()) {
				programmingBox.setTotalRows(rs.getInt(1));
			}
			loaded = true;
		} catch (Exception e) {
			Logger.error("An error has been occurred tryning loading the programmingBox.\n" + e.getMessage(), e);
		} finally {
			close(rs, cst, conn);
		}
		return loaded;
	}

}
