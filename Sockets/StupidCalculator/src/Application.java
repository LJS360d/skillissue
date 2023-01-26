import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Application extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int WIDTH = 250;
    public static int HEIGHT = 300;
    private String title = "";
    private JFrame frame;
    private Thread thread;
    private boolean running = false;

    private int[] num1 = new int[12];
    private int index1 = -1;
    private int[] num2 = new int[12];
    private int index2 = -1;
    private char operation = ' ';

    public static void main(String[] args) {
        new Application().start();
    }

    public Application() {
        this.frame = new JFrame(title);
    }

    public synchronized void start() {
        this.running = true;
        this.thread = new Thread(this, title);
        buildButtons();
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.add(this);
        this.frame.pack();
        this.frame.setLayout(null);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.thread.start();
    }

    @Override
    public synchronized void run() {
        long lastTimer = System.nanoTime();
        long currentTimer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double progress = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            progress += (now - lastTimer) / ns;
            lastTimer = now;
            while (progress >= 1) {
                update();
                progress--;
                render();
                frames++;
            }
            if (System.currentTimeMillis() - currentTimer > 1000) {
                currentTimer += 1000;
                this.frame.setTitle(title + " " + frames + " fps");
                frames = 0;
            }
        }
        stop();
    }

    void update() {
    }

    void render() {
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        graphics.setColor(Color.WHITE);
        graphics.fillRect(10, 10, this.frame.getWidth() - 45, 30);
        graphics.dispose();
        buffer.show();
    }

    void stop() {
        try {
            this.running = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void buildButtons() {
        JButton b1 = new JButton("1");
        b1.setBounds(10, 50, 50, 50);
        b1.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b1.getText());
        });
        this.frame.add(b1);

        JButton b2 = new JButton("2");
        b2.setBounds(60, 50, 50, 50);
        b2.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b2.getText());
        });
        this.frame.add(b2);
        JButton b3 = new JButton("3");
        b3.setBounds(110, 50, 50, 50);
        b3.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b3.getText());
        });
        this.frame.add(b3);
        JButton b4 = new JButton("4");
        b4.setBounds(10, 100, 50, 50);
        b4.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b4.getText());
        });
        this.frame.add(b4);
        JButton b5 = new JButton("5");
        b5.setBounds(60, 100, 50, 50);
        b5.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b5.getText());
        });
        this.frame.add(b5);
        JButton b6 = new JButton("6");
        b6.setBounds(110, 100, 50, 50);
        b6.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b6.getText());
        });
        this.frame.add(b6);
        JButton b7 = new JButton("7");
        b7.setBounds(10, 150, 50, 50);
        b7.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b7.getText());
        });
        this.frame.add(b7);
        JButton b8 = new JButton("8");
        b8.setBounds(60, 150, 50, 50);
        b8.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b8.getText());
        });
        this.frame.add(b8);
        JButton b9 = new JButton("9");
        b9.setBounds(110, 150, 50, 50);
        b9.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b9.getText());
        });
        this.frame.add(b9);
        JButton b0 = new JButton("0");
        b0.setBounds(10, 200, 50, 50);
        b0.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = Integer.parseInt(b0.getText());
        });
        this.frame.add(b0);
        JButton dot = new JButton(".");
        dot.setBounds(60, 200, 50, 50);
        dot.addActionListener((l) -> {
            if (index1 <= 12)
                num1[++index1] = -1;
        });
        this.frame.add(dot);
        JButton eq = new JButton("=");
        eq.setBounds(110, 200, 50, 50);
        this.frame.add(eq);
        JButton plus = new JButton("+");
        plus.setBounds(165, 50, 50, 50);
        this.frame.add(plus);
        JButton minus = new JButton("-");
        minus.setBounds(165, 100, 50, 50);
        this.frame.add(minus);
        JButton times = new JButton("*");
        times.setBounds(165, 150, 50, 50);
        this.frame.add(times);
        JButton div = new JButton("/");
        div.setBounds(165, 200, 50, 50);
        this.frame.add(div);
    }
}
