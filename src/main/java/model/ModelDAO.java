package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

/**
 * @author Birki László
 */

public class ModelDAO {
    /**
     * Slf4j logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ModelDAO.class);
    /**
     * paraméter nélküli alap constructor.
     */
    public ModelDAO() {
    }
    /**
     * EntityManagerFactory.
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
    /**
     * EntityManager.
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Bejegyzés rögzítése a szavak táblába.
     * @param angol új angol szó
     * @param magyar új magyar szó
     */
    public void createSzavak(String angol, String magyar){
        logger.info("Új szó mentése a szavak táblába.");
        em.getTransaction().begin();
        szavak sz = new szavak(angol, magyar);
        em.persist(sz);
        em.getTransaction().commit();

    }

    /**
     * Bejegyzés olvasása a szavak táblából.
     * @param id elsődtelegs kulcs
     * @return beazonosított bejegyzés
     */
    public szavak readSzavak(int id){
        logger.info("Angol és magyar szavak keresése ID alapján.");
        return em.find(szavak.class, id);
    }

    /**
     * A szavak tábla összes bejegyzésének lekérése.
     * @return szavakat tartalmazó lista
     */
    public List<szavak> osszesSzo(){
        logger.info("Szavak tábla tartalmának letöltése a list-be.");
        TypedQuery<szavak> query = em.createQuery( "select t from model.szavak t", szavak.class );
        return query.getResultList();
    }

    /**
     * szavak tábla bejegyzéseinek lekérése.
     * @return aktuális bejegyzés szám
     */
    public int tableCount() {
        logger.info("Szavak tábla sorainak számának lekérése.");
        Query q = em.createQuery("SELECT count(*) FROM szavak");
        Number result = (Number) q.getSingleResult();
        return result.intValue();

    }

}
