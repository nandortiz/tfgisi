package controllers;

import akka.actor.typed.RecipientRef;
import entities.*;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
import com.fasterxml.jackson.databind.JsonNode;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.RecursoExtraBD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


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

    public Result retrieve (Http.Request request, int bibliotecaID, int id) {
        logger.debug("In OrdenadorController.getRecursoExtra(id), retrieve ordenador with id: {}", id);
        Ordenador result = (Ordenador) RecursoExtraBD.getInstance().getRecursoExtra(id);

        if (RecursoExtraBD.getInstance().getRecursoExtra(id) == null) {
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

                    Template template = cfg.getTemplate("ordenadorMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("bibliotecaID", bibliotecaID);
                    mapa.put("ordenadorID", id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("Ordenador with id:" + id + " not found", false));
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

                Template template = cfg.getTemplate("ordenador.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("ordenador", result);
                mapa.put("bibliotecaID", bibliotecaID);
                mapa.put("ordenadorID", id);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            //ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObjects = Json.toJson(RecursoExtraBD.getInstance().getRecursoExtra(id));
            // JsonNode jsonObjects = mapper.convertValue(RecursoExtraBD.getInstance().getRecursoExtra(id),JsonNode.class);

            logger.debug("In OrdenadorController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (Http.Request request, int id){
        Collection<RecursoExtraShort> result = RecursoExtraBD.getInstance().getAllRecursosExtras(id, TipoRecursoExtra.O);
        logger.debug("In OrdenadorController.getAllRecursosExtras(int bibliotecaID, TipoRecursoExtra tipo), result is: {}", result.toString());
        //ObjectMapper mapper = new ObjectMapper();
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

                Template template = cfg.getTemplate("ordenadores.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("ordenadores", result);
                mapa.put("bibliotecaID", id);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        }else {
            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In OrdenadorController.retrieve(), delete ordenador with id: {}",id);
        if (!RecursoExtraBD.getInstance().deleteRecursoExtra(id)) {
            return notFound(ApplicationUtil.createResponse("Ordenador with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Ordenador with id:" + id + " deleted", true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In OrdenadorController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Ordenador ordenador = (Ordenador) RecursoExtraBD.getInstance().update(Json.fromJson(json, Ordenador.class),id);
        logger.debug("In OrdenadorController.update(), ordenador is: {}", ordenador);
        if (ordenador == null) {
            return notFound(ApplicationUtil.createResponse("Ordenador not found", false));
        }
        JsonNode jsonObject = Json.toJson(ordenador);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }
    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In OrdenadorController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioNumSerieOrdenador cambioNumSerie = (CambioNumSerieOrdenador) RecursoExtraBD.getInstance().modifyRecursoExtra(Json.fromJson(json, CambioNumSerieOrdenador.class),id);
        if (cambioNumSerie == null) {
            return notFound(ApplicationUtil.createResponse("Ordenador not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioNumSerie);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }


}
