package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import logics.GroupLogic;
import models.Group;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Constant;

public class GroupControl extends Controller {

	public Result index() {
		return ok(views.html.index.render());
	}

	public Result save() {
		try {
			String result = "No se ha podido completar la solicitud. \n";
			final Map<String, String[]> data = request().body().asFormUrlEncoded();
			if (data != null) {
				Group group = new Group(Integer.parseInt(data.get("groupId")[0]));
				group.setCreationYear(Integer.parseInt(data.get("creationYear")[0]));
				group.setName(data.get("groupName")[0]);
				group.setStarDate(LocalDate.parse(data.get("startDate")[0], DateTimeFormatter.ofPattern(Constant.FORMAT_DATE)).atStartOfDay());
				group.setExpirationDate(LocalDate.parse(data.get("expirationDate")[0], DateTimeFormatter.ofPattern(Constant.FORMAT_DATE)).atStartOfDay());
				if (GroupLogic.save(group)) {
					result = String.valueOf(group.getId());
				} else {
					result = Constant.ERROR;
				}
			}
			return ok(result);
		} catch (Exception e) {
			Logger.error("Ha ocurrido un error intentando guardar los datos del grupo \n" + e.getMessage(), e);
			return badRequest("Ha ocurrido un error intentando guardar los datos del grupo ( " + e.getMessage() + " )");
		}
	}

}
