package controllers;

import logics.ProgrammingBoxLogic;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ProgrammingBoxControl extends Controller {

	public Result load() {
		return ok(Json.stringify(Json.toJson(ProgrammingBoxLogic.load())));
	}
}
