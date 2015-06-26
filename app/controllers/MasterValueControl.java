package controllers;

import logics.MasterValueLogoc;
import models.Collection;
import models.MasterValue;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class MasterValueControl extends Controller {

	public Result typeCollections() {
		return ok(Json.stringify(Json.toJson(MasterValueLogoc.load(new MasterValue(Collection.TYPE)))));
	}

	public Result colors() {
		return ok(Json.stringify(Json.toJson(MasterValueLogoc.load(new MasterValue(Collection.COLOR)))));
	}
}
