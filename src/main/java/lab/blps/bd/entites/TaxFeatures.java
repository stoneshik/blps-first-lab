package lab.blps.bd.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lab.blps.bd.entites.enums.TaxFeatureEnum;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tax_features")
public class TaxFeatures implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_regime_id", nullable = false)
    private TaxRegime taxRegime;
    @NotNull
    @Column(name = "tax_feature")
    private TaxFeatureEnum taxFeatureEnum;
}
