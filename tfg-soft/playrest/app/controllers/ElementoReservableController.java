/*package controllers;

import entities.ElementoReservable;

import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ElementoReservableBD;
import utils.ApplicationUtil;

import java.sql.SQLException;


public class ElementoReservableController extends Controller {



    private static final Logger logger = LoggerFactory.getLogger("controller");
    //TODO: public Result create(eltoreservable); si es instance de sala, ve a SalaController, si es instance de puesto, ve a PuestoController
    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ElementoReservableBD.create(), input is: {}", json.toString());
        ElementoReservable elementoReservable = ElementoReservableBD.getInstance().addElementoReservable(Json.fromJson(json, ElementoReservable.class));
        JsonNode jsonObject = Json.toJson(elementoReservable);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,elementoReservable.getUrl());

    }



}
*/