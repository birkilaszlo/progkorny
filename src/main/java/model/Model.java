package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Birki László
 */

public class Model {

    public Model() {
    }

    /**
     * Véletlen szám generálása
     * @param min véletlenszám generálás intervallum alsó határa
     * @param max véletlenszám generálás intervallum felső határa
     * @param db hány darab véletlen szám készüljön
     * @return különböző véletlenszámokat tartalamzó lista
     */
    public List<Integer> generateRandomNumbers(int min, int max, int db){
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
     * véletlen szám generálása min és max között
     * @return generált véletlenszám
     */
    public int generateCorrentAnswerPlace(int min, int max){
        int correct = 0;
        correct = (int)((Math.random() * max) + min);
        return correct;
    }

}
