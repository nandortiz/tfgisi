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
        logger.debug("In ElementoReservableBD.create(), input is: {}", json.toString());
        Puesto puesto = (Puesto) ElementoReservableBD.getInstance().addElementoReservable(Json.fromJson(json, Puesto.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(puesto);
        System.out.println("La elemento reservable es: "  +puesto);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,puesto.getUrl());
    }

    public Result retrieveAll (int id){
        Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In ElementoReservableController.getAllElementosReservables(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result retrieve (int bibliotecaID, int id) {
        Puesto result = (Puesto)ElementoReservableBD.getInstance().getElementoReservable(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Elemento reservable with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In ElementoReservableController.getElementoReservable(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.retrieve(), delete ElementoReservable with id: {}",id);
        if (!ElementoReservableBD.getInstance().deleteElementoReservable(id)) {
            return notFound(ApplicationUtil.createResponse("ElementoReservable with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Elemento reservable with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioInfoPuesto cambioInfoPuesto = (CambioInfoPuesto) ElementoReservableBD.getInstance().modifyElementoReservable(Json.fromJson(json, CambioInfoPuesto.class),id);
        if (cambioInfoPuesto == null) {
            return notFound(ApplicationUtil.createResponse("Elemento reservable not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioInfoPuesto);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Puesto puesto = (Puesto) ElementoReservableBD.getInstance().update(Json.fromJson(json, Puesto.class),id);
        logger.debug("In ElementoReservableController.update(), elemento reservable  is: {}", puesto);

        if (puesto == null) {
            return notFound(ApplicationUtil.createResponse("Elemento reservable not found", false));
        }

        JsonNode jsonObject = Json.toJson(puesto);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}
