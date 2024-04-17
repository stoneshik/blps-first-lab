package lab.blps.services;

import lab.blps.bd.entites.TaxRegime;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceTaxRegimeService {
    private final TaxRegimeRepository taxRegimeRepository;

    public List<TaxRegimeWithFeaturesAndCategory> choice(TaxRegimeChoice taxRegimeChoice) {
        List<TaxRegimeWithFeaturesAndCategory> taxRegimesWithFeaturesAndCategories;
        if (taxRegimeChoice.getTaxpayerCategories().isEmpty()) {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                taxRegimesWithFeaturesAndCategories = filterOnlyByMaxAnnualIncomeThousandsAndMaxNumberEmployees(
                        taxRegimeChoice
                );
            } else {
                taxRegimesWithFeaturesAndCategories = taxRegimeRepository.findByTaxFeature(
                        taxRegimeChoice.getTaxFeatures(),
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees()
                );
            }
        } else {
            if (taxRegimeChoice.getTaxFeatures().isEmpty()) {
                taxRegimesWithFeaturesAndCategories = taxRegimeRepository
                        .findByTaxpayerCategoryAndTaxFeature(
                                taxRegimeChoice.getTaxpayerCategories(),
                                taxRegimeChoice.getTaxFeatures(),
                                taxRegimeChoice.getMaxAnnualIncomeThousands(),
                                taxRegimeChoice.getMaxNumberEmployees()
                        );
            } else {
                taxRegimesWithFeaturesAndCategories = taxRegimeRepository.findByTaxpayerCategory(
                        taxRegimeChoice.getTaxpayerCategories(),
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees()
                );
            }
        }
        return taxRegimesWithFeaturesAndCategories;
    }

    private List<TaxRegimeWithFeaturesAndCategory> filterOnlyByMaxAnnualIncomeThousandsAndMaxNumberEmployees(
            TaxRegimeChoice taxRegimeChoice
    ) {
        List<TaxRegime> taxRegimes = taxRegimeRepository.findByMaxAnnualIncomeThousandsAndMaxNumberEmployees(
                taxRegimeChoice.getMaxAnnualIncomeThousands(),
                taxRegimeChoice.getMaxNumberEmployees()
        );
        List<TaxRegimeWithFeaturesAndCategory> taxRegimesWithFeaturesAndCategories = new ArrayList<>();
        for (TaxRegime taxRegime : taxRegimes) {
            TaxRegimeWithFeaturesAndCategory taxRegimeWithFeaturesAndCategory = new TaxRegimeWithFeaturesAndCategory(
                    taxRegime.getId(),
                    taxRegime.getTitle(),
                    taxRegime.getDescription(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    taxRegime.getMaxAnnualIncomeThousands(),
                    taxRegime.getMaxNumberEmployees()
            );
            taxRegimesWithFeaturesAndCategories.add(taxRegimeWithFeaturesAndCategory);
        }
        return taxRegimesWithFeaturesAndCategories;
    }
}
