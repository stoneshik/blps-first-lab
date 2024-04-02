package lab.blps.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaxRegimeChoiceDto {
    @JsonProperty("id")
    private Long Id;
    private List<String> taxpayerCategories;
    private List<String> taxFeatures;
    private Long maxAnnualIncomeThousands;
    private Long maxNumberEmployees;
}
