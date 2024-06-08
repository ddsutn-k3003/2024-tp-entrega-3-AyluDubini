package ar.edu.utn.dds.k3003.persist;

import ar.edu.utn.dds.k3003.model.PuntosBody;

import javax.persistence.EntityManager;

public class PuntosRepository {
    private EntityManager entityManager ;

    public PuntosRepository(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public PuntosRepository() {
        super();
    }

    public void save(PuntosBody puntos) { //ver
        this.entityManager.persist(puntos);
    }
}
