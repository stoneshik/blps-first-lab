package lab.blps.services.choice;

import lab.blps.bd.entites.TaxFeatures;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategories;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ChoiceFilterByTaxpayerCategoryAndTaxFeature implements ChoiceFilter {
    private final TaxRegimeRepository taxRegimeRepository;

    @Override
    public List<TaxRegimeWithFeaturesAndCategory> filter(TaxRegimeChoice taxRegimeChoice) {
        List<TaxpayerCategories> taxpayerCategories = taxRegimeRepository.findTaxpayerCategories(
                taxRegimeChoice.getTaxpayerCategories()
        );
        List<Long> filteredTaxRegimeIds = getTaxRegimeIdsFromTaxpayerCategories(taxpayerCategories);
        List<TaxFeatures> taxFeatures = taxRegimeRepository.findTaxFeaturesByFilteredTaxRegimeIds(
                taxRegimeChoice.getTaxFeatures(),
                filteredTaxRegimeIds
        );
        filteredTaxRegimeIds = getTaxRegimeIdsFromTaxFeatures(taxFeatures);
        List<TaxRegime> taxRegimes = taxRegimeRepository
                .findByMaxAnnualIncomeThousandsAndMaxNumberEmployeesAndFilteredTaxRegimeIds(
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees(),
                        filteredTaxRegimeIds
                );
        List<TaxRegimeWithFeaturesAndCategory> taxRegimesWithFeaturesAndCategories = new ArrayList<>();
        for (TaxRegime taxRegime : taxRegimes) {
            TaxRegimeWithFeaturesAndCategory taxRegimeWithFeaturesAndCategory = new TaxRegimeWithFeaturesAndCategory(
                    taxRegime.getId(),
                    taxRegime.getTitle(),
                    taxRegime.getDescription(),
                    filterTaxpayerCategories(taxRegime, taxpayerCategories),
                    filterTaxFeatures(taxRegime, taxFeatures),
                    taxRegime.getMaxAnnualIncomeThousands(),
                    taxRegime.getMaxNumberEmployees()
            );
            taxRegimesWithFeaturesAndCategories.add(taxRegimeWithFeaturesAndCategory);
        }
        return taxRegimesWithFeaturesAndCategories;
    }

    private List<Long> getTaxRegimeIdsFromTaxpayerCategories(List<TaxpayerCategories> taxpayerCategories) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxpayerCategories taxpayerCategory: taxpayerCategories) {
            filteredTaxRegimeIds.add(taxpayerCategory.getTaxRegime().getId());
        }
        return filteredTaxRegimeIds;
    }

    private List<Long> getTaxRegimeIdsFromTaxFeatures(List<TaxFeatures> taxFeatures) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxFeatures taxFeature: taxFeatures) {
            filteredTaxRegimeIds.add(taxFeature.getTaxRegime().getId());
        }
        return filteredTaxRegimeIds;
    }

    private List<TaxpayerCategories> filterTaxpayerCategories(
            TaxRegime taxRegime,
            List<TaxpayerCategories> taxpayerCategories
    ) {
        List<TaxpayerCategories> filteredTaxpayerCategories = new ArrayList<>();
        for (TaxpayerCategories taxpayerCategory : taxpayerCategories) {
            if (taxpayerCategory.getTaxRegime().getId().equals(taxRegime.getId())) {
                filteredTaxpayerCategories.add(taxpayerCategory);
            }
        }
        return filteredTaxpayerCategories;
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
