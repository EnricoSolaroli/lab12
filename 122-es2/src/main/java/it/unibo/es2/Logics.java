package it.unibo.es2;

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
     *
     * @param pair the position coordinate
     * @return the new String to display on the button ("*" or " ")
     */
    String hit(Pair<Integer, Integer> pair);

    /**
     * Checks if a winning condition implies the application should close.
     *
     * @return true if the application should close
     */
    boolean toQuit();
}
