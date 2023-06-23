package controllers;

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
import services.ElementoReservableBD;
import services.RecursoExtraBD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class LibroController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In RecursoExtraBD.create(), input is: {}", json.toString());
        Libro libro = (Libro) RecursoExtraBD.getInstance().addRecursoExtra(Json.fromJson(json, Libro.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(libro);
        System.out.println("El libro a crear es: "  +libro);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,libro.getUrl());
    }

    public Result retrieveAll (Http.Request request, int id){
        Collection<RecursoExtraShort> result = RecursoExtraBD.getInstance().getAllRecursosExtras(id, TipoRecursoExtra.L);
        logger.debug("In LibroController.getAllRecursosExtras(int bibliotecaID, TipoRecursoExtra tipo), result is: {}", result.toString());
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

                Template template = cfg.getTemplate("libros.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("libros", result);
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

    public Result retrieve (Http.Request request, int bibliotecaID, int id) {
        logger.debug("In LibroController.getRecursoExtra(id), retrieve libro with id: {}", id);
        Libro result = (Libro) RecursoExtraBD.getInstance().getRecursoExtra(id);

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

                    Template template = cfg.getTemplate("libroMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("bibliotecaID", bibliotecaID);
                    mapa.put("libroID", id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("Libro with id:" + id + " not found", false));
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

                Template template = cfg.getTemplate("libro.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("libro", result);
                mapa.put("bibliotecaID", bibliotecaID);
                mapa.put("libroID", id);
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

            logger.debug("In LibroController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.retrieve(), delete libro with id: {}",id);
        if (!RecursoExtraBD.getInstance().deleteRecursoExtra(id)) {
            return notFound(ApplicationUtil.createResponse("Libro with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Libro with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioIsbnLibro cambioIsbnLibro = (CambioIsbnLibro) RecursoExtraBD.getInstance().modifyRecursoExtra(Json.fromJson(json, CambioIsbnLibro.class),id);
        if (cambioIsbnLibro == null) {
            return notFound(ApplicationUtil.createResponse("Libro not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioIsbnLibro);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In LibroController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Libro libro = (Libro) RecursoExtraBD.getInstance().update(Json.fromJson(json, Libro.class),id);
        logger.debug("In LibroController.update(), libro  is: {}", libro);

        if (libro == null) {
            return notFound(ApplicationUtil.createResponse("Libro not found", false));
        }

        JsonNode jsonObject = Json.toJson(libro);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}