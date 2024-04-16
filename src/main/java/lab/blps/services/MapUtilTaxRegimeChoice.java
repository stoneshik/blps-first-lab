package lab.blps.services;

import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lab.blps.bd.entites.enums.TaxpayerCategoryEnum;
import lab.blps.services.dto.TaxRegimeChoiceDto;
import lab.blps.services.entities.TaxRegimeChoice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapUtilTaxRegimeChoice {
    public static TaxRegimeChoiceDto mapToTaxRegimeChoiceDto(TaxRegimeChoice taxRegimeChoice) {
        TaxRegimeChoiceDto taxRegimeChoiceDto = new TaxRegimeChoiceDto();
        List<String> taxpayerCategories = new ArrayList<>();
        for (TaxpayerCategoryEnum taxpayerCategoryEnum : taxRegimeChoice.getTaxpayerCategories()) {
            taxpayerCategories.add(taxpayerCategoryEnum.name().toLowerCase());
        }
        taxRegimeChoiceDto.setTaxpayerCategories(taxpayerCategories);
        List<String> taxFeatures = new ArrayList<>();
        for (TaxFeatureEnum taxFeatureEnum : taxRegimeChoice.getTaxFeatures()) {
            taxFeatures.add(taxFeatureEnum.name().toLowerCase());
        }
        taxRegimeChoiceDto.setTaxFeatures(taxFeatures);
        taxRegimeChoiceDto.setMaxAnnualIncomeThousands(taxRegimeChoice.getMaxAnnualIncomeThousands());
        taxRegimeChoiceDto.setMaxNumberEmployees(taxRegimeChoice.getMaxNumberEmployees());
        return taxRegimeChoiceDto;
    }

    public static TaxRegimeChoice mapToTaxRegimeChoice(TaxRegimeChoiceDto taxRegimeChoiceDto) {
        TaxRegimeChoice taxRegimeChoice = new TaxRegimeChoice();
        List<TaxpayerCategoryEnum> taxpayerCategories = new ArrayList<>();
        for (String taxpayerCategory : taxRegimeChoiceDto.getTaxpayerCategories()) {
            taxpayerCategories.add(TaxpayerCategoryEnum.valueOf(taxpayerCategory.toUpperCase()));
        }
        taxRegimeChoice.setTaxpayerCategories(taxpayerCategories);
        List<TaxFeatureEnum> taxFeatures = new ArrayList<>();
        for (String taxFeature : taxRegimeChoiceDto.getTaxFeatures()) {
            taxFeatures.add(TaxFeatureEnum.valueOf(taxFeature.toUpperCase()));
        }
        taxRegimeChoice.setTaxFeatures(taxFeatures);
        taxRegimeChoice.setMaxAnnualIncomeThousands(taxRegimeChoiceDto.getMaxAnnualIncomeThousands());
        taxRegimeChoice.setMaxNumberEmployees(taxRegimeChoiceDto.getMaxNumberEmployees());
        return taxRegimeChoice;
    }
}
