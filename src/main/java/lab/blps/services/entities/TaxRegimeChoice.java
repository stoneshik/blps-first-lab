package lab.blps.services.entities;

import lab.blps.bd.entites.TaxFeature;
import lab.blps.bd.entites.TaxpayerCategory;
import lombok.Data;

import java.util.List;

@Data
public class TaxRegimeChoice {
    private List<TaxpayerCategory> taxpayerCategories;
    private List<TaxFeature> taxFeatures;
    private Long maxAnnualIncomeThousands;
    private Long maxNumberEmployees;
}
