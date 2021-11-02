package yetAnotherWeatherSource.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataTransferObject Class for "coord" section of API response, which holds coordinates.
 * Has AllArgsConstructor and NoArgsConstructor annotations required for use in Integration Mock tests.
 * Attributes:
 * lon longitude
 * lat latitude
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordDto {
    private Double lon;
    private Double lat;
}
