package jsges.nails.controller.services;


import jsges.nails.dto.services.ServiceTypeDTO;
import jsges.nails.domain.services.ServiceType;
import jsges.nails.service.services.IServiceTypeService;
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
public class ServiceTypeController {

    @Autowired
    private IServiceTypeService modelService;


    /*public TipoServicioController() {

    }*/

    /*@GetMapping({"/tiposServicios"})
    public List<TipoServicio> getAll() {
        List<TipoServicio> tipoServicios = this.modelService.listar();
        return tipoServicios;
    }*/

    @GetMapping("/serviceType")
    public ResponseEntity<?> getServiceType(){
        try {
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing service types:"+e.getMessage());
        }
    }

       /* @GetMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicio> getPorId(@PathVariable Integer id){
        TipoServicio cliente = modelService.buscarPorId(id);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        return ResponseEntity.ok(cliente);
    }*/

    @GetMapping("/serviceType/{id})")
    public ResponseEntity<?> getServiceTypeById(@PathVariable Integer id){
        try{

            ServiceTypeDTO modelDTO = modelService.getModelById(id);

            if(modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The service type with id:" + id + " does not exist");
            }
            return ResponseEntity.ok(modelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing service type: " + e.getMessage());
        }
    }

    /*@GetMapping({"/tiposServiciosPageQuery"})
    public ResponseEntity<Page<TipoServicio>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "${max_page}") int size) {
        List<TipoServicio> listado = modelService.listar(consulta);
        Page<TipoServicio> bookPage = modelService.findPaginated(PageRequest.of(page, size),listado);
        return ResponseEntity.ok().body(bookPage);
    }*/

    @GetMapping("/serviceTypePageQuery")
    public ResponseEntity<?> getItems(@RequestParam(defaultValue = "") String request, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {
        try{
            List<ServiceTypeDTO> serviceType = modelService.getModelByRequest(request);

            Page<ServiceTypeDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),serviceType);
            return ResponseEntity.ok().body(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing service types:"+e.getMessage());
        }
    }


    /*@PostMapping("/tiposServicios")
    public  ResponseEntity<TipoServicio>  agregar(@RequestBody TipoServicioDTO model){
        List<TipoServicio> list = modelService.buscar(model.denomination);
        if (!list.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
           // throw new RecursoNoEncontradoExcepcion("Ya existe una linea con la denomination: " + model.denomination);
        }

        TipoServicio nuevoModelo = modelService.newModel(model);
        return ResponseEntity.ok(nuevoModelo);
    }*/

    @PostMapping("/serviceType")
    public ResponseEntity<?> createServiceType(@RequestBody ServiceType model){
        try {
            ServiceTypeDTO modelSaved = modelService.createModel(model);

            if (modelSaved == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error saving service type");
            }
            return ResponseEntity.ok(modelSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating service type: " + e.getMessage());
        }
    }

    /*@PutMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicio> actualizar(@PathVariable Integer id,
                                                   @RequestBody TipoServicio modelRecibido){
        TipoServicio model = modelService.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        modelService.guardar(modelRecibido);
        return ResponseEntity.ok(modelRecibido);
    }*/

    @PutMapping("/serviceType/{id}")
    public ResponseEntity<?> updateServiceType(@RequestBody ServiceType modelRecibido, @PathVariable Integer id){
        try{
            ServiceTypeDTO existingModelDTO = modelService.getModelById(id);

            if (existingModelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This service type does not exist");
            }

            ServiceTypeDTO updatedModelDTO = modelService.updateModel(existingModelDTO, modelRecibido);

            if(updatedModelDTO == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating service type");
            }
            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating service type: " + e.getMessage());
        }
    }

    /*@PutMapping("/tipoServicioEliminar/{id}")
    public ResponseEntity<TipoServicio> eliminar(@PathVariable Integer id){
        TipoServicio model = modelService.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        model.setEstado(1);

        modelService.guardar(model);
        return ResponseEntity.ok(model);
    }*/

    @DeleteMapping("/serviceType/{id}")
    public ResponseEntity<?> deleteServiceType(@PathVariable Integer id){
        try{
            ServiceTypeDTO modelDTO = modelService.getModelById(id);

            if (modelDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The service type with id " + id + " does not exist");
            }

            modelService.deleteModel(modelDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting service type: " + e.getMessage());
        }
    }
}

