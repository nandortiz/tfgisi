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
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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
        System.out.println("La sala es: "  +sala);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,sala.getUrl());
    }


    public Result retrieve (Http.Request request, int bibliotecaID, int id) {
        logger.debug("In SalaController.getElementoReservable(id), retrieve puesto with id: {}", id);
        Sala result = (Sala) ElementoReservableBD.getInstance().getElementoReservable(id);

        if (ElementoReservableBD.getInstance().getElementoReservable(id) == null) {
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

                    Template template = cfg.getTemplate("salaMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("bibliotecaID", bibliotecaID);
                    mapa.put("salaID", id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("Puesto with id:" + id + " not found", false));
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

                Template template = cfg.getTemplate("sala.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("sala", result);
                mapa.put("bibliotecaID", bibliotecaID);
                mapa.put("salaID", id);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            //ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObjects = Json.toJson(ElementoReservableBD.getInstance().getElementoReservable(id));
            // JsonNode jsonObjects = mapper.convertValue(ElementoReservableBD.getInstance().getElementoReservable(id),JsonNode.class);

            logger.debug("In SalaController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result retrieveAll (Http.Request request, int id){
        Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id, TipoElementoReservable.S);
        logger.debug("In SalaController.getAllElementosReservables(int bibliotecaID, TipoElementoReservable tipo), result is: {}", result.toString());
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

                Template template = cfg.getTemplate("salas.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("salas", result);
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
        logger.debug("In SalaController.retrieve(), delete sala with id: {}",id);
        if (!ElementoReservableBD.getInstance().deleteElementoReservable(id)) {
            return notFound(ApplicationUtil.createResponse("Sala with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Sala with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In SalaController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioAforoSala cambioAforoSala = (CambioAforoSala) ElementoReservableBD.getInstance().modifyElementoReservable(Json.fromJson(json, CambioAforoSala.class),id);
        if (cambioAforoSala == null) {
            return notFound(ApplicationUtil.createResponse("Sala not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioAforoSala);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In SalaController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Sala sala = (Sala)ElementoReservableBD.getInstance().update(Json.fromJson(json, Sala.class),id);
        logger.debug("In SalaController.update(), sala is: {}", sala);
        if (sala == null) {
            return notFound(ApplicationUtil.createResponse("Sala not found", false));
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
