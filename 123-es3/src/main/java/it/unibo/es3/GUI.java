package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final transient LogicsImpl logic;
    private final Integer size;

    /**
     * Constructor.
     *
     * @param size the size of the grid
     */
    public GUI(final int size) {
        this.size = size;
        this.logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        final JButton hitButton = new JButton(">");
        this.getContentPane().add(BorderLayout.SOUTH, hitButton);

        hitButton.addActionListener(e -> {
            logic.hit();
            this.updateGrid(logic.selectedPairs());
            if (logic.toQuit()) {
                dispose();
            }
        });

        // Create buttons and add them to the panel
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton button = new JButton(" ");
                this.cells.add(button);
                panel.add(button);
            }
        }
        pack();
        this.setVisible(true);
        this.updateGrid(logic.selectedPairs());
    }

    /**
     * update the status of the grid.
     * 
     * @param selectedPairs the grid to update.
     */
    protected void updateGrid(final List<Pair<Integer, Integer>> selectedPairs) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells.get(i * size + j)
                    .setText(selectedPairs.contains(new Pair<>(i, j)) ? "*" : "");
            }
        }
    }
}
