package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Birki László
 */

public class Model {

    /**
     * Slf4j logger.
     */
    public final static Logger logger = LoggerFactory.getLogger(Model.class);

    /**
     * Paraméter nélküli construktor.
     */
    public Model() {
    }

    /**
     * Véletlen szám generálása.
     * @param min véletlenszám generálás intervallum alsó határa.
     * @param max véletlenszám generálás intervallum felső határa.
     * @param db hány darab véletlen szám készüljön.
     * @return különböző véletlenszámokat tartalamzó lista.
     */
    public List<Integer> generateRandomNumbers(int min, int max, int db){
        logger.info("Véletlenszám generálása a kérdés-válaszhoz.");
        Set<Integer> rn = new HashSet<Integer>();
        int number = 0;
        while(rn.size() != 4){
            number = (int) ((Math.random() * max) + min);
            rn.add(number);
        }
        List<Integer> randomNumber = new ArrayList<Integer>(rn);
        return randomNumber;
    }

    /**
     * véletlen szám generálása min és max között.
     * @param min véletlenszám generálás intervallum alsó határa.
     * @param max véletlenszám generálás intervallum felső határa.
     * @return generált véletlenszám
     */
    public int generateCorrentAnswerPlace(int min, int max){
        logger.info("Véletlenszám generálása a helyes válasz helyének megadásához.");
        int correct = 0;
        correct = (int)((Math.random() * max) + min);
        return correct;
    }

}
