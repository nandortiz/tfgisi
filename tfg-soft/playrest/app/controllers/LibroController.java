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
import services.RecursoExtraBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class LibroController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In RecursoExtraBD.create(), input is: {}", json.toString());
        Libro libro = (Libro) RecursoExtraBD.getInstance().addRecursoExtra(Json.fromJson(json, Libro.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(libro);
        System.out.println("El libro a crear es: "  +libro);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,libro.getUrl());
    }

    public Result retrieveAll (int id){
        Collection<RecursoExtraShort> result = RecursoExtraBD.getInstance().getAllRecursosExtras(id, TipoRecursoExtra.L);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In LibroController.getAllRecursosExtras(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result retrieve (int bibliotecaID, int id) {
        Libro result = (Libro)RecursoExtraBD.getInstance().getRecursoExtra(id);
        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Libro with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In LibroController.getRecursoExtra(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.retrieve(), delete libro with id: {}",id);
        if (!RecursoExtraBD.getInstance().deleteRecursoExtra(id)) {
            return notFound(ApplicationUtil.createResponse("Libro with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Libro with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioIsbnLibro cambioIsbnLibro = (CambioIsbnLibro) RecursoExtraBD.getInstance().modifyRecursoExtra(Json.fromJson(json, CambioIsbnLibro.class),id);
        if (cambioIsbnLibro == null) {
            return notFound(ApplicationUtil.createResponse("Libro not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioIsbnLibro);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Libro libro = (Libro) RecursoExtraBD.getInstance().update(Json.fromJson(json, Libro.class),id);
        logger.debug("In LibroController.update(), libro  is: {}", libro);

        if (libro == null) {
            return notFound(ApplicationUtil.createResponse("Libro not found", false));
        }

        JsonNode jsonObject = Json.toJson(libro);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}