package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import entities.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BibliotecaBD;
import services.UsuarioBD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


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
    /*public Result retrieve (int id) {
        Usuario result = UsuarioBD.getInstance().getUsuario(id);

        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In UsuarioController.getUsuario(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }*/

    public Result retrieve (Http.Request request, int id) {
        logger.debug("In UsuarioController.getUsuario(id), retrieve usuario with id: {}", id);
        Usuario result = UsuarioBD.getInstance().getUsuario(id);
        if (UsuarioBD.getInstance().getUsuario(id) == null) {
            if (request.accepts("text/html")) {
                String output = "error";
                try {


                    Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                    cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                    cfg.setDefaultEncoding("UTF-8");
                    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                    cfg.setLogTemplateExceptions(false);

                    cfg.setWrapUncheckedExceptions(true);
                    cfg.setFallbackOnNullLoopVariable(false);
                    cfg.setNumberFormat("computer");

                    Template template = cfg.getTemplate("usuarioMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("usuarioID", result);
                    mapa.put("usuarioID", id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {
                return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
            }
        }
        if (request.accepts("text/html")) {
            String output = "error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("usuario.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("usuario", result);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        }        else {
            JsonNode jsonObjects = Json.toJson(UsuarioBD.getInstance().getUsuario(id));
            logger.debug("In UsuarioController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
            //return ok("<html> <body><h1>Esto es una prueba"+ id +" </h1></body></html>").as("text/html");

        }
    }

/*
    public Result retrieveAll (){
        Collection<UsuarioShort> result = UsuarioBD.getInstance().getAllUsuarios();

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In UsuarioController.getAllUsuarios(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }
    */

    public Result retrieveAll (Http.Request request){
        Collection<UsuarioShort> result = UsuarioBD.getInstance().getAllUsuarios();
        logger.debug("In UsuarioController.getAllUsuarios(), result is: {}", result.toString());

        if (request.accepts("text/html")) {
            String output = "error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("usuarios.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("usuarios", result);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        }else {

            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            //return ok(ApplicationUtil.createResponse(jsonData, true));

            //ObjectMapper mapper = new ObjectMapper();

            //  JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));

        }

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
