package controllers;

import entities.*;

import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ElementoReservableBD;
import services.PuestoBD;
import services.SalaBD;
import utils.ApplicationUtil;

import javax.swing.text.Element;
import java.sql.SQLException;
import java.util.Collection;


public class ElementoReservableController extends Controller {

/*

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
*/

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ElementoReservableBD.create(), input is: {}", json.toString());
        ElementoReservable elementoReservable = Json.fromJson(json, ElementoReservable.class);

        if (elementoReservable instanceof Sala) {
            Sala sala = SalaBD.getInstance().addSala((Sala) elementoReservable, bibliotecaID );
            JsonNode jsonObject = Json.toJson(sala);
            return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
        } else if (elementoReservable instanceof Puesto) {
            Puesto puesto = PuestoBD.getInstance().addPuesto((Puesto) elementoReservable, bibliotecaID );
            JsonNode jsonObject = Json.toJson(puesto);
            return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,puesto.getUrl());
        } else {
            return badRequest(ApplicationUtil.createResponse("Invalid data", false));
        }
    }



private static final Logger logger = LoggerFactory.getLogger("controller");
public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
    logger.debug("In ElementoReservableController.update()");
    JsonNode json = request.body().asJson();

    if (json == null) {
        return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
    }
    CambioDescripcion cambioDescripcion = (CambioDescripcion) ElementoReservableBD.getInstance().modifyElementoReservable(Json.fromJson(json, CambioDescripcion.class),id);
    if (cambioDescripcion == null) {
        return notFound(ApplicationUtil.createResponse("Elemento reservable not found", false));
    }

    JsonNode jsonObject = Json.toJson(cambioDescripcion);
    return ok(ApplicationUtil.createResponse(jsonObject, true));
}

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.retrieve(), delete ElementoReservable with id: {}",id);
        if (!ElementoReservableBD.getInstance().deleteElementoReservable(id)) {
            return notFound(ApplicationUtil.createResponse("ElementoReservable with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("ElementoRerservable with id:" + id + " deleted", true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In ElementoReservableController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        ElementoReservable elementoReservable = ElementoReservableBD.getInstance().update(Json.fromJson(json, ElementoReservable.class),id);
        logger.debug("In ElementoReservableController.update(), elementoReservable  is: {}", elementoReservable);

        if (elementoReservable == null) {
            return notFound(ApplicationUtil.createResponse("ElementoReservable not found", false));
        }

        JsonNode jsonObject = Json.toJson(elementoReservable);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result retrieve (int bibliotecaID, int id) {
        ElementoReservable result = ElementoReservableBD.getInstance().getElementoReservable(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("ElementoReservable with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In ElementoReservableController.getElementoReservable(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }
   /* public Result retrieveAll (int id){
        Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id;

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In ElementoReservableController.getAllElementosReservables(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


*/
}
