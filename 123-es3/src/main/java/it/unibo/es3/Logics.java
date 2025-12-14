package it.unibo.es3;

import java.util.List;

/**
 * Interface defining the logic for a GUI application.
 */
public interface Logics {

    /**
     * The number of slots.
     *
     * @return the number of slots
     */
    int size();

    /**
     * Hit the element at the specified position.
     * Updates the internal status
     */
    void hit();

    /**
     * Checks if a winning condition implies the application should close.
     *
     * @return true if the application should close
     */
    boolean toQuit();

    /**
     * @return the list of the selectedPairs
     */
    List<Pair<Integer, Integer>> selectedPairs();
}
