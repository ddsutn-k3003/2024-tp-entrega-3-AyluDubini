package ar.edu.utn.dds.k3003.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PuntosBody{ //persistir un único objeto que se puede modificar
    @Column
    private Double pesosDonados;
    @Column
    private Double viandasDistribuidas;
    @Column
    private Double viandasDonadas;
    @Column
    private Double tarjetasRepartidas;
    @Column
    private Double heladerasActivas;

    protected PuntosBody() {
        super();
    }

    public PuntosBody(Double pesosDonados , Double viandasDistribuidas, Double viandasDonadas,
                      Double tarjetasRepartidas, Double heladerasActivas) {
        this.pesosDonados = pesosDonados;
        this.viandasDistribuidas = viandasDistribuidas;
        this.viandasDonadas =viandasDonadas;
        this.tarjetasRepartidas = tarjetasRepartidas;
        this.heladerasActivas = heladerasActivas;
    }
}

