package lab.blps.services;

import lab.blps.bd.entites.TaxFeature;
import lab.blps.bd.entites.TaxpayerCategory;
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
        for (TaxpayerCategory taxpayerCategory : taxRegimeChoice.getTaxpayerCategories()) {
            taxpayerCategories.add(taxpayerCategory.name().toLowerCase());
        }
        taxRegimeChoiceDto.setTaxpayerCategories(taxpayerCategories);
        List<String> taxFeatures = new ArrayList<>();
        for (TaxFeature taxFeature : taxRegimeChoice.getTaxFeatures()) {
            taxFeatures.add(taxFeature.name().toLowerCase());
        }
        taxRegimeChoiceDto.setTaxFeatures(taxFeatures);
        taxRegimeChoiceDto.setMaxAnnualIncomeThousands(taxRegimeChoice.getMaxAnnualIncomeThousands());
        taxRegimeChoiceDto.setMaxNumberEmployees(taxRegimeChoice.getMaxNumberEmployees());
        return taxRegimeChoiceDto;
    }

    public static TaxRegimeChoice mapToTaxRegimeChoice(TaxRegimeChoiceDto taxRegimeChoiceDto) {
        TaxRegimeChoice taxRegimeChoice = new TaxRegimeChoice();
        List<TaxpayerCategory> taxpayerCategories = new ArrayList<>();
        for (String taxpayerCategory : taxRegimeChoiceDto.getTaxpayerCategories()) {
            taxpayerCategories.add(TaxpayerCategory.valueOf(taxpayerCategory.toUpperCase()));
        }
        taxRegimeChoice.setTaxpayerCategories(taxpayerCategories);
        List<TaxFeature> taxFeatures = new ArrayList<>();
        for (String taxFeature : taxRegimeChoiceDto.getTaxFeatures()) {
            taxFeatures.add(TaxFeature.valueOf(taxFeature.toUpperCase()));
        }
        taxRegimeChoice.setTaxFeatures(taxFeatures);
        taxRegimeChoice.setMaxAnnualIncomeThousands(taxRegimeChoiceDto.getMaxAnnualIncomeThousands());
        taxRegimeChoice.setMaxNumberEmployees(taxRegimeChoiceDto.getMaxNumberEmployees());
        return taxRegimeChoice;
    }
}
