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
        logger.debug("In ElementoReservableBD.create(), input is: {}", json.toString());
        Sala sala = (Sala) ElementoReservableBD.getInstance().addElementoReservable(Json.fromJson(json, Sala.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(sala);
        System.out.println("El elemento reservable es: "  +sala);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
    }


    public Result retrieve (int bibliotecaID, int id) {
        Sala result = (Sala) ElementoReservableBD.getInstance().getElementoReservable(id);

        if  (result == null) {                                  //TODO frase de abajo se podría dejar con "Sala de id x not found"
            return notFound(ApplicationUtil.createResponse("Elemento reservable with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In ElementoReservableController.getElementoReservable(id), result is: {}", result.toString());
                        //TODO no sería "In ElementoReservableBD?????"
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (int id){
       Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In ElementoReservableController.getAllElementosReservables(), result is: {}",result.toString());
                //TODO IDEM EltoResBD?????
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.retrieve(), delete Elemento Reservable with id: {}",id);
        if (!ElementoReservableBD.getInstance().deleteElementoReservable(id)) {
            return notFound(ApplicationUtil.createResponse("Elemento Reservable with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Elemento Reservable with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioAforoSala cambioAforoSala = (CambioAforoSala) ElementoReservableBD.getInstance().modifyElementoReservable(Json.fromJson(json, CambioAforoSala.class),id);
        if (cambioAforoSala == null) {
            return notFound(ApplicationUtil.createResponse("Elemento reservable not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioAforoSala);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Sala sala = (Sala)ElementoReservableBD.getInstance().update(Json.fromJson(json, Sala.class),id);
        logger.debug("In ElementoReservableController.update(), elemento reservable  is: {}", sala);

        if (sala == null) {
            return notFound(ApplicationUtil.createResponse("Elemento reservable not found", false));
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
