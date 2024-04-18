package lab.blps.services.choice;

import lab.blps.bd.entites.TaxFeatures;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategories;
import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lab.blps.bd.entites.enums.TaxpayerCategoryEnum;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RequiredArgsConstructor
public class ChoiceFilterByTaxpayerCategory implements ChoiceFilter {
    private final TaxRegimeRepository taxRegimeRepository;

    @Override
    public List<TaxRegimeWithFeaturesAndCategory> filter(TaxRegimeChoice taxRegimeChoice) {
        List<TaxpayerCategories> taxpayerCategories = taxRegimeRepository.findTaxpayerCategories(
                taxRegimeChoice.getTaxpayerCategories()
        );
        List<Long> filteredTaxRegimeIds = getTaxRegimeIdsFromTaxpayerCategories(taxpayerCategories);
        List<TaxRegime> taxRegimes = taxRegimeRepository
                .findByMaxAnnualIncomeThousandsAndMaxNumberEmployeesAndFilteredTaxRegimeIds(
                        taxRegimeChoice.getMaxAnnualIncomeThousands(),
                        taxRegimeChoice.getMaxNumberEmployees(),
                        filteredTaxRegimeIds
                );
        List<TaxRegimeWithFeaturesAndCategory> taxRegimesWithFeaturesAndCategories = new ArrayList<>();
        for (TaxRegime taxRegime : taxRegimes) {
            List<TaxpayerCategories> filteredTaxpayerCategories = filterTaxpayerCategory(
                    taxRegime,
                    taxpayerCategories,
                    taxRegimeChoice.getTaxpayerCategories()
            );
            if (filteredTaxpayerCategories.isEmpty()) {
                continue;
            }
            TaxRegimeWithFeaturesAndCategory taxRegimeWithFeaturesAndCategory = new TaxRegimeWithFeaturesAndCategory(
                    taxRegime.getId(),
                    taxRegime.getTitle(),
                    taxRegime.getDescription(),
                    filteredTaxpayerCategories,
                    new ArrayList<>(),
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

    private List<TaxpayerCategories> filterTaxpayerCategory(
            TaxRegime taxRegime,
            List<TaxpayerCategories> taxpayerCategories,
            List<TaxpayerCategoryEnum> choiceTaxpayerCategoryEnums
    ) {
        List<TaxpayerCategories> filteredTaxpayerCategories = taxpayerCategories
                .stream()
                .filter(taxpayerCategory -> taxpayerCategory.getTaxRegime().getId().equals(taxRegime.getId()))
                .toList();
        if (filteredTaxpayerCategories.size() != choiceTaxpayerCategoryEnums.size()) {
            return new ArrayList<>();
        }
        List<TaxpayerCategoryEnum> filteredTaxpayerCategoryEnums = filteredTaxpayerCategories
                .stream()
                .map(TaxpayerCategories::getTaxpayerCategoryEnum)
                .toList();
        if (!new HashSet<>(choiceTaxpayerCategoryEnums).containsAll(filteredTaxpayerCategoryEnums)) {
            return new ArrayList<>();
        }
        return filteredTaxpayerCategories;
    }
}
