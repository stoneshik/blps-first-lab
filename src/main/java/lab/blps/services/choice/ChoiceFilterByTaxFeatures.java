package lab.blps.services.choice;

import lab.blps.bd.entites.TaxFeatures;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ChoiceFilterByTaxFeatures implements ChoiceFilter {
    private final TaxRegimeRepository taxRegimeRepository;

    @Override
    public List<TaxRegimeWithFeaturesAndCategory> filter(TaxRegimeChoice taxRegimeChoice) {
        List<TaxFeatures> taxFeatures = taxRegimeRepository.findTaxFeatures(taxRegimeChoice.getTaxFeatures());
        List<Long> filteredTaxRegimeIds = getTaxRegimeIdsFromTaxFeatures(taxFeatures);
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

    private List<Long> getTaxRegimeIdsFromTaxFeatures(List<TaxFeatures> taxFeatures) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxFeatures taxFeature: taxFeatures) {
            filteredTaxRegimeIds.add(taxFeature.getTaxRegime().getId());
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
