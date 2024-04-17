package lab.blps.services;

import lab.blps.bd.entites.TaxFeatures;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategories;
import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
                taxRegimesWithFeaturesAndCategories = filterByFeatures(taxRegimeChoice);
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

    private List<TaxRegimeWithFeaturesAndCategory> filterByFeatures(
            TaxRegimeChoice taxRegimeChoice
    ) {
        List<TaxFeatures> taxFeatures = taxRegimeRepository.findTaxFeatures(taxRegimeChoice.getTaxFeatures());
        List<Long> filteredTaxRegimeIds = getFilteredIdsFromFeatures(taxFeatures);
        List<TaxRegime> taxRegimes = taxRegimeRepository
                .findByMaxAnnualIncomeThousandsAndMaxNumberEmployeesAndFilteredTaxRegimeIds(
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees(),
                        filteredTaxRegimeIds
                );
        List<TaxRegimeWithFeaturesAndCategory> taxRegimesWithFeaturesAndCategories = new ArrayList<>();
        for (TaxRegime taxRegime : taxRegimes) {
            List<TaxFeatures> filteredTaxFeatures = filterTaxFeatures(taxRegime, taxFeatures);
            TaxRegimeWithFeaturesAndCategory taxRegimeWithFeaturesAndCategory = new TaxRegimeWithFeaturesAndCategory(
                    taxRegime.getId(),
                    taxRegime.getTitle(),
                    taxRegime.getDescription(),
                    new ArrayList<>(),
                    filteredTaxFeatures,
                    taxRegime.getMaxAnnualIncomeThousands(),
                    taxRegime.getMaxNumberEmployees()
            );
            taxRegimesWithFeaturesAndCategories.add(taxRegimeWithFeaturesAndCategory);
        }
        return taxRegimesWithFeaturesAndCategories;
    }

    private List<Long> getFilteredIdsFromCategories(List<TaxpayerCategories> taxpayerCategories) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxpayerCategories taxCategory: taxpayerCategories) {
            filteredTaxRegimeIds.add(taxCategory.getId());
        }
        return filteredTaxRegimeIds;
    }

    private List<Long> getFilteredIdsFromFeatures(List<TaxFeatures> taxFeatures) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxFeatures taxFeature: taxFeatures) {
            filteredTaxRegimeIds.add(taxFeature.getId());
        }
        return filteredTaxRegimeIds;
    }

    private List<TaxFeatures> filterTaxFeatures(TaxRegime taxRegime, List<TaxFeatures> taxFeatures) {
        List<TaxFeatures> filteredTaxFeatures = new ArrayList<>();
        for (TaxFeatures taxFeature : taxFeatures) {
            if (taxFeature.getTaxRegime().getId().equals(taxRegime.getId())) {
                filteredTaxFeatures.add(taxFeature);
            }
        }
        return filteredTaxFeatures;
    }
}
