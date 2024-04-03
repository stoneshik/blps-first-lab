package lab.blps.bd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxRegimeDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("taxpayerCategory")
    private String taxpayerCategory;
    @JsonProperty("taxFeature")
    private String taxFeature;
    @JsonProperty("maxAnnualIncomeThousands")
    private Long maxAnnualIncomeThousands;
    @JsonProperty("maxNumberEmployees")
    private Long maxNumberEmployees;
}
