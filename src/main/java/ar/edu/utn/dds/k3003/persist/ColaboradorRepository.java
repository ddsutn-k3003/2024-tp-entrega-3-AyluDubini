package ar.edu.utn.dds.k3003.persist;

import ar.edu.utn.dds.k3003.model.Colaborador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class ColaboradorRepository {
    static private final EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager;
   // private static AtomicLong seqId = new AtomicLong();

    public ColaboradorRepository(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }
    public ColaboradorRepository() {
        super();
    }

    public Colaborador save(Colaborador colaborador) {
        entityManager = entityManagerFactory.createEntityManager();
        this.entityManager.persist(colaborador); //null


        /*if (Objects.isNull(colaborador.getId())) {
            colaborador.setId(seqId.getAndIncrement());
            this.entityManager.persist(colaborador);
        }
        else {
            this.entityManager.persist(colaborador);
        }*/
         return this.findById(colaborador.getId());
    }

    public Colaborador findById(Long id) {
        return this.entityManager.find(Colaborador.class, id);
    }

    public void remove(Long id){
        this.entityManager.remove(this.findById(id));
    }

    /*public List<Colaborador> all() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Colaborador> criteriaQuery = criteriaBuilder.createQuery(Colaborador.class);
        Root<Colaborador> root = criteriaQuery.from(Colaborador.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }*/



}
