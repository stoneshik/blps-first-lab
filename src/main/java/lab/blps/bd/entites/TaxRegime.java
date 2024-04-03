package lab.blps.bd.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tax_regime")
public class TaxRegime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tax_regime_id_seq")
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "taxpayer_category")
    private TaxpayerCategory taxpayerCategory;
    @Enumerated(EnumType.STRING)
    @Column(name = "feature")
    private TaxFeature taxFeature;
    @Min(value = 100L)
    @Column(name = "max_annual_income_thousands")
    private Long maxAnnualIncomeThousands;
    @Min(value = 0L)
    @Column(name = "max_number_employees")
    private Long maxNumberEmployees;
    @NotNull
    @Column(name = "title", length = 100)
    private String title;
    @NotNull
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
