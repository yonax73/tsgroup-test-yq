package logics;

import models.Group;
import daos.GroupDAO;

public class GroupLogic {

	public static boolean save(Group group) {
		boolean saved = false;
		if (group.getId() > 0) {
			saved = GroupDAO.update(group);
		} else {
			saved = GroupDAO.create(group);
		}
		return saved;
	}

}
