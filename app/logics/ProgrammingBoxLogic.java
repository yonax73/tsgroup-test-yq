package logics;

import models.Group;
import models.ProgrammingBox;
import daos.GroupDAO;
import daos.ProgrammingBoxDAO;

public class ProgrammingBoxLogic {

	public static ProgrammingBox load() {
		ProgrammingBox programmingBox = new ProgrammingBox();
		if (ProgrammingBoxDAO.loadMaxRow(programmingBox)) {
			if (GroupDAO.load(programmingBox)) {
				for (Group group : programmingBox.getGroups()) {
					group.createCollection(programmingBox.getTotalRows());
					CollectionLogic.loadByGroup(group);
				}
			}
		}
		return programmingBox;
	}
}
