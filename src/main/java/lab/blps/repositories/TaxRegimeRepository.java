package lab.blps.repositories;

import lab.blps.bd.entites.TaxFeature;
import lab.blps.bd.entites.TaxRegime;
import lab.blps.bd.entites.TaxpayerCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRegimeRepository extends CrudRepository<TaxRegime, Long> {
    @Query(
            "from TaxRegime tr where tr.maxAnnualIncomeThousands > :maxAnnualIncomeThousands " +
                    "and tr.maxNumberEmployees > :maxNumberEmployees "
    )
    List<TaxRegime> findByMaxAnnualIncomeThousandsAndMaxNumberEmployees(
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees
    );
    @Query(
            "from TaxRegime tr where (tr.taxpayerCategory in :taxpayerCategories " +
            "and tr.maxAnnualIncomeThousands > :maxAnnualIncomeThousands " +
            "and tr.maxNumberEmployees > :maxNumberEmployees) " +
            "or (:taxpayerCategory = 'INDIVIDUAL_ENTREPRENEUR_AND_LEGAL_ENTITY' " +
            "and (tr.taxpayerCategory = 'INDIVIDUAL_ENTREPRENEUR' OR tr.taxpayerCategory = 'LEGAL_ENTITY'))"
    )
    List<TaxRegime> findByTaxpayerCategory(
            @Param("taxpayerCategories") List<TaxpayerCategory> taxpayerCategories,
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees
    );
    @Query(
            "from TaxRegime tr where tr.taxFeature in :taxFeatures " +
            "and tr.maxAnnualIncomeThousands > :maxAnnualIncomeThousands " +
            "and tr.maxNumberEmployees > :maxNumberEmployees " +
            "or (:taxFeatures = 'NO_NEED_KEEP_TAX_RECORDS_AND_OBLIGATION_SUBMIT_DECLARATIONS' " +
            "and (tr.taxFeature = 'NO_NEED_KEEP_TAX_RECORDS' OR tr.taxFeature = 'OBLIGATION_SUBMIT_DECLARATIONS'))"
    )
    List<TaxRegime> findByTaxFeature(
            @Param("taxFeatures") List<TaxFeature> taxFeatures,
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees
    );
    @Query(
            "from TaxRegime tr where tr.taxpayerCategory in :taxpayerCategories " +
            "and tr.taxFeature in :taxFeatures " +
            "and tr.maxAnnualIncomeThousands > :maxAnnualIncomeThousands " +
            "and tr.maxNumberEmployees > :maxNumberEmployees " +
            "or (:taxpayerCategory = 'INDIVIDUAL_ENTREPRENEUR_AND_LEGAL_ENTITY' " +
            "and (tr.taxpayerCategory = 'INDIVIDUAL_ENTREPRENEUR' OR tr.taxpayerCategory = 'LEGAL_ENTITY')) " +
            "or (:taxFeatures = 'NO_NEED_KEEP_TAX_RECORDS_AND_OBLIGATION_SUBMIT_DECLARATIONS' " +
            "and (tr.taxFeature = 'NO_NEED_KEEP_TAX_RECORDS' OR tr.taxFeature = 'OBLIGATION_SUBMIT_DECLARATIONS'))"
    )
    List<TaxRegime> findByTaxpayerCategoryAndTaxFeature(
            @Param("taxpayerCategories") List<TaxpayerCategory> taxpayerCategories,
            @Param("taxFeatures") List<TaxFeature> taxFeatures,
            @Param("maxAnnualIncomeThousands") Long maxAnnualIncomeThousands,
            @Param("maxNumberEmployees") Long maxNumberEmployees
    );
}
