package org.hanuna.rusakov.frame;

import org.hanuna.rusakov.MainProcessor;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author: erokhins
 */
public class MainForm extends JFrame {
    private final JPanel mainPanel = new JPanel();
    private final JPanel graphPanel = new JPanel();
    private final JPanel diagramPanel = new JPanel();
    private final JPanel controlPanel = new JPanel();

    private final JTextField count = new JTextField(); // int > 0
    private final JTextField alpha = new JTextField(); // float > 0
    private final JTextField beta = new JTextField(); // float > 0
    private final JCheckBox  angleFix = new JCheckBox();
    private final JButton    runButton = new JButton();

    private final MainProcessor processor;

    private final int graphSize = 400;
    private final int diagramSize = 400;

    public MainForm(MainProcessor processor) {
        this.processor = processor;
        packForm();
    }

    private void packControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(new JLabel("count:"));
        controlPanel.add(count);

        controlPanel.add(new JLabel("alpha:"));
        controlPanel.add(alpha);

        controlPanel.add(new JLabel("beta:"));
        controlPanel.add(beta);

        angleFix.setText("fix angle");
        controlPanel.add(angleFix);

        controlPanel.add(Box.createVerticalStrut(20));

        runButton.setText("Run");
        controlPanel.add(runButton);
        controlPanel.add(Box.createVerticalStrut(400));
    }

    private void packForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Rusakov Picture");

        graphPanel.setBackground(Color.WHITE);
        graphPanel.setPreferredSize(new Dimension(graphSize, graphSize));
        mainPanel.add(graphPanel);

        diagramPanel.setBackground(Color.WHITE);
        diagramPanel.setPreferredSize(new Dimension(diagramSize, graphSize));
        mainPanel.add(diagramPanel);

        controlPanel.setPreferredSize(new Dimension(200, graphSize));
        packControlPanel();
        mainPanel.add(controlPanel);

        setContentPane(mainPanel);
        pack();
    }
}
