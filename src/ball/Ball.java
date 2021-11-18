package ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ball extends JFrame {
    public Ball() {
        initUI();
    }

    private void initUI() {
        final Surface surface = new Surface();
        add(surface);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });
        setTitle("Ball");
        setSize(2020, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Ball ball = new Ball();
        ball.setVisible(true);
    }
}

//class Surface extends JPanel implements ActionListener {
//    private double width;
//    private double height;
//    private final int DELAY = 50;
//    private double speed = 13;
//    private double xSlide = 0;
//    private double ySlide = 0;
//    private double degree = 70;
//    private double timeCounter = 0;
//    private double lastSpeedY;
//    private Timer timer;
//    private boolean outOFBoundY = false;
//    private boolean once = true;
//    private double timeInitial = 3;
//    private JTextField degreeTextBox;
//    private JTextField speedTextBox;
//    private JTextField cycleTextBox;
//    private JLabel degreeLabel;
//    private JLabel speedLabel;
//    private JLabel cycleLabel;
//    private JButton startButton;
//
//    private boolean startGame = false;
//
//    public Surface() {
//        initTimer();
//        height = height * 0.95 - height * 0.1;
//        speedTextBox = new JTextField(10);
//        degreeTextBox = new JTextField(10);
//        cycleTextBox = new JTextField(10);
//        degreeLabel = new JLabel("Degree");
//        cycleLabel = new JLabel("Cycle");
//        speedLabel = new JLabel("Speed");
//        startButton = new JButton("Start");
//        add(speedLabel);
//        add(speedTextBox);
//        add(degreeLabel);
//        add(degreeTextBox);
//        add(cycleLabel);
//        add(cycleTextBox);
//        add(startButton);
//        getValue();
//    }
//
//    private void initTimer() {
//        timer = new Timer(DELAY, this);
//    }
//
//    public Timer getTimer() {
//        return timer;
//    }
//
//    private void getValue() {
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!speedTextBox.getText().isEmpty() && !degreeTextBox.getText().isEmpty() && !cycleTextBox.getText().isEmpty()) {
//                    degree = Integer.valueOf(degreeTextBox.getText());
//                    speed = Integer.valueOf(speedTextBox.getText());
//                    timeInitial = Integer.valueOf(cycleTextBox.getText());
//                } else if (!degreeTextBox.getText().isEmpty()) {
//                    degree = Integer.parseInt(degreeTextBox.getText());
//                } else if (!speedTextBox.getText().isEmpty()) {
//                    speed = Integer.parseInt(speedTextBox.getText());
//                } else if (!cycleTextBox.getText().isEmpty()) {
//                    timeInitial = Integer.parseInt(speedTextBox.getText());
//                }
//                if (!startGame) {
//                    startGame = true;
//                    timer.start();
//                    startButton.setText("Pause");
//                } else {
//                    startGame = false;
//                    timer.stop();
//                    startButton.setText("Start");
//                }
//            }
//        });
//    }
//
//    private void doDrawing(Graphics2D g2d) {
//        width = getWidth();
//        height = getHeight();
//        lastSpeedY = (Math.sin(Math.PI / 180 * degree) * speed) - (9.81 * timeCounter);
//        if (ySlide == 0 && xSlide == 0) {
//            ySlide = height * 0.95 - height * 0.1;
//            xSlide = width * 0.05;
//        }
//        g2d.drawLine((int) (width * 0.05), (int) (height * 0.05), (int) (width * 0.05), (int) (height * 0.95));
//        g2d.drawLine((int) (width * 0.05), (int) (height * 0.95), (int) (width * 0.95), (int) (height * 0.95));
//        Area area = new Area(new Ellipse2D.Double(xSlide, ySlide, (int) (height * 0.1), (int) (height * 0.1)));
//        g2d.fill(area);
//        xSlide += Math.cos(Math.PI / 180 * degree) * speed;
//        if (outOFBoundY) {
//            ySlide += Math.abs(lastSpeedY);
//            timeCounter -= 0.01;
//        } else {// sinir iceri
//            if (once) {
//                ySlide -= Math.abs(lastSpeedY);
//                timeCounter += 0.01;
//            }
//        }
//    }
//
//    private void firstMove() {
//        ySlide = 0;
//        xSlide = 0;
//        timeCounter = 0;
//        outOFBoundY = false;
//        startButton.setText("Start");
//        once=true;
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        if (ySlide < 0) {
//            outOFBoundY = true;
//        } else if (ySlide > height * 0.95 - height * 0.1) {
//            outOFBoundY = false;
//        }
//
//        if (xSlide > width) {
//            timeInitial--;
//            firstMove();
//        }
//        if (lastSpeedY > (Math.sin(Math.PI / 180 * degree) * speed)) {
//            once = false;
//        }
//        super.paintComponent(g);
//        doDrawing((Graphics2D) g);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (startGame)
//            if (timeInitial > 0) {
//                repaint();
//            } else {
//                startGame = false;
//                timer.stop();
//            }
//
//    }
//}
