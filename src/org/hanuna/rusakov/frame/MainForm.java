package org.hanuna.rusakov.frame;

import org.hanuna.rusakov.MainProcessor;
import sun.awt.VerticalBagLayout;
import sun.print.PeekGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

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
    private final int pointRadius = 3;

    private final int diagramSize = 500;
    private final int columnCount = 40;
    private final int thickLine = 6;

    public MainForm(MainProcessor processor) {
        this.processor = processor;
        packForm();
    }

    private void setStartValues() {
        count.setText("100");
        alpha.setText("1.2");
        beta.setText("2.3");
        angleFix.setSelected(true);
    }

    private void packControlPanel() {
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        setStartValues();

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
        runButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runActions();
            }
        });
        controlPanel.add(runButton);
        controlPanel.add(Box.createVerticalStrut(400));
    }

    private void runActions() {
        int c = -1;
        float a = -1, b = -1;
        boolean angle = false;
        try {
            c = Integer.parseInt(count.getText());
        } catch (Exception e) {
            c = -1;
        }

        try {
            a = Float.parseFloat(alpha.getText());
        } catch (Exception e) {
            a = -1;
        }

        try {
            b = Float.parseFloat(beta.getText());
        } catch (Exception e) {
            b = -1;
        }
        if (a < 0 || b < 0 || c < 0) {
            if (a < 0) {
                alpha.setText("");
            }
            if (b < 0) {
                beta.setText("");
            }
            if (c < 0) {
                count.setText("");
            }
            return;
        }
        angle = angleFix.isSelected();
        processor.calculate(c, a, b, angle);

        drawPictures();
    }

    private Image getGraphImage(int size) {
        BufferedImage image = new BufferedImage(2 * size, 2 * size, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 2 * size, 2 * size);
        graphics.setColor(Color.BLACK);

        graphics.drawLine(size, 0, size, 2 * size);
        graphics.drawLine(0, size, 2 * size, size);

        for (MainProcessor.Point point :  processor.getGraph(size)) {
            int x = point.getX() + size - pointRadius / 2;
            int y = point.getY() + size - pointRadius / 2;
            graphics.fillOval(x, y, pointRadius, pointRadius);
        }
        return image;
    }

    private Image getDiagramImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(thickLine, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));

        List<Integer> values = processor.getDiagram(columnCount, height);
        for (int i = 0; i < columnCount; i++) {
            int x = width * (2 * i + 1) / (2 * columnCount);
            graphics.drawLine(x, height, x, height - values.get(i));
        }
        return image;
    }

    private void drawPictures() {
        graphPanel.getGraphics().drawImage(getGraphImage(graphSize / 2), 0, 0, null);
        diagramPanel.getGraphics().drawImage(getDiagramImage(diagramSize, graphSize), 0, 0, null);
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
