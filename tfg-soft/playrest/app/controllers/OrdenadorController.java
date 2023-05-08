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
import services.ElementoReservableBD;
import services.RecursoExtraBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;


public class OrdenadorController extends Controller{

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In RecursoExtraBD.create(), input is: {}", json.toString());
        Ordenador ordenador = (Ordenador) RecursoExtraBD.getInstance().addRecursoExtra(Json.fromJson(json, Ordenador.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(ordenador);
        System.out.println("El ordenador es: "  +ordenador);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,ordenador.getUrl());
    }

    public Result retrieve (int bibliotecaID, int id) {
        Ordenador result = (Ordenador) RecursoExtraBD.getInstance().getRecursoExtra(id);

        if  (result == null) {                                  //TODO frase de abajo se podría dejar con "Ordenador de id x not found"
            return notFound(ApplicationUtil.createResponse("Ordenador with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In OrdenadorController.getRecursoExtra(id), result is: {}", result.toString());
            //TODO no sería "In RecursoExtraBD?????"
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (int id){
        Collection<RecursoExtraShort> result = RecursoExtraBD.getInstance().getAllRecursosExtras(id, TipoRecursoExtra.O);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In OrdenadorController.getAllRecursosExtras(), result is: {}",result.toString());
        //TODO IDEM RecExtraBD?????
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In OrdenadorController.retrieve(), delete ordenador with id: {}",id);
        if (!RecursoExtraBD.getInstance().deleteRecursoExtra(id)) {
            return notFound(ApplicationUtil.createResponse("Ordenador with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Ordenador with id:" + id + " deleted", true));
    }




}
