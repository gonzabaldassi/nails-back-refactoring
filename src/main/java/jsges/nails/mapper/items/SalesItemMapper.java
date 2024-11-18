package jsges.nails.mapper.items;

import jsges.nails.domain.items.SalesItem;
import jsges.nails.dto.items.SalesItemDTO;
import org.springframework.stereotype.Component;

@Component
public class SalesItemMapper {

    public SalesItemDTO convertModelToDto(SalesItem salesItem) {
        SalesItemDTO salesItemDTO = new SalesItemDTO();

        salesItemDTO.setId(salesItem.getId());
        salesItemDTO.setDenomination(salesItem.getDenomination());
        salesItemDTO.setLineId(salesItem.getLine().getId());

        return salesItemDTO;
    }
}
