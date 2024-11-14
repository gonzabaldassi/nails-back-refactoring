package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.articulos.IArticuloVentaService;
import jsges.nails.service.articulos.ILineaService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ArticuloVentaController {
    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaController.class);
    @Autowired
    private IArticuloVentaService  modelService;

    @Autowired
    private ILineaService lineaService;

    /*
    public ArticuloVentaController() {

    }*/
/*
    @GetMapping({"/articulos"})
    public List<ArticuloVentaDTO> getAll() {
        logger.info("enta en  traer todas los articulos");
        List<ArticuloVenta> list = modelService.listar();
        List<ArticuloVentaDTO> listadoDTO    =  new ArrayList<>();
        list.forEach((model) -> {
            listadoDTO.add(new ArticuloVentaDTO(model));
        });
        return listadoDTO;
    }
*/

    @GetMapping("/article")
    public ResponseEntity<?> getArticle(){
        try{
            return ResponseEntity.ok(modelService.listar());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing articles:"+e.getMessage());
        }
    }

    @GetMapping("/articlePageQuery")
    public ResponseEntity<?> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "${max_page}") int size) {
        try{
            List<ArticuloVentaDTO> articulos = modelService.listar(consulta);

            Page<ArticuloVentaDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),articulos);
            return ResponseEntity.ok().body(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing article:"+e.getMessage());
        }
    }

/*
    @PostMapping("/articulos")
    public ArticuloVenta agregar(@RequestBody ArticuloVentaDTO model){
        logger.info("entra" );

        Integer idLinea = model.linea;

        ArticuloVenta newModel =  new ArticuloVenta();
        newModel.setDenominacion(model.denominacion);
        newModel.setLinea(lineaService.buscarPorId(idLinea));

        ArticuloVenta modelSave= modelService.guardar(newModel);
        return modelSave;
    }
*/

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody ArticuloVentaDTO model){
        try{
            ArticuloVentaDTO modelSaved = modelService.guardar(model);

            if (modelSaved == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error saving article");
            }

            return ResponseEntity.ok(modelSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating article:" + e.getMessage());
        }
    }



/*
    @DeleteMapping("/articuloEliminar/{id}")
    public ResponseEntity<ArticuloVenta> eliminar(@PathVariable Integer id){
        ArticuloVenta model = modelService.buscarPorId(id);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }

        model.asEliminado();
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }
*/

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Integer id){

        try{
            ArticuloVentaDTO modelDTO = modelService.buscarPorId(id);

            if (modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The article with id:" + id + ", doesnt exist");
            }

            modelService.eliminar(modelDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting client: " + e.getMessage());
        }
    }

    /*
    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> getPorId(@PathVariable Integer id){
        ArticuloVenta articuloVenta = modelService.buscarPorId(id);
        if(articuloVenta == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        ArticuloVentaDTO model = new ArticuloVentaDTO(articuloVenta);
        return ResponseEntity.ok(model);
    }*/

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Integer id){
        try{

            ArticuloVentaDTO modelDTO = modelService.buscarPorId(id);

            if(modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The article with id:"+ id + ", doesnt exist");
            }
            return ResponseEntity.ok(modelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting article: " + e.getMessage());
        }
    }

    /*
    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVenta> actualizar(@PathVariable Integer id,
                                                    @RequestBody ArticuloVentaDTO modelRecibido){
        logger.info("articulo " +modelRecibido);
        ArticuloVenta model = modelService.buscarPorId(id);
        logger.info("articulo " +model);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        logger.info("articulo " +model);
        model.setDenominacion(modelRecibido.denominacion);
        model.setLinea(lineaService.buscarPorId(modelRecibido.linea));
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }

     */

    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@RequestBody ArticuloVentaDTO modelRecibido, @PathVariable Integer id){
        try{

            ArticuloVentaDTO existingModelDTO = modelService.buscarPorId(id);

            if(existingModelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This article does not exist");
            }

            ArticuloVentaDTO updatedModelDTO = modelService.update(existingModelDTO, modelRecibido);

            if(updatedModelDTO == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating article");
            }

            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating client: " + e.getMessage());
        }
    }
}

