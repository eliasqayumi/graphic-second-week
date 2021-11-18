package ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class Surface extends JPanel implements ActionListener {
    private double width;
    private double height;
    private final int DELAY = 50;
    private double speed = 13;
    private double xSlide = 2;
    private double ySlide = 2;
    private double degree = 70;
    private double timeCounter = 0;
    private double lastSpeedY;
    private Timer timer;
    private boolean outOFBoundY = false;
    private boolean dividerBoolean = false;
    private double divider = 1;
    private double timeInitial = 2;
    private JTextField degreeTextBox;
    private JTextField speedTextBox;
    private JTextField cycleTextBox;
    private JLabel degreeLabel;
    private JLabel speedLabel;
    private JLabel cycleLabel;
    private JButton startButton;
    private JButton stopButton;
    private boolean startGame = false;
    private boolean control = true;
    private Graphics2D g2d;
    private Shadow shadow;

    private LinkedList<Shadow> listShadow = new LinkedList<>();

    public Surface() {
        initTimer();
        initAdder();
        getValue();
    }

    private void initAdder() {
        height = height * 0.95 - height * 0.1;
        speedTextBox = new JTextField(10);
        degreeTextBox = new JTextField(10);
        cycleTextBox = new JTextField(10);
        degreeLabel = new JLabel("Degree");
        cycleLabel = new JLabel("Cycle");
        speedLabel = new JLabel("Speed");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        add(speedLabel);
        add(speedTextBox);
        add(degreeLabel);
        add(degreeTextBox);
        add(cycleLabel);
        add(cycleTextBox);
        add(startButton);
        add(stopButton);
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
    }

    public Timer getTimer() {
        return timer;
    }

    private void getValue() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!speedTextBox.getText().isEmpty() && !degreeTextBox.getText().isEmpty() && !cycleTextBox.getText().isEmpty()) {
                    if (Integer.valueOf(degreeTextBox.getText()) > 90) {

                    }
                    degree = Integer.valueOf(degreeTextBox.getText());
                    speed = Integer.valueOf(speedTextBox.getText());
                    timeInitial = Integer.valueOf(cycleTextBox.getText());
                } else if (!degreeTextBox.getText().isEmpty()) {
                    degree = Integer.parseInt(degreeTextBox.getText());
                } else if (!speedTextBox.getText().isEmpty()) {
                    speed = Integer.parseInt(speedTextBox.getText());
                } else if (!cycleTextBox.getText().isEmpty()) {
                    timeInitial = Integer.parseInt(speedTextBox.getText());
                }
                if (!startGame) {
                    startGame = true;
                    timer.start();
                    startButton.setText("Pause");
                } else {
                    startGame = false;
                    timer.stop();
                    startButton.setText("Start");
                }

            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setText("Start");
                firstMove();
                startGame = false;
                timer.stop();
            }
        });
    }

    private void doDrawing() {
        width = getWidth();
        height = getHeight();
        if (control) {
            if (ySlide < height * 0.95 - height * 0.1)
                ySlide += 5;
            else if ((ySlide >= height * 0.95 - height * 0.1) && (xSlide < width * 0.05))
                xSlide += 2;
        }
        lastSpeedY = (Math.sin(Math.PI / 180 * degree) * speed) / divider - (9.81 * timeCounter);
        g2d.drawLine((int) (width * 0.05), (int) (height * 0.05), (int) (width * 0.05), (int) (height * 0.95));
        g2d.drawLine((int) (width * 0.05), (int) (height * 0.95), (int) (width * 0.95), (int) (height * 0.95));
        g2d.fill(new Ellipse2D.Double(xSlide, ySlide, (int) (height * 0.1), (int) (height * 0.1)));
        if (xSlide >= width * 0.05) {
            control = false;
            xSlide += Math.cos(Math.PI / 180 * degree) * speed;
            if (outOFBoundY) {
                ySlide += Math.abs(lastSpeedY);
                timeCounter -= 0.01;
            } else {
                ySlide -= lastSpeedY;
                timeCounter += 0.01;
            }
            shadow = new Shadow(xSlide, ySlide, Math.sin(Math.PI / 180 * degree));
            shadow.setOpaque(false);
            shadow.setBounds(0, 0, getWidth(), getHeight());
            add(shadow);
            listShadow.add(shadow);
        }
    }

    private void firstMove() {
        ySlide = 2;
        xSlide = 2;
        divider = 1;
        timeCounter = 0;
        outOFBoundY = false;
        dividerBoolean = false;
        startButton.setText("Start");
        control = true;
        for (Shadow shadow : listShadow) {
            remove(shadow);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (ySlide < 0) {
            outOFBoundY = true;
        } else if (ySlide > (height * 0.95 - height * 0.1) + 5) {
            outOFBoundY = false;
            dividerBoolean = true;
        }

        if (dividerBoolean) {
            divider += 2;
            dividerBoolean = false;
            timeCounter = 0;
        }
        if (xSlide > width) {
            timeInitial--;
            firstMove();
        }
        g2d = (Graphics2D) g;
        super.paintComponent(g);
        doDrawing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (startGame)
            if (timeInitial > 0) {
                repaint();
            } else {
                startGame = false;
                timer.stop();
            }
    }
}
