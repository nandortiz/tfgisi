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
import services.ReservaBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;

public class ReservaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        System.out.println(json);
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ReservaBD.create(), input is: {}", json.toString());
        Reserva reserva = ReservaBD.getInstance().addReserva(Json.fromJson(json, Reserva.class));

        JsonNode jsonObject = Json.toJson(reserva);
        System.out.println("La reserva es: " + reserva);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION, reserva.getUrl());
    }

    public Result retrieve (int reservaID) {
        Reserva result = ReservaBD.getInstance().getReserva(reservaID);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In ReservaController.getReserva(reservaID), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (){
        Collection<ReservaShort> result = ReservaBD.getInstance().getAllReservas();

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In ReservaController.getAllReservas(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result delete(int reservaID) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaController.retrieve(), delete reserva with id: {}",reservaID);
        if (!ReservaBD.getInstance().deleteReserva(reservaID)) {
            return notFound(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " deleted", true));
    }

    public Result modify(int reservaID, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioFecha cambioFecha = (CambioFecha) ReservaBD.getInstance().modifyReserva(Json.fromJson(json, CambioFecha.class),reservaID);
        if (cambioFecha == null) {
            return notFound(ApplicationUtil.createResponse("Reserva not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioFecha);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int reservaID) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Reserva reserva = ReservaBD.getInstance().update(Json.fromJson(json, Reserva.class),reservaID);
        logger.debug("In ReservaController.update(), reserva  is: {}", reserva);
        if (reserva == null) {
            return notFound(ApplicationUtil.createResponse("Reserva not found", false));
        }

        JsonNode jsonObject = Json.toJson(reserva);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }


}