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
import services.ReservaBD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ReservaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        System.out.println(json);
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ReservaBD.create(), input is: {}", json.toString());
        Reserva reserva = ReservaBD.getInstance().addReserva(Json.fromJson(json, Reserva.class));

        JsonNode jsonObject = Json.toJson(reserva);
        System.out.println("La reserva es: " + reserva);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION, reserva.getUrl());
    }

    public Result retrieve(Http.Request request, int reservaID) {
        logger.debug("In ReservaController.retrieve(), retrieve Reserva with id: {}", reservaID);
        System.out.println("In ReservaController.retrieve(), retrieve Reserva with id: {}" + reservaID);
        Reserva result = ReservaBD.getInstance().getReserva(reservaID);

        if (ReservaBD.getInstance().getReserva(reservaID) == null) {

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

                    Template template = cfg.getTemplate("reservaMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("reservaID", reservaID);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " not found", false));
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

                Template template = cfg.getTemplate("reserva.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("reserva", result);
                mapa.put("url", result.getUrl());
                mapa.put("usuarioID", result.getUsuarioID());
                mapa.put("elementoReservableID", result.getElementoReservableID());
                mapa.put("fecha", result.getFecha());
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        }else {


            // ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObjects = Json.toJson(ReservaBD.getInstance().getReserva(reservaID));

            //JsonNode jsonObjects = mapper.convertValue(ReservaBD.getInstance().getReserva(reservaID),JsonNode.class);

            logger.debug("In ReservaController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }

    }

    public Result retrieveAll (Http.Request request){
        Collection<ReservaShort> result = ReservaBD.getInstance().getAllReservas();
        logger.debug("In ReservaController.getAllReservas(), result is: {}", result.toString());
        // ObjectMapper mapper = new ObjectMapper();
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

                Template template = cfg.getTemplate("reservas.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("reservas", result);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));

        }
    }

    public Result delete(int reservaID) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaController.retrieve(), delete reserva with id: {}",reservaID);
        if (!ReservaBD.getInstance().deleteReserva(reservaID)) {
            return notFound(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Reserva with id:" + reservaID + " deleted", true));
    }


}