package logics;

import models.Collection;
import models.Group;
import models.ProgrammingBox;
import daos.CollectionDAO;

public class CollectionLogic {

	public static boolean loadByGroup(Group group) {
		return group != null && group.getId() > 0 && CollectionDAO.loadByGroup(group);

	}

	public static boolean save(Group groupCollection) {
		boolean saved = false;
		Collection collection = groupCollection.getCollection();
		if (collection.getId() > 0) {
			saved = CollectionDAO.update(groupCollection);
		} else {
			saved = CollectionDAO.create(groupCollection);
		}
		return saved;
	}

	public static boolean delete(Collection collection) {
		return collection != null && collection.getId() > 0 && CollectionDAO.delete(collection);
	}

	public static boolean load(Collection collection) {
		return collection != null && collection.getId() > 0 && CollectionDAO.load(collection);
	}

	public static boolean changeGroup(ProgrammingBox programmingBox) {
		return CollectionDAO.changeGroup(programmingBox);
	}

}
