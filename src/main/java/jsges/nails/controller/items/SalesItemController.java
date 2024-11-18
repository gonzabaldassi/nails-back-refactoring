package jsges.nails.controller.items;

import jsges.nails.domain.items.SalesItem;
import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.service.items.ISalesItemService;
import jsges.nails.service.items.ILineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class SalesItemController {
    private static final Logger logger = LoggerFactory.getLogger(SalesItemController.class);
    @Autowired
    private ISalesItemService modelService;

    @Autowired
    private ILineService lineaService;

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

    @GetMapping("/item")
    public ResponseEntity<?> getItem(){
        try{
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing articles:"+e.getMessage());
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

    @GetMapping("/item/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Integer id){
        try{

            SalesItemDTO modelDTO = modelService.getModelById(id);

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


    @GetMapping("/itemPageQuery")
    public ResponseEntity<?> getItems(@RequestParam(defaultValue = "") String request, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {
        try{
            List<SalesItemDTO> salesItems = modelService.getModelByRequest(request);

            Page<SalesItemDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),salesItems);
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
        newModel.setDenomination(model.denomination);
        newModel.setLinea(lineaService.buscarPorId(idLinea));

        ArticuloVenta modelSave= modelService.guardar(newModel);
        return modelSave;
    }
*/

    @PostMapping("/item")
    public ResponseEntity<?> createItem(@RequestBody SalesItem model){
        try{
            SalesItemDTO modelSaved = modelService.createModel(model);

            if (modelSaved == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error saving item");
            }

            return ResponseEntity.ok(modelSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating item:" + e.getMessage());
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
        model.setDenomination(modelRecibido.denomination);
        model.setLinea(lineaService.buscarPorId(modelRecibido.linea));
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }

     */

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItem(@RequestBody SalesItem receivedModel, @PathVariable Integer id){
        try{

            SalesItemDTO existingModelDTO = modelService.getModelById(id);

            if(existingModelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This item does not exist");
            }

            SalesItemDTO updatedModelDTO = modelService.updateModel(existingModelDTO, receivedModel);

            if(updatedModelDTO == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating item");
            }

            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating item: " + e.getMessage());
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

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){

        try{
            SalesItemDTO modelDTO = modelService.getModelById(id);

            if (modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The item with id:" + id + ", doesnt exist");
            }

            modelService.deleteModel(modelDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting item: " + e.getMessage());
        }
    }
}

