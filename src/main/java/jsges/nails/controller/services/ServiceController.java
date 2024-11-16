package jsges.nails.controller.services;
import jsges.nails.dto.services.ServiceDTO;
import jsges.nails.service.services.IServiceService;
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
public class ServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);


    @Autowired
    private IServiceService modelService;

    /*@Autowired
    private IClienteService clienteService;

    @Autowired
    private ITipoServicioService tipoServicioService;

    @Autowired
    private IItemServicioService itemServicioService;*/

    /*
    public ServicioController() {

    }*/


    /*@GetMapping({"/servicios"})
    public List<ServicioDTO> getAll() {
        List<Servicio> servicios = this.modelService.listar();
        List<ServicioDTO> lista =new ArrayList<>();
        for (Servicio elemento : servicios) {
            System.out.println("1"); // Debug: Verificar el contenido
            List<ItemServicio> items = itemServicioService.listar();
            System.out.println("3"); // Debug: Verificar el contenido
            ServicioDTO ser  = new ServicioDTO(elemento,items);
            lista.add(ser);
        }
        return lista;
    }*/

    @GetMapping("/service")
    public ResponseEntity<?> getService() {
        try {
            return ResponseEntity.ok(modelService.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing services: " + e.getMessage());
        }
    }


    /*@GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> getPorId(@PathVariable Integer id){
        logger.info("entra  en buscar servicio"  );
        Servicio model = modelService.buscarPorId(id);
        if(model == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        List<ItemServicio>listItems = itemServicioService.buscarPorServicio(model.getId());
        ServicioDTO modelDTO  = new ServicioDTO(model,listItems);
        logger.info(modelDTO.toString());
        return ResponseEntity.ok(modelDTO);
    }*/

    @GetMapping("/service/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Integer id) {
        try{
            ServiceDTO modelDTO = modelService.buscarPorId(id);

            if(modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The service with id: " + id + " does not exist");
            }
            return ResponseEntity.ok(modelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing service: " + e.getMessage());
        }
    }


    /*@GetMapping({"/serviciosPageQuery"})
    public ResponseEntity<Page<ServicioDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "${max_page}") int size) {
        List<Servicio> listado = modelService.listar(consulta);
        List<ServicioDTO> listadoDTO    =  new ArrayList<>();
        listado.forEach((model) -> {
            listadoDTO.add(new ServicioDTO(model));
        });
        Page<ServicioDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listadoDTO);
        return ResponseEntity.ok().body(bookPage);
    }*/

    @GetMapping("/servicePageQuery")
    public ResponseEntity<?> getItems((@RequestParam(defaultValue = "") String consulta,@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {
        try {
            List<ServiceDTO> services = modelService.listar(consulta);

            Page<ServiceDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size), services);
            return ResponseEntity.ok(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing services: " + e.getMessage());
        }
    }


    /*@PostMapping("/servicios")
    public Servicio agregar(@RequestBody ServicioDTO model){

        Integer idCliente = model.cliente;

        Servicio newModel =  new Servicio();
        newModel.setCliente(clienteService.buscarPorId(idCliente));
        newModel.setFechaRegistro(model.fechaDocumento);
        newModel.setFechaRealizacion(model.fechaDocumento);
        newModel.setEstado(0);

        Servicio servicioGuardado= modelService.guardar(newModel);
        for (ItemServicioDTO elemento : model.listaItems) {
            double precio = elemento.getPrecio();
            logger.info("entra for");

            TipoServicio tipoServicio = tipoServicioService.buscarPorId(elemento.getTipoServicioId());
            String observacion = elemento.getObservaciones();
            ItemServicio item = new ItemServicio(newModel, tipoServicio, precio,observacion);

            itemServicioService.guardar(item);

        }

        return servicioGuardado;
    }*/

    @PostMapping("/service")
    public ResponseEntity<?> createService(@RequestBody ServiceDTO model) {
        try{
            ServiceDTO modelSaved = modelService.guardar(model);

            if (modelSaved == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error saving service");
            }
            return ResponseEntity.ok(modelSaved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating service:" + e.getMessage());
        }
    }

    /*----------------------------------------------------------------------------------*/

    @PutMapping("/service/{id}")
    public ResponseEntity<?> updateService(@RequestBody ServiceDTO modelRecibido, @PathVariable Integer id) {
        try {
            ServiceDTO existingModelDTO = modelService.buscarPorId(id);

            if(existingModelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This service does not exist");
            }

            ServiceDTO updatedModelDTO = modelService.update(existingModelDTO, modelRecibido);

            if (updatedModelDTO == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating service");
            }

            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating service: " + e.getMessage());
        }
    }


    @DeleteMapping("/service/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Integer id) {
        try{
            ServiceDTO modelDTO = modelService.buscarPorId(id);

            if(modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The service with id:" + id + " does not exist");
            }

            modelService.eliminar(modelDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting client: " + e.getMessage());
        }
    }


}

