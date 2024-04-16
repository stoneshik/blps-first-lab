package lab.blps.bd.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lab.blps.bd.entites.enums.TaxpayerCategoryEnum;

@Entity
@Table(name = "taxpayer_category")
public class TaxpayerCategory {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tax_regime_id", nullable = false)
    private TaxRegime taxRegime;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "taxpayer_category")
    private TaxpayerCategoryEnum taxpayerCategoryEnum;
}
