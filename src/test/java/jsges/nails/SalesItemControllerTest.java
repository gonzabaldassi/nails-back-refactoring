package jsges.nails;

import jsges.nails.controller.items.SalesItemController;
import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.service.items.SalesItemImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SalesItemController.class)
public class SalesItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesItemImplementation salesItemService;

    @Test
    void getAllSalesItems() throws Exception {
        // Datos simulados

        SalesItemDTO item1 = new SalesItemDTO(1,"Denomination1",1);
        SalesItemDTO item2 = new SalesItemDTO(2,"Denomination2",2);
        List<SalesItemDTO> items = Arrays.asList(item1, item2);

        // Simular comportamiento del servicio
        when(salesItemService.getModels()).thenReturn(items);

        // Realizar la solicitud GET y verificar el resultado
        mockMvc.perform(get("/nails/item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].denomination").value("Denomination1"))
                .andExpect(jsonPath("$[1].denomination").value("Denomination2"));

        // Verificar que el servicio fue llamado
        verify(salesItemService).getModels();
    }
}