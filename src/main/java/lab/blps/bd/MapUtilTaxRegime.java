package lab.blps.bd;


import lab.blps.bd.dto.TaxRegimeDto;
import lab.blps.bd.entites.TaxFeature;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategory;

public class MapUtilTaxRegime {
    public static TaxRegimeDto mapToRegimeChoiceDto(TaxRegime taxRegime) {
        TaxRegimeDto taxRegimeDto = new TaxRegimeDto();
        taxRegimeDto.setId(taxRegime.getId());
        taxRegimeDto.setTaxpayerCategory(taxRegime.getTaxpayerCategory().name().toLowerCase());
        taxRegimeDto.setTaxFeature(taxRegime.getTaxFeature().name().toLowerCase());
        taxRegimeDto.setMaxAnnualIncomeThousands(taxRegime.getMaxAnnualIncomeThousands());
        taxRegimeDto.setMaxNumberEmployees(taxRegime.getMaxNumberEmployees());
        taxRegimeDto.setTitle(taxRegime.getTitle());
        taxRegimeDto.setDescription(taxRegime.getDescription());
        return taxRegimeDto;
    }

    public static TaxRegime mapToRegimeChoice(TaxRegimeDto taxRegimeDto) {
        TaxRegime taxRegime = new TaxRegime();
        taxRegime.setId(taxRegimeDto.getId());
        taxRegime.setTaxpayerCategory(TaxpayerCategory.valueOf(taxRegimeDto.getTaxpayerCategory().toUpperCase()));
        taxRegime.setTaxFeature(TaxFeature.valueOf(taxRegimeDto.getTaxFeature().toUpperCase()));
        taxRegime.setMaxAnnualIncomeThousands(taxRegimeDto.getMaxAnnualIncomeThousands());
        taxRegime.setMaxNumberEmployees(taxRegimeDto.getMaxNumberEmployees());
        taxRegime.setTitle(taxRegimeDto.getTitle());
        taxRegime.setDescription(taxRegimeDto.getDescription());
        return taxRegime;
    }
}
