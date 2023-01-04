package controllers;

import entities.Biblioteca;

//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateExceptionHandler;
import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.BibliotecaBD;
import utils.ApplicationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class BibliotecaController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In BibliotecaBD.create(), input is: {}", json.toString());
        Biblioteca biblioteca = BibliotecaBD.getInstance().addBiblioteca(Json.fromJson(json, Biblioteca.class));
        JsonNode jsonObject = Json.toJson(biblioteca);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,biblioteca.getUrl());
    }
/**
    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BibliotecaController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Biblioteca biblioteca = BibliotecaBD.getInstance().updateBiblioteca(Json.fromJson(json, Biblioteca.class),id);
        logger.debug("In BibliotecaController.update(), biblioteca  is: {}",biblioteca);
        if (biblioteca == null) {
            return notFound(ApplicationUtil.createResponse("Biblioteca not found", false));
        }

        JsonNode jsonObject = Json.toJson(biblioteca);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    /**public Result modify(Http.Request request, int id) throws SQLException, ClassNotFoundException{
        logger.debug("In BibliotecaController.modify()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        CambioHorario cam = BibliotecaBD.getInstance().cambioBiblioteca(Json.fromJson(json, CambioHorario.class),id);

        //Biblioteca biblioteca = BibliotecaBD.getInstance().cambioBiblioteca(Json.fromJson(json, CambioHorario.class),id);

        if (cam == null) {
            return notFound(ApplicationUtil.createResponse("Biblioteca not found", false));
        }

        JsonNode jsonObject = Json.toJson(cam);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    //public Result retrieve(int id) {
    // ArrayLis<Biblioteca> result = BibliotecaBD.getInstance().getBiblioteca(id);
    //   logger.debug("In BibliotecaController.getBiblioteca(id), result is: {}",result.toString());
    // ObjectMapper mapper = new ObjectMapper();

    // JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
    // return ok(ApplicationUtil.createResponse(jsonData, true));
    // }





    public Result retrieve(int id) {
        logger.debug("In BibliotecaController.retrieve(), retrieve biblioteca with id: {}",id);
        Biblioteca result = BibliotecaBD.getInstance().getBiblioteca(id);
        //  if (BibliotecaBD.getInstance().getBiblioteca(id) == null) {
            /*if (request.accepts("text/html")) {
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

                    Template template = cfg.getTemplate("bibliotecaMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("bibliotecaID", id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("biblioteca with id:" + id + " not found", false));
            }
        }

        if (request.accepts("text/html")) {
            String output="error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("biblioteca.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("biblioteca", result);
                mapa.put("listaDisponibilidadBiblioteca", result.getListaDisponibilidadBiblioteca());
                mapa.put("listaPuesto", result.getListaPuesto());
                mapa.put("listaSala", result.getListaSala());
                template.process(mapa, sw);
                output=sw.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {



        JsonNode jsonObjects = Json.toJson(BibliotecaBD.getInstance().getBiblioteca(id));

        logger.debug("In BibliotecaController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));

        // }


    }
/**

    public Result listBibliotecas() throws IOException{//}, TemplateException {
        Collection<BibliotecaShort> result = BibliotecaBD.getInstance().getAllBibliotecas();
        logger.debug("In BibliotecaController.listBibliotecas(), result is: {}", result.toString());
        //ObjectMapper mapper = new ObjectMapper();

      /*  if (request.accepts("text/html")) {
            String output="error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("bibliotecas.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("bibliotecas", result);
                template.process(mapa, sw);
                output=sw.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
        JsonNode jsonData = Json.toJson(result);
        //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

        //  }
    }

    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BibliotecaController.retrieve(), delete Biblioteca with id: {}",id);
        if (!BibliotecaBD.getInstance().deleteBiblioteca(id)) {
            return notFound(ApplicationUtil.createResponse("Biblioteca with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Biblioteca with id:" + id + " deleted", true));
    }


    public Result methodNotAllowed() {

        return status(405,ApplicationUtil.createResponse("Method Not Allowed", true));
    }
**/

}
