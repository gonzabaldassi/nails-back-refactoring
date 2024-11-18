package jsges.nails.controller.items;
import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
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

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class LineController {
    private static final Logger logger = LoggerFactory.getLogger(LineController.class);
    @Autowired
    private ILineService modelService;

    /*public LineaController() {

    }*/
    /*
    @GetMapping({"/lineas"})
    public List<Linea> getAll() {
        logger.info("enta en  traer todas las lineas");
        List<LineaDTO> listadoDTO    =  new ArrayList<>();
        List<Linea>  list    = modelService.listar();
        list.forEach((model) -> {
            listadoDTO.add(new LineaDTO(model));
        });
        return list;
    }*/

    @GetMapping({"/line"})
    public ResponseEntity<?> getLines(){
        try {
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error showing the lines:" + e.getMessage());
        }
    }

    /*
    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getLineById(@PathVariable Integer id){
        Linea linea = modelService.buscarPorId(id);
        if(linea == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        LineaDTO model = new LineaDTO(linea);
        return ResponseEntity.ok(model);
    }*/

    @GetMapping("/line/{id}")
    public ResponseEntity<?> getLineById(@PathVariable Integer id){
        try{

            LineDTO modelDTO = modelService.getModelById(id);

            if (modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Line with id:" + id + ", does not exist");
            }
            return ResponseEntity.ok(modelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error showing the line: " + e.getMessage());
        }
    }

    /*
    @GetMapping({"/lineasPageQuery"})
    public ResponseEntity<Page<LineaDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "${max_page}") int size) {
        List<LineaDTO> listadoDTO    =  new ArrayList<>();
        List<Linea> listado = modelService.listar(consulta);
         listado.forEach((model) -> {
            listadoDTO.add(new LineaDTO(model));
         });

        Page<LineaDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listadoDTO);
        return ResponseEntity.ok().body(bookPage);
    }*/

    @GetMapping({"/linePageQuery"})
    public ResponseEntity<?> getItems(@RequestParam(defaultValue = "") String request, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {

        try{
            List<LineDTO> lines = modelService.getModelByRequest(request);

            Page<LineDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),lines);
            return ResponseEntity.ok().body(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error showing lines"+e.getMessage());
        }
    }

    /*
    @PostMapping("/linea")
    public  ResponseEntity<Linea> agregar(@RequestBody LineaDTO model){
        List<Linea> list = modelService.buscar(model.denomination);
        if (!list.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Linea nuevaLinea = modelService.newModel(model);
        return ResponseEntity.ok(nuevaLinea);
    }*/

    @PostMapping("/line")
    public ResponseEntity<?> createLine(@RequestBody Line model) {
        try{
            LineDTO modelSaved = modelService.createModel(model);

            if (modelSaved == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error adding line");
            }

            return ResponseEntity.ok(modelSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding line" + e.getMessage());
        }
    }

    /*
    @PutMapping("/linea/{id}")
    public ResponseEntity<Linea> actualizar(@PathVariable Integer id,
                                            @RequestBody LineaDTO modelRecibido){
        Linea model = modelService.buscarPorId(modelRecibido.id);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        model.setDenomination(modelRecibido.denomination);
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }*/

    @PutMapping("/line/{id}")
    public ResponseEntity<?> updateLine(@PathVariable Integer id,
                                            @RequestBody Line receivedModel){
        try{
            LineDTO existingModelDTO = modelService.getModelById(id);

            if (existingModelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This line does not exist");
            }

            LineDTO updatedModelDTO = modelService.updateModel(existingModelDTO, receivedModel);

            if(updatedModelDTO == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating line");
            }

            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating line" + e.getMessage());
        }
    }

    /*
    @PutMapping("/lineaEliminar/{id}")
    public ResponseEntity<Linea> eliminar(@PathVariable Integer id){
        Linea model = modelService.buscarPorId(id);
        if (model == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }

        model.asEliminado();
        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }*/

    @DeleteMapping("/line/{id}")
    public ResponseEntity<?> deleteLine(@PathVariable Integer id){

        try{
            LineDTO modelDTO = modelService.getModelById(id);

            if (modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The line with id: " + id + ", does not exist");
            }

            modelService.deleteModel(modelDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting line" + e.getMessage());
        }
    }

}

