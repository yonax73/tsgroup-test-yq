package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.MasterValue;
import play.Logger;
import play.db.DB;

public class MasterValueDAO extends DAO {

	public static List<MasterValue> load(MasterValue masterValue) {
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		List<MasterValue> masterValues = new ArrayList<>();
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_master_values_LOAD(?)");
			cst.setInt(1, masterValue.getMasterId());
			rs = cst.executeQuery();
			if (rs.next()) {

				do {
					MasterValue m = new MasterValue();
					m.setId(rs.getInt(1));
					m.setValue1(rs.getString(2));
					m.setValue2(rs.getString(3));
					masterValues.add(m);
				} while (rs.next());
			}
		} catch (Exception e) {
			Logger.error("An error has been occurred tryning loading the mastervalues.\n" + e.getMessage(), e);
		} finally {
			close(rs, cst, conn);
		}
		return masterValues;
	}
}
