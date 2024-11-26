package jsges.nails.controller.services;
import jsges.nails.domain.services.ServiceClass;
import jsges.nails.DTO.services.ServiceClassDTO;
import jsges.nails.service.services.IServiceClassService;
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
public class ServiceClassController {

    @Autowired
    private IServiceClassService modelService;

    @GetMapping("/service")
    public ResponseEntity<?> getService() {
        try {
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing services: " + e.getMessage());
        }
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Integer id) {
        try{
            ServiceClassDTO modelDTO = modelService.getModelById(id);

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

    @GetMapping("/servicePageQuery")
    public ResponseEntity<?> getItems(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "${max_page}") int size) {
        try {
            List<ServiceClassDTO> services = modelService.getModels();

            Page<ServiceClassDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size), services);
            return ResponseEntity.ok(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing services: " + e.getMessage());
        }
    }

    @PostMapping("/service")
    public ResponseEntity<?> createService(@RequestBody ServiceClass model) {
        try{
            ServiceClassDTO modelSaved = modelService.createModel(model);

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

    @DeleteMapping("/service/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Integer id) {
        try{
            ServiceClassDTO modelDTO = modelService.getModelById(id);

            if(modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The service with id:" + id + " does not exist");
            }

            modelService.deleteModel(modelDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting client: " + e.getMessage());
        }
    }


}

