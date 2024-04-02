package lab.blps.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaxRegimeChoiceDto {
    @JsonProperty("taxpayerCategories")
    private List<String> taxpayerCategories;
    @JsonProperty("taxFeatures")
    private List<String> taxFeatures;
    @JsonProperty("maxAnnualIncomeThousands")
    private Long maxAnnualIncomeThousands;
    @JsonProperty("maxNumberEmployees")
    private Long maxNumberEmployees;
}
