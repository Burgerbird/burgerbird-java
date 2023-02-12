import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Container;
import java.awt.Component;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.Renderer;
import javax.swing.JComponent;

class Update {

    public class BurgerBirdSource {

        public static void actionPerformed(ActionListener e) {


            public class BurgerBird implements ActionListener, KeyListener {

                public static BurgerBird burgerBird;
                public final int WIDTH = 800, HEIGHT = 800;
                public BurgerBird.Renderer renderer;
                public Rectangle bird;
                public int ticks, yMotion, score;
                public ArrayList<Rectangle> columns;
                public Random rand;
                public boolean gameOver, started;

                public void repaint(Graphics g) {
                    g.setColor(Color.CYAN);
                    g.fillRect(0, 0, WIDTH, HEIGHT);
                    g.setColor(Color.ORANGE);
                    g.fillRect(0, HEIGHT - 120, WIDTH, 120);
                    g.setColor(Color.RED);
                    g.fillRect(bird.x, bird.y, bird.width, bird.height);
                    for (Rectangle column : columns) {
                        paintColumn(g, column);
                    }
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", 1, 100));
                }


                class Renderer extends JComponent {
                    public static final long serialVersionUID = 1L;

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        BurgerBird.burgerBird.repaint(g);
                    }
                }


                public BurgerBird() {
                    JFrame jframe = new JFrame();
                    Timer timer = new Timer(20, this);

                    renderer = new BurgerBird.Renderer();
                    rand = new Random();

                    jframe.add(renderer);
                    jframe.setTitle("Burger Bird");
                    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jframe.setSize(WIDTH, HEIGHT);
                    jframe.addKeyListener(this);
                    jframe.setResizable(false);
                    jframe.setVisible(true);

                    bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
                    columns = new ArrayList<Rectangle>();

                    addColumn(true);
                    addColumn(true);
                    addColumn(true);
                    addColumn(true);

                    timer.start();
                }

                public void addColumn(boolean start) {
                    int space = 300;
                    int width = 100;
                    int height = 50 + rand.nextInt(300);

                    if (start) {
                        columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
                        columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
                    } else {
                        columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
                        columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
                    }
                }

                public void paintColumn(Graphics g, Rectangle column) {
                    g.setColor(Color.green.darker());
                    g.fillRect(column.x, column.y, column.width, column.height);
                }

                public void jump() {
                    if (gameOver) {
                        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
                        columns.clear();
                        yMotion = 0;
                    }
                }
            }
        }
    }
}




