package jsges.nails.mapper.items;

import jsges.nails.domain.items.SalesItem;
import jsges.nails.dto.items.SalesItemDTO;

public class SalesItemMapper {

    public SalesItemDTO convertSalesItemToDto(SalesItem salesItem) {
        SalesItemDTO salesItemDTO = new SalesItemDTO();

        salesItemDTO.setId(salesItem.getId());
        salesItemDTO.setDenomination(salesItem.getDenomination());
        salesItemDTO.setLineId(salesItem.getLine().getId());

        return salesItemDTO;
    }

    /*public SalesItem convertDtoToSalesItem(SalesItemDTO salesItemDTO) {
        SalesItem salesItem = new SalesItem();

        salesItem.setId(salesItemDTO.getId());
        salesItem.setDenomination(salesItemDTO.getDenomination());
        salesItem.setLine()
    }*/
}
