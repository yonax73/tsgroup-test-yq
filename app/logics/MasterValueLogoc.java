package logics;

import java.util.List;

import models.MasterValue;
import daos.MasterValueDAO;

public class MasterValueLogoc {

	public static List<MasterValue> load(MasterValue masterValue) {
		return MasterValueDAO.load(masterValue);
	}

}
