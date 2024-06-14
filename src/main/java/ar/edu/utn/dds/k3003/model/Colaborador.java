package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.FormaDeColaborarEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor

@Entity
public class Colaborador {

    @Id  //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column//(name = "colaborador_id")
    private Long id;
    @Column
    private String nombre;

    //@Transient
    @ElementCollection(targetClass = FormaDeColaborarEnum.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "formas_de_colaborar", joinColumns = @JoinColumn(name = "colaborador_id"))
    @Column(name = "forma")
    private List<FormaDeColaborarEnum> formas;

    public Colaborador(String nombre, List<FormaDeColaborarEnum> formas) {
        this.nombre = nombre;
        this.formas = formas;
    }

    public Colaborador() {
        super();
    }
}
