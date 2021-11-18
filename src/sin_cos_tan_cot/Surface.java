package sin_cos_tan_cot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

class Surface extends JPanel {
    private double width;
    private double height;
    //    half-wave goes in x coordinate in 180 points
    private static final int SCALE = 180;
    //    how many does it cycle
    private int cycles = 10;
    //    it holds value of all points should be painted,
    private double[] waves;
    //    in total how many points should be painted
    private int points;
    //    height of frequency
    private double heFreq = 200;
    //    give tools for drawing
    private Graphics2D g2d;
    //    make it 2 pixel coordinate dimension
    private BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    //    add text fields
    private JTextField widthTextBox;
    private JTextField heightTextBox;
    //    add label for text fields
    private JLabel hLabel;
    private JLabel wLabel;
    //    add buttons
    private JButton button;
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton cotButton;
    private boolean sinShow = false;
    private boolean cosShow = false;
    private boolean tanShow = false;
    private boolean cotShow = false;

    public Surface() {
        initialAdd();
    }

    private void initialAdd() {
        heightTextBox = new JTextField(10);
        widthTextBox = new JTextField(10);
        hLabel = new JLabel("Wave height");
        wLabel = new JLabel("Wave width");
        button = new JButton("Submit");
        sinButton = new JButton("SIN");
        cosButton = new JButton("COS");
        tanButton = new JButton("TAN");
        cotButton = new JButton("COT");
        add(hLabel);
        add(heightTextBox);
        add(wLabel);
        add(widthTextBox);
        add(button);
        add(sinButton);
        add(cosButton);
        add(tanButton);
        add(cotButton);
    }

    private void doDrawing() {
        width = getWidth();
        height = getHeight();
        points = SCALE * (cycles + 2) * 2;
        waves = new double[points];
        getValue();
        drawSin();
        drawCos();
        drawTan();
        drawCot();
        drawDimension(g2d);
    }

    private void drawSin() {
        sinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sinShow = (!sinShow ? true : false);
            }
        });
        if (sinShow) {
            sin();
            drawingWave(g2d, Color.black);
            repaint();
        }
    }

    private void drawCos() {
        cosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cosShow = (!cosShow ? true : false);
            }
        });
        if (cosShow) {
            cos();
            drawingWave(g2d, Color.black);
            repaint();
        }
    }

    private void drawTan() {
        tanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tanShow = (!tanShow ? true : false);
            }
        });
        if (tanShow) {
            tan();
            drawingWave(g2d, Color.black);
            repaint();
        }
    }

    private void drawCot() {
        cotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cotShow = (!cotShow ? true : false);
            }
        });
        if (cotShow) {
            cot();
            drawingWave(g2d, Color.black);
            repaint();
        }
    }

    private void getValue() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!heightTextBox.getText().isEmpty() && !widthTextBox.getText().isEmpty()) {
                    cycles = Integer.valueOf(widthTextBox.getText());
                    heFreq = Integer.valueOf(heightTextBox.getText());
                } else if (!widthTextBox.getText().isEmpty())
                    cycles = Integer.parseInt(widthTextBox.getText());
                else if (!heightTextBox.getText().isEmpty())
                    heFreq = Integer.parseInt(heightTextBox.getText());
                repaint();
            }
        });
    }

    private void sin() {
        for (int i = 0; i < points; i++)
            waves[i] = -Math.sin((Math.PI / SCALE) * i);
    }

    private void cos() {
        for (int i = 0; i < points; i++)
            waves[i] = -Math.cos((Math.PI / SCALE) * i);
    }

    private void tan() {
        for (int i = 0; i < points; i++)
            waves[i] = -Math.tan((Math.PI / SCALE) * i);
    }

    private void cot() {
        for (int i = 0; i < points; i++)
            waves[i] = -1 / Math.tan((Math.PI / SCALE) * i);
    }

    private void drawingWave(Graphics2D g2d, Color color) {
        g2d.setPaint(color);
        double winPoints=width/points;
        for (double i = (width * 0.3); i < (points - width * 0.3); i++)
            g2d.draw(new Line2D.Double((i - 1) *winPoints, waves[(int) i - 1] * heFreq * .95 + height / 2,
                    i * winPoints, waves[(int) i] * heFreq * .95 + height / 2));
    }

    private void drawDimension(Graphics2D g2d) {

        g2d.setPaint(Color.BLACK);
        for (int i = (int) width / 2; i < width * 0.95; i += 10) {
            g2d.drawLine(i, (int) (height / 2 - 2), i, (int) (height / 2 + 2));
            g2d.drawLine((int) width - i, (int) (height / 2 - 2), (int) width - i, (int) (height / 2 + 2));
        }
        for (int i = (int) height / 2; i < height * 0.95; i += 10) {
            g2d.drawLine((int) (width / 2 - 2), i, (int) (width / 2 + 2), i);
            g2d.drawLine((int) (width / 2 - 2), (int) height - i, (int) (width / 2 + 2), (int) height - i);
        }
        g2d.setStroke(bs1);
        // X Coordinate System
        g2d.drawLine((int) (width * 0.05), (int) height / 2, (int) (width * 0.95), (int) height / 2);
        // Y Coordinate System
        g2d.drawLine((int) (width * 0.5), (int) (height * 0.05), (int) (width / 2), (int) (height * 0.95));
        // Left Row
        g2d.drawLine((int) (width * 0.05), (int) height / 2, (int) (width * 0.05) + 5, (int) (height / 2) - 4);
        g2d.drawLine((int) (width * 0.05), (int) height / 2, (int) (width * 0.05) + 5, (int) (height / 2) + 4);
        // Right Row
        g2d.drawLine((int) (width * 0.95) - 5, (int) height / 2 - 4, (int) (width * 0.95), (int) (height / 2));
        g2d.drawLine((int) (width * 0.95) - 5, (int) height / 2 + 4, (int) (width * 0.95), (int) (height / 2));
        // Up Row
        g2d.drawLine((int) (width * 0.5), (int) (height * 0.05), (int) (width * 0.5) + 4, (int) (height * 0.05) + 5);
        g2d.drawLine((int) (width * 0.5), (int) (height * 0.05), (int) (width * 0.5) - 4, (int) (height * 0.05) + 5);
        // down Row
        g2d.drawLine((int) (width * 0.5), (int) (height * 0.95), (int) (width * 0.5) + 4, (int) (height * 0.95) - 5);
        g2d.drawLine((int) (width * 0.5), (int) (height * 0.95), (int) (width * 0.5) - 4, (int) (height * 0.95) - 5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        super.paintComponent(g);
        doDrawing();
    }
}
