package jsges.nails.controller.organization;

import jsges.nails.domain.organization.Customer;
import jsges.nails.dto.organization.CustomerDTO;
import jsges.nails.service.organization.ICustomerService;
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

public class CustomerController {

    @Autowired
    private ICustomerService modelService;

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer(){
        try {
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing customers: " + e.getMessage());
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id){
        try{
            CustomerDTO modelDTO = modelService.getModelById(id);

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

    @GetMapping("/customerPageQuery")
    public ResponseEntity<?> getItems (@RequestParam(defaultValue = "") String request, @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "${max_page}") int size) {

        try {
            List<CustomerDTO> customers = modelService.getModelByRequest(request);

            Page<CustomerDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size), customers);
            return ResponseEntity.ok().body(bookPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error listing customers: " + e.getMessage());
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer model){
        try{
            CustomerDTO modelSaved = modelService.createModel(model);

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

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer receivedModel, @PathVariable Integer id){
        try{
            CustomerDTO existingModelDTO = modelService.getModelById(id);

            if (existingModelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("This customer does not exist");
            }

            CustomerDTO updatedModelDTO = modelService.updateModel(existingModelDTO, receivedModel);

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

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){

        try {
            CustomerDTO modelDTO = modelService.getModelById(id);

            if (modelDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The customer with id:" + id + ", doesnt exist");
            }

            modelService.deleteModel(modelDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting customer: " + e.getMessage());
        }
    }

}
