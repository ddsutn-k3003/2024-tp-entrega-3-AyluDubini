package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.ColaboradorDTO;
import ar.edu.utn.dds.k3003.facades.dtos.FormaDeColaborarEnum;
import ar.edu.utn.dds.k3003.model.Colaborador;
import ar.edu.utn.dds.k3003.model.PuntosBody;
import ar.edu.utn.dds.k3003.persist.ColaboradorRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.NoSuchElementException;

public class ColaboradorController {
    private final Fachada fachada;
    private EntityManagerFactory entityManagerFactory;

    /*public ColaboradorController(Fachada fachada) {
        this.fachada = fachada;
    }*/

    public ColaboradorController(Fachada fachada,EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.fachada = fachada;
    }

    /*public void handle(Context ctx) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        ColaboradorRepository repo = new ColaboradorRepository(em);
        Colaborador colaborador = ctx.bodyAsClass(Colaborador.class);
        em.getTransaction().begin();
        repo.save(colaborador);
        em.getTransaction().commit();
        em.close();
        ctx.json(colaborador);
    }*/

    public void agregar(Context context) {
        EntityManager em = entityManagerFactory.createEntityManager();
        var colaboradorDTO = context.bodyAsClass(ColaboradorDTO.class);
        em.getTransaction().begin();
        var colaboradorDTORta = this.fachada.agregar(colaboradorDTO);
        em.getTransaction().commit();
        em.close();
        context.json(colaboradorDTORta);
    }

    public void obtener(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        try {
            var colaboradorDTO = this.fachada.buscarXId(id);
            context.json(colaboradorDTO);
        } catch (NoSuchElementException ex) {
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }

    public void modificar(Context context) { //revisar
        var id = context.pathParamAsClass("id", Long.class).get();
        EntityManager em = entityManagerFactory.createEntityManager();
        var forma = context.bodyAsClass(FormaDeColaborarEnum.class);
        try{
            em.getTransaction().begin();
            var colaboradorDTO = this.fachada.modificar(id, List.of(forma));
            em.getTransaction().commit();
            em.close();
            context.json(colaboradorDTO);
        } catch (NoSuchElementException ex) {
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }

    public void puntos(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
            try {var puntosColaborador = this.fachada.puntos(id);
            context.json(puntosColaborador);
            } catch (NoSuchElementException ex) {
                context.result(ex.getLocalizedMessage());
                context.status(HttpStatus.NOT_FOUND);
            }
    }

    public void actualizarPuntos(Context context) {
        PuntosBody puntos = context.bodyAsClass(PuntosBody.class);
        Double pesosDonados = puntos.getPesosDonados();
        Double viandasDistribuidas = puntos.getViandasDistribuidas();//context.attribute("viandasDistribuidas");
        Double viandasDonadas= puntos.getViandasDonadas();
        Double tarjetasRepartidas= puntos.getTarjetasRepartidas();
        Double heladerasActivas= puntos.getHeladerasActivas();
        try {this.fachada.actualizarPesosPuntos(pesosDonados,
                viandasDistribuidas,
                viandasDonadas,
                tarjetasRepartidas,
                heladerasActivas);
            context.result("Puntos actualizados");
            context.status(HttpStatus.OK);
            }
        catch (NoSuchElementException ex) {
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public void prueba(Context context) {
        EntityManager em = entityManagerFactory.createEntityManager();
        ColaboradorDTO colaborador1 = new ColaboradorDTO("Pepe" , List.of(FormaDeColaborarEnum.DONADOR));
        ColaboradorDTO colaborador2 = new ColaboradorDTO("Jose" , List.of(FormaDeColaborarEnum.TRANSPORTADOR));
        ColaboradorDTO colaborador3 = new ColaboradorDTO("Laura" , List.of(FormaDeColaborarEnum.DONADOR));
        em.getTransaction().begin();
        var colaboradorDTORta1 = this.fachada.agregar(colaborador1);
        var colaboradorDTORta2 = this.fachada.agregar(colaborador2);
        var colaboradorDTORta3 = this.fachada.agregar(colaborador3);
        em.getTransaction().commit();
        em.close();
        this.fachada.actualizarPesosPuntos(0.5 , 1.0 , 1.5,2.0,5.0);
        /*context.json(colaboradorDTORta1); //revisar
        context.json(colaboradorDTORta2);*/
        context.json(colaboradorDTORta3);
    }
    public void borrarBD(Context context) {

    }



}
