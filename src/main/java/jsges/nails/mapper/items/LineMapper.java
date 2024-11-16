package jsges.nails.mapper.items;

import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;

public class LineMapper {

    public LineDTO convertLineToDto(Line line) {
        LineDTO lineDTO = new LineDTO();

        lineDTO.setId(line.getId());
        lineDTO.setDenomination(line.getDenomination());

        return lineDTO;
    }

    public Line convertDtoToLine(LineDTO lineDTO) {
        Line line = new Line();

        line.setId(lineDTO.getId());
        line.setDenomination(lineDTO.getDenomination());

        return line;
    }
}
