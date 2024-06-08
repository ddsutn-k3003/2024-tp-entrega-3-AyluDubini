package ar.edi.itn.dds.k3003.model;

import ar.edu.utn.dds.k3003.model.Colaborador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static ar.edu.utn.dds.k3003.facades.dtos.FormaDeColaborarEnum.DONADOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPersistencia {

        static EntityManagerFactory entityManagerFactory ;
        EntityManager entityManager ;

        @BeforeAll
        public static void setUpClass() throws Exception {
            entityManagerFactory = Persistence.createEntityManagerFactory("colaboradoresdb");
        }
        @BeforeEach
        public void setup() throws Exception {
            entityManager = entityManagerFactory.createEntityManager();
        }
        @Test
        public void testConectar() {
// vacío, para ver que levante el ORM
        }
    @Test
    public void testGuardarYRecuperarDoc() throws Exception {
        Colaborador col1 = new Colaborador("pepe", List.of(DONADOR));
        entityManager.getTransaction().begin();
        entityManager.persist(col1);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        Colaborador col2 = entityManager.find(Colaborador.class,1L);

        assertEquals(col1.getNombre(), col2.getNombre()); // también puede redefinir el equals
    }

}


