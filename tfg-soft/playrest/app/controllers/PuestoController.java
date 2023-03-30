package controllers;

import entities.*;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BibliotecaBD;
import services.PuestoBD;
import services.SalaBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class PuestoController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In PuestoBD.create(), input is: {}", json.toString());
        Puesto puesto = PuestoBD.getInstance().addPuesto(Json.fromJson(json, Puesto.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(puesto);
        System.out.println("La puesto es: "  +puesto);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,puesto.getUrl());
    }

    public Result retrieveAll (int id){
        Collection<PuestoShort> result = PuestoBD.getInstance().getAllPuestos(id);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In PuestoController.getAllPuestos(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result retrieve (int bibliotecaID, int id) {
        Puesto result = PuestoBD.getInstance().getPuesto(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Puesto with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In PuestoController.getPuesto(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In PuestoController.retrieve(), delete Puesto with id: {}",id);
        if (!PuestoBD.getInstance().deletePuesto(id)) {
            return notFound(ApplicationUtil.createResponse("Puesto with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Puesto with id:" + id + " deleted", true));
    }


}
