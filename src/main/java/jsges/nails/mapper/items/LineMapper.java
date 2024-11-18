package jsges.nails.mapper.items;

import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
import org.springframework.stereotype.Component;

@Component
public class LineMapper {

    public LineDTO convertModelToDto(Line line) {
        LineDTO lineDTO = new LineDTO();

        lineDTO.setId(line.getId());
        lineDTO.setDenomination(line.getDenomination());

        return lineDTO;
    }
}
