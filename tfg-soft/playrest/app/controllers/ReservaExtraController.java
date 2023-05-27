/*package controllers;

import play.mvc.Controller;

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
import services.ReservaExtraBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class ReservaExtraController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ReservaExtraBD.create(), input is: {}", json.toString());
        Reserva reserva = ReservaExtraBD.getInstance().addReservaExtra(Json.fromJson(json, Reserva.class));
        JsonNode jsonObject = Json.toJson(reservaExtra);
        System.out.println("La reserva extra es: " + reservaExtra);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION, reserva.getUrl());
    }

    public Result retrieve (int reservaExtraID) {
        Reserva result = ReservaExtraBD.getInstance().getReserva(reservaExtraID);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Reserva extra with id:" + reservaExtraID + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In ReservaController.getReservaExtra(reservaExtraID), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (){
        Collection<ReservaShort> result = ReservaExtraBD.getInstance().getAllReservasExtra();

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In ReservaExtraController.getAllReservasExtra(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result delete(int reservaExtraID) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaExtraController.retrieve(), delete reserva extra with id: {}",reservaExtraID);
        if (!ReservaExtraBD.getInstance().deleteReservaExtra(reservaExtraID)) {
            return notFound(ApplicationUtil.createResponse("Reserva extra with id:" + reservaExtraID + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Reserva extra with id:" + reservaExtraID + " deleted", true));
    }

    public Result modify(int reservaExtraID, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaExtraController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioFecha cambioFecha = (CambioFecha) ReservaExtraBD.getInstance().modifyReservaExtra(Json.fromJson(json, CambioFecha.class),reservaExtraID);
        if (cambioFecha == null) {
            return notFound(ApplicationUtil.createResponse("Reserva extra not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioFecha);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int reservaExtraID) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaExtraController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Reserva reserva = ReservaExtraBD.getInstance().update(Json.fromJson(json, Reserva.class),reservaExtraID);
        logger.debug("In ReservaExtraController.update(), reserva extra  is: {}", reserva);
        if (reserva == null) {
            return notFound(ApplicationUtil.createResponse("Reserva extra not found", false));
        }

        JsonNode jsonObject = Json.toJson(reserva);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }



}*/
