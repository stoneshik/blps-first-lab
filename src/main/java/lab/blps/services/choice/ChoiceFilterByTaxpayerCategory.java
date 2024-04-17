package lab.blps.services.choice;

import lab.blps.bd.entites.TaxpayerCategories;
import lab.blps.repositories.TaxRegimeRepository;
import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ChoiceFilterByTaxpayerCategory implements ChoiceFilter {
    private final TaxRegimeRepository taxRegimeRepository;

    @Override
    public List<TaxRegimeWithFeaturesAndCategory> filter(TaxRegimeChoice taxRegimeChoice) {
        return null;
    }

    private List<Long> getFilteredIdsFromCategories(List<TaxpayerCategories> taxpayerCategories) {
        List<Long> filteredTaxRegimeIds = new ArrayList<>();
        for (TaxpayerCategories taxCategory: taxpayerCategories) {
            filteredTaxRegimeIds.add(taxCategory.getId());
        }
        return filteredTaxRegimeIds;
    }
}
