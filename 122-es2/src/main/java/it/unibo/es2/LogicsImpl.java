package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int size;
    private final List<Pair<Integer, Integer>> selectedPairs; 

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.size = size;
        this.selectedPairs = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hit(final Pair<Integer, Integer> position) {
       if (this.selectedPairs.contains(position)) {
            this.selectedPairs.remove(position);
            return " ";
       } else {
            this.selectedPairs.add(position);
            return "*";
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        final boolean rowFull = IntStream.range(0, size)
            .anyMatch(row -> 
                selectedPairs.stream()
                    .filter(p -> p.x() == row)
                    .count() == size
            );
        final boolean colFull = IntStream.range(0, size)
            .anyMatch(col ->
                selectedPairs.stream()
                    .filter(p -> p.y() == col)
                    .count() == size
            );

        return rowFull || colFull;
    }
}
