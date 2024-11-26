package jsges.nails;

import jsges.nails.domain.items.Line;
import jsges.nails.domain.items.SalesItem;
import jsges.nails.dto.items.SalesItemDTO;
import jsges.nails.mapper.items.SalesItemMapper;
import jsges.nails.repository.items.SalesItemRepository;
import jsges.nails.service.items.SalesItemImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SalesItemImplementationTest {

    @Mock
    private SalesItemRepository salesItemRepository;

    @Mock
    private SalesItemMapper salesItemMapper;

    @InjectMocks
    private SalesItemImplementation salesItemImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getModels() {

        Line line1 = new Line();
        Line line2 = new Line();

        SalesItem salesItem1 = new SalesItem(1,"Denomination1",1,"Observation1",line1);
        SalesItem salesItem2 = new SalesItem(2,"Denomination2",1,"Observation2",line2);

        List <SalesItem> salesItems = Arrays.asList(salesItem1,salesItem2);

        //Simulo DAO
        when(salesItemRepository.findByState0()).thenReturn(salesItems);

        //Simulo Mapper
        when(salesItemMapper.convertModelToDto(salesItem1)).thenReturn(new SalesItemDTO(1,"Denomination1",1));
        when(salesItemMapper.convertModelToDto(salesItem2)).thenReturn(new SalesItemDTO(2,"Denomination2",2));


        //Resultado
        List <SalesItemDTO> salesItemResult = salesItemImplementation.getModels();

        assertEquals(2,salesItemResult.size(), "There should be 2 salesItem in the result");
        assertEquals("Denomination1",salesItemResult.get(0).getDenomination(),"The denomination of the first line should be: Denomination1");
        assertEquals("Denomination2",salesItemResult.get(1).getDenomination(),"The denomination of the first line should be: Denomination2");

        verify(salesItemRepository).findByState0();
        verify(salesItemMapper).convertModelToDto(salesItem1);
        verify(salesItemMapper).convertModelToDto(salesItem2);
    }
}