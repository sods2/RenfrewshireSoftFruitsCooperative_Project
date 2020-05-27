package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

/**
 * All operation fo batch are executed on this class
 */
public class BachManager {

    /**
     * Checking batch weight
     *
     * @param weight batch's weight
     * @return false if weight min and max is exceeded
     */
    public boolean checkBatchWeight(int weight) {

        return weight < 100 && weight > 0;
    }

}
