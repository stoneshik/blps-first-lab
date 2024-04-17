package lab.blps.services.choice;

import lab.blps.services.entities.TaxRegimeChoice;
import lab.blps.services.entities.TaxRegimeWithFeaturesAndCategory;

import java.util.List;

public interface ChoiceFilter {
    List<TaxRegimeWithFeaturesAndCategory> filter(TaxRegimeChoice taxRegimeChoice);
}
