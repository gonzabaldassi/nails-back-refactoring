package jsges.nails.controller.items;
import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
import jsges.nails.service.items.ILineService;
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

    @Autowired
    private ILineService modelService;


    @GetMapping({"/line"})
    public ResponseEntity<?> getLine(){
        try {
            return ResponseEntity.ok(modelService.getModels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error showing the lines:" + e.getMessage());
        }
    }

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

