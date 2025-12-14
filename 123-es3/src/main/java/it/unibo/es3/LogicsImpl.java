package it.unibo.es3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
    @SuppressFBWarnings(value = "DMI_RANDOM_USED_ONLY_ONCE", justification = "Uso occasionale, performance non critica")
    public LogicsImpl(final int size) {
        this.size = size;
        this.selectedPairs = new ArrayList<>();
        final Random random = new Random();
        while (this.selectedPairs.size() < 3) {
            final Pair<Integer, Integer> pos = new Pair<>(random.nextInt(size), random.nextInt(size));
            if (!this.selectedPairs.contains(pos)) {
                this.selectedPairs.add(pos);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
       return this.size;
    }

    private void addSelectedPair(final Pair<Integer, Integer> position) {
        if (!selectedPairs.contains(position)) {
            selectedPairs.add(position);
        }
    }

    private List<Pair<Integer, Integer>> neighbors(final Pair<Integer, Integer> cell) {
        final List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                final Pair<Integer, Integer> newPair = new Pair<>(cell.x() + i, cell.y() + j);
                if (0 <= newPair.x() && newPair.x() < size && 0 <= newPair.y() && newPair.y() < size) {
                    result.add(newPair);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit() {
        final List<Pair<Integer, Integer>> currentStatus = new ArrayList<>(this.selectedPairs);
        for (final Pair<Integer, Integer> pair : currentStatus) {
            this.neighbors(pair).forEach(this::addSelectedPair);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return selectedPairs.size() == size * size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Integer, Integer>> selectedPairs() {
        return new ArrayList<>(this.selectedPairs);
    }
}
