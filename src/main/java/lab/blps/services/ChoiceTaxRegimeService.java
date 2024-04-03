package lab.blps.services;

import lab.blps.bd.entites.TaxRegime;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceTaxRegimeService {
    private final TaxRegimeRepository taxRegimeRepository;

    public List<TaxRegime> choice(TaxRegimeChoice taxRegimeChoice) {
        List<TaxRegime> taxRegimes;
        if (taxRegimeChoice.getTaxpayerCategories().isEmpty()) {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                taxRegimes = (List<TaxRegime>) taxRegimeRepository.findAll();
            } else {
                taxRegimes = taxRegimeRepository.findByTaxFeature(
                        taxRegimeChoice.getTaxFeatures(),
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees()
                );
            }
        } else {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                taxRegimes = taxRegimeRepository
                        .findByTaxpayerCategoryAndTaxFeature(
                                taxRegimeChoice.getTaxpayerCategories(),
                                taxRegimeChoice.getTaxFeatures(),
                                taxRegimeChoice.getMaxAnnualIncomeThousands(),
                                taxRegimeChoice.getMaxNumberEmployees()
                        );
            } else {
                taxRegimes = taxRegimeRepository.findByTaxpayerCategory(
                        taxRegimeChoice.getTaxpayerCategories(),
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees()
                );
            }
        }
        return taxRegimes;
    }
}
