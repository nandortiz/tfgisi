package controllers;

import entities.Biblioteca;
import entities.Sala;
import entities.SalaShort;
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
import services.SalaBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class SalaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In SalaBD.create(), input is: {}", json.toString());
        Sala sala = SalaBD.getInstance().addSala(Json.fromJson(json, Sala.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(sala);
        System.out.println("La sala es: "  +sala);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
    }

    public Result retrieveAll (int id){
       Collection<SalaShort> result = SalaBD.getInstance().getAllSalas(id);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In SalaController.getAllSalas(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result retrieve (int bibliotecaID, int id) {
        Sala result = SalaBD.getInstance().getSala(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Sala with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In SalaController.getSala(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In SalaController.retrieve(), delete Sala with id: {}",id);
        if (!SalaBD.getInstance().deleteSala(id)) {
            return notFound(ApplicationUtil.createResponse("Sala with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Sala with id:" + id + " deleted", true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In SalaController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Sala sala = SalaBD.getInstance().update(Json.fromJson(json, Sala.class),id);
        logger.debug("In SalaController.update(), sala  is: {}", sala);
        if (sala == null) {
            return notFound(ApplicationUtil.createResponse("Sala not found", false));
        }

        JsonNode jsonObject = Json.toJson(sala);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

  /*  public Result create(Http.Request request,int bibliotecaID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In SalaBD.create(), input is: {}", json.toString());
        Sala sala  = SalaBD.getInstance().addSala(Json.fromJson(json, Sala.class), bibliotecaID);
        JsonNode jsonObject = Json.toJson(sala);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
    }
*/



}
