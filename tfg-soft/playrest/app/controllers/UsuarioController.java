package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BibliotecaBD;
import services.UsuarioBD;
import utils.ApplicationUtil;

import java.sql.SQLException;
import java.util.Collection;


public class UsuarioController extends  Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In UsuarioBD.create(), input is: {}", json.toString());
        Usuario usuario = UsuarioBD.getInstance().addUsuario(Json.fromJson(json, Usuario.class));
        JsonNode jsonObject = Json.toJson(usuario);
        System.out.println("El usuario es: "  +usuario);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,usuario.getUrl());
    }
    public Result retrieve (int id) {
        Usuario result = UsuarioBD.getInstance().getUsuario(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In UsuarioController.getUsuario(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }
    public Result retrieveAll (){
        Collection<UsuarioShort> result = UsuarioBD.getInstance().getAllUsuarios();

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In UsuarioController.getAllUsuarios(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In UsuarioController.retrieve(), delete usuario with id: {}",id);
        if (!UsuarioBD.getInstance().deleteUsuario(id)) {
            return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Usuario with id:" + id + " deleted", true));
    }


    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In UsuarioController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioGrado cambioGrado = UsuarioBD.getInstance().modifyUsuario(Json.fromJson(json, CambioGrado.class),id);
        if (cambioGrado == null) {
            return notFound(ApplicationUtil.createResponse("Usuario not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioGrado);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In UsuarioController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Usuario usuario = UsuarioBD.getInstance().update(Json.fromJson(json, Usuario.class),id);
        logger.debug("In UsuarioController.update(), usuario  is: {}", usuario);
        if (usuario == null) {
            return notFound(ApplicationUtil.createResponse("Usuario not found", false));
        }

        JsonNode jsonObject = Json.toJson(usuario);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }


}
