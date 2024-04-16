package lab.blps.bd;


import lab.blps.bd.dto.TaxRegimeDto;
import lab.blps.bd.entites.TaxRegime;

public class MapUtilTaxRegime {
    public static TaxRegimeDto mapToRegimeChoiceDto(TaxRegime taxRegime) {
        TaxRegimeDto taxRegimeDto = new TaxRegimeDto();
        taxRegimeDto.setId(taxRegime.getId());
        taxRegimeDto.setTitle(taxRegime.getTitle());
        taxRegimeDto.setDescription(taxRegime.getDescription());
        taxRegimeDto.setMaxAnnualIncomeThousands(taxRegime.getMaxAnnualIncomeThousands());
        taxRegimeDto.setMaxNumberEmployees(taxRegime.getMaxNumberEmployees());
        return taxRegimeDto;
    }

    public static TaxRegime mapToRegimeChoice(TaxRegimeDto taxRegimeDto) {
        TaxRegime taxRegime = new TaxRegime();
        taxRegime.setId(taxRegimeDto.getId());
        taxRegime.setTitle(taxRegimeDto.getTitle());
        taxRegime.setDescription(taxRegimeDto.getDescription());
        taxRegime.setMaxAnnualIncomeThousands(taxRegimeDto.getMaxAnnualIncomeThousands());
        taxRegime.setMaxNumberEmployees(taxRegimeDto.getMaxNumberEmployees());
        return taxRegime;
    }
}
