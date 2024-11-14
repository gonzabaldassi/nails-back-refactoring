package jsges.nails.controller.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.organizacion.IClienteService;
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


@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")

public class ClienteControlador {
    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    /*@Autowired
    private IClienteService clienteServicio;*/

    @Autowired
    private IClienteService modelService;

    /*public ClienteControlador() {
    }*/

    /*@GetMapping({"/clientes"})
    public List<ClienteDTO> getAll() {
        List<ClienteDTO> listadoDTO    =  new ArrayList<>();
        List<Cliente> list = this.clienteServicio.listar();

        list.forEach((model) -> {
            listadoDTO.add(new ClienteDTO(model));
        });
        return listadoDTO;
    }*/

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer(){
        try {
            return ResponseEntity.ok(modelService.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing customers: " + e.getMessage());
        }
    }

    /*
    @GetMapping({"/clientesPageQuery"})
    public ResponseEntity<Page<ClienteDTO>> getItems(@RequestParam(defaultValue = "") String consulta,@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "${max_page}") int size) {
        List<Cliente> listado = clienteServicio.listar(consulta);
        List<ClienteDTO> listadoDTO    =  new ArrayList<>();
        listado.forEach((model) -> {
            listadoDTO.add(new ClienteDTO(model));
        });
        Page<ClienteDTO> bookPage = clienteServicio.findPaginated(PageRequest.of(page, size),listadoDTO);
        return ResponseEntity.ok().body(bookPage);
    }*/

    @GetMapping("/customerPageQuery")
    public ResponseEntity<?> getItems((@RequestParam(defaultValue = "") String consulta,@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {

        try {
            List<ClienteDTO> clientes = modelService.listar(consulta);

            Page<ClienteDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size), clientes);
            return ResponseEntity.ok().body(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing customers: " + e.getMessage());
        }
    }


    /*@PostMapping("/clientes")
    public Cliente agregar(@RequestBody Cliente cliente){
       // logger.info("Cliente a agregar: " + cliente);
        return clienteServicio.guardar(cliente);
    }     */

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody ClienteDTO model){
        try{
            ClienteDTO modelSaved = modelService.guardar(model);

            if (modelSaved == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error saving customer");
            }

            return ResponseEntity.ok(modelSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating customer: " + e.getMessage());
        }
    }


    /*@PutMapping("/clienteEliminar/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Integer id){
        Cliente model = clienteServicio.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

        model.setEstado(1);

        clienteServicio.guardar(model);
        return ResponseEntity.ok(model);
    }*/

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){

        try {
            ClienteDTO modelDTO = modelService.buscarPorId(id);

            if (modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The customer with id:" + id + ", doesnt exist");
            }

            modelService.eliminar(modelDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting customer: " + e.getMessage());
        }
    }


    /*@GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getPorId(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarPorId(id);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        return ResponseEntity.ok(cliente);
    }*/

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id){
        try{
            ClienteDTO modelDTO = modelService.buscarPorId(id);

            if (modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The customer with id:" + id + ", doesnt exist");
            }
            return ResponseEntity.ok(modelDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing customer: " + e.getMessage());
        }
    }

    /*@PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id,
                                              @RequestBody Cliente modelRecibido){
        Cliente model = clienteServicio.buscarPorId(id);
        if (model == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);



        clienteServicio.guardar(modelRecibido);
        return ResponseEntity.ok(modelRecibido);
    }*/

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody ClienteDTO modelRecibido, @PathVariable Integer id){
        try{
            ClienteDTO existingModelDTO = modelService.buscarPorId(id);

            if (existingModelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This customer does not exist");
            }

            ClienteDTO updatedModelDTO = modelService.update(existingModelDTO, modelRecibido);

            if(updatedModelDTO == null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error updating customer");
            }
            return ResponseEntity.ok(updatedModelDTO);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
                    .body("Error updating customer: " + e.getMessage());
        }
    }

}
