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






}
