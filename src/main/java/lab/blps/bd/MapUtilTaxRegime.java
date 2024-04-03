package lab.blps.bd;


import lab.blps.bd.dto.TaxRegimeDto;
import lab.blps.bd.entites.TaxFeature;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategory;

public class MapUtilTaxRegime {
    public static TaxRegimeDto mapToRegimeChoiceDto(TaxRegime taxRegime) {
        TaxRegimeDto taxRegimeDto = new TaxRegimeDto();
        taxRegimeDto.setId(taxRegime.getId());
        taxRegimeDto.setTitle(taxRegime.getTitle());
        taxRegimeDto.setDescription(taxRegime.getDescription());
        if (taxRegime.getTaxpayerCategory() != null) {
            taxRegimeDto.setTaxpayerCategory(taxRegime.getTaxpayerCategory().name().toLowerCase());
        }
        if (taxRegime.getTaxFeature() != null) {
            taxRegimeDto.setTaxFeature(taxRegime.getTaxFeature().name().toLowerCase());
        }
        taxRegimeDto.setMaxAnnualIncomeThousands(taxRegime.getMaxAnnualIncomeThousands());
        taxRegimeDto.setMaxNumberEmployees(taxRegime.getMaxNumberEmployees());
        return taxRegimeDto;
    }

    public static TaxRegime mapToRegimeChoice(TaxRegimeDto taxRegimeDto) {
        TaxRegime taxRegime = new TaxRegime();
        taxRegime.setId(taxRegimeDto.getId());
        taxRegime.setTitle(taxRegimeDto.getTitle());
        taxRegime.setDescription(taxRegimeDto.getDescription());
        if (taxRegimeDto.getTaxpayerCategory() != null) {
            taxRegime.setTaxpayerCategory(TaxpayerCategory.valueOf(taxRegimeDto.getTaxpayerCategory().toUpperCase()));
        }
        if (taxRegimeDto.getTaxFeature() != null) {
            taxRegime.setTaxFeature(TaxFeature.valueOf(taxRegimeDto.getTaxFeature().toUpperCase()));
        }
        taxRegime.setMaxAnnualIncomeThousands(taxRegimeDto.getMaxAnnualIncomeThousands());
        taxRegime.setMaxNumberEmployees(taxRegimeDto.getMaxNumberEmployees());
        return taxRegime;
    }
}
