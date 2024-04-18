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
            List<TaxpayerCategories> filteredTaxpayerCategories = filterTaxpayerCategory(
                    taxRegime,
                    taxpayerCategories,
                    taxRegimeChoice.getTaxpayerCategories()
            );
            if (filteredTaxpayerCategories.isEmpty()) {
                continue;
            }
            List<TaxFeatures> filteredTaxFeatures = filterTaxFeatures(
                    taxRegime,
                    taxFeatures,
                    taxRegimeChoice.getTaxFeatures()
            );
            if (filteredTaxFeatures.isEmpty()) {
                continue;
            }
            TaxRegimeWithFeaturesAndCategory taxRegimeWithFeaturesAndCategory = new TaxRegimeWithFeaturesAndCategory(
                    taxRegime.getId(),
                    taxRegime.getTitle(),
                    taxRegime.getDescription(),
                    filteredTaxpayerCategories,
                    filteredTaxFeatures,
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

    private List<TaxFeatures> filterTaxFeatures(
            TaxRegime taxRegime,
            List<TaxFeatures> taxFeatures,
            List<TaxFeatureEnum> choiceTaxFeaturesEnums
    ) {
        List<TaxFeatures> filteredTaxFeatures = taxFeatures
                .stream()
                .filter(taxFeature -> taxFeature.getTaxRegime().getId().equals(taxRegime.getId()))
                .toList();
        if (filteredTaxFeatures.size() != choiceTaxFeaturesEnums.size()) {
            return new ArrayList<>();
        }
        List<TaxFeatureEnum> filteredTaxFeaturesEnum = filteredTaxFeatures
                .stream()
                .map(TaxFeatures::getTaxFeatureEnum)
                .toList();
        if (!new HashSet<>(choiceTaxFeaturesEnums).containsAll(filteredTaxFeaturesEnum)) {
            return new ArrayList<>();
        }
        return filteredTaxFeatures;
    }
}
