package controllers;

import entities.Sala;
import entities.SalaShort;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SalaBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class SalaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request,int bibliotecaID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In SalaBD.create(), input is: {}", json.toString());
        Sala sala  = SalaBD.getInstance().addSala(Json.fromJson(json, Sala.class), bibliotecaID);
        JsonNode jsonObject = Json.toJson(sala);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
    }




}
