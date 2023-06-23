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

import javax.swing.text.Element;
import java.io.StringWriter;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class PuestoController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(int bibliotecaID, Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        System.out.println(json);
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ElementoReservableBD.create(), input is: {}", json.toString());
        Puesto puesto = (Puesto) ElementoReservableBD.getInstance().addElementoReservable(Json.fromJson(json, Puesto.class), bibliotecaID );
        JsonNode jsonObject = Json.toJson(puesto);
        System.out.println("El puesto a crear es: "  +puesto);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,puesto.getUrl());
    }

    /*
    public Result retrieveAll (int id){
        Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id, TipoElementoReservable.P);

        JsonNode jsonObjects = Json.toJson(result);
        logger.debug("In PuestoController.getAllElementosReservables(), result is: {}",result.toString());

        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }*/


    public Result retrieveAll (Http.Request request, int id){
        Collection<ElementoReservableShort> result = ElementoReservableBD.getInstance().getAllElementosReservables(id, TipoElementoReservable.P);
        logger.debug("In PuestoController.getAllElementosReservables(int bibliotecaID, TipoElementoReservable tipo), result is: {}", result.toString());
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

                Template template = cfg.getTemplate("puestos.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("puestos", result);
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

    /*public Result retrieve (int bibliotecaID, int id) {
        Puesto result = (Puesto)ElementoReservableBD.getInstance().getElementoReservable(id);
        if  (result == null) {
            return notFound(ApplicationUtil.createResponse("Puesto with id:" + id + " not found", false));
        } else {
            JsonNode jsonObjects = Json.toJson(result);
            logger.debug("In PuestoController.getElementoReservable(id), result is: {}", result.toString());

            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }
    */

    public Result retrieve (Http.Request request, int bibliotecaID, int id) {
        logger.debug("In PuestoController.getElementoReservable(id), retrieve puesto with id: {}", id);
        Puesto result = (Puesto) ElementoReservableBD.getInstance().getElementoReservable(id);

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

                    Template template = cfg.getTemplate("puestoMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("bibliotecaID", bibliotecaID);
                    mapa.put("puestoID", id);
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

                Template template = cfg.getTemplate("puesto.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("puesto", result);
                mapa.put("bibliotecaID", bibliotecaID);
                mapa.put("puestoID", id);
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

            logger.debug("In PuestoController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }




    public Result delete(int bibliotecaID, int id ) throws SQLException, ClassNotFoundException {
        logger.debug("In PuestoController.retrieve(), delete puesto with id: {}",id);
        if (!ElementoReservableBD.getInstance().deleteElementoReservable(id)) {
            return notFound(ApplicationUtil.createResponse("Puesto with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Puesto with id:" + id + " deleted", true));
    }

    public Result modify(int id, Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In PuestoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioInfoPuesto cambioInfoPuesto = (CambioInfoPuesto) ElementoReservableBD.getInstance().modifyElementoReservable(Json.fromJson(json, CambioInfoPuesto.class),id);
        if (cambioInfoPuesto == null) {
            return notFound(ApplicationUtil.createResponse("Puesto not found", false));
        }

        JsonNode jsonObject = Json.toJson(cambioInfoPuesto);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In PuestoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Puesto puesto = (Puesto) ElementoReservableBD.getInstance().update(Json.fromJson(json, Puesto.class),id);
        logger.debug("In PuestoController.update(), puesto  is: {}", puesto);

        if (puesto == null) {
            return notFound(ApplicationUtil.createResponse("Puesto not found", false));
        }

        JsonNode jsonObject = Json.toJson(puesto);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}
