package controllers;

import java.util.ArrayList;
import java.util.Map;

import logics.CollectionLogic;
import models.Collection;
import models.Group;
import models.MasterValue;
import models.ProgrammingBox;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Constant;

public class CollectionControl extends Controller {

	public Result changeGroup() {
		try {
			String result = "No se ha podido completar la solicitud. \n";
			final Map<String, String[]> data = request().body().asFormUrlEncoded();
			if (data != null) {
				ProgrammingBox programmingBox = new ProgrammingBox();
				programmingBox.setGroups(new ArrayList<Group>());
				Group group = new Group();
				group.setId(Integer.parseInt(data.get("groupCollectionId")[0]));
				Collection collection = new Collection();
				collection.setId(Integer.parseInt(data.get("collectionId")[1]));
				collection.setRow(Integer.parseInt(data.get("row")[0]));
				group.setCollection(collection);
				if (collection.getId() > 0) {
					programmingBox.getGroups().add(group);
				}
				Group group2 = new Group();
				group2.setId(Integer.parseInt(data.get("groupCollectionId")[1]));
				Collection collection2 = new Collection();
				collection2.setId(Integer.parseInt(data.get("collectionId")[0]));
				collection2.setRow(Integer.parseInt(data.get("row")[1]));
				group2.setCollection(collection2);
				if (collection2.getId() > 0) {
					programmingBox.getGroups().add(group);
				}
				if (CollectionLogic.changeGroup(programmingBox)) {
					result = Constant.SUCCESS;
				} else {
					result = Constant.ERROR;
				}
			}
			return ok(result);
		} catch (Exception e) {
			Logger.error("Ha ocurrido un error intentando guardar los datos de la coleccion \n" + e.getMessage(), e);
			return badRequest("Ha ocurrido un error intentando guardar los datos de la coleccion ( " + e.getMessage() + " )");
		}
	}

	public Result save() {
		try {
			String result = "No se ha podido completar la solicitud. \n";
			final Map<String, String[]> data = request().body().asFormUrlEncoded();
			if (data != null) {
				Group group = new Group();
				group.setId(Integer.parseInt(data.get("groupCollectionId")[0]));
				Collection collection = new Collection();
				collection.setId(Integer.parseInt(data.get("collectionId")[0]));
				collection.setRow(Integer.parseInt(data.get("row")[0]));
				collection.setName(data.get("collectionName")[0]);
				collection.setBrand(data.get("brand")[0]);
				collection.setColor(new MasterValue(Integer.parseInt(data.get("color")[0]), 0));
				collection.setType(new MasterValue(Integer.parseInt(data.get("type")[0]), 0));
				group.setCollection(collection);
				if (CollectionLogic.save(group)) {
					result = String.valueOf(collection.getId());
				} else {
					result = Constant.ERROR;
				}
			}
			return ok(result);
		} catch (Exception e) {
			Logger.error("Ha ocurrido un error intentando guardar los datos de la coleccion \n" + e.getMessage(), e);
			return badRequest("Ha ocurrido un error intentando guardar los datos de la coleccion ( " + e.getMessage() + " )");
		}
	}

	public Result delete(int id) {
		try {
			String result = "No se ha podido completar la solicitud. \n";
			if (CollectionLogic.delete(new Collection(id))) {
				result = Constant.SUCCESS;
			} else {
				result = Constant.ERROR;
			}
			return ok(result);
		} catch (Exception e) {
			Logger.error("Ha ocurrido un error intentando eliminar los datos de la coleccion \n" + e.getMessage(), e);
			return badRequest("Ha ocurrido un error intentando eliminar los datos de la coleccion ( " + e.getMessage() + " )");
		}
	}

	public Result load(int id) {
		try {
			String result = "No se ha podido completar la solicitud. \n";
			Collection collection = new Collection(id);
			if (CollectionLogic.load(collection)) {
				result = Json.stringify(Json.toJson(collection));
			} else {
				result = Constant.ERROR;
			}
			return ok(result);
		} catch (Exception e) {
			Logger.error("Ha ocurrido un error intentando cargar los datos de la coleccion \n" + e.getMessage(), e);
			return badRequest("Ha ocurrido un error intentando cargar los datos de la coleccion ( " + e.getMessage() + " )");
		}
	}

}
