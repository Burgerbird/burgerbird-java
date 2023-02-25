import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
public class FlappyBird implements ActionListener, MouseListener, KeyListener{

    public static FlappyBird flappyBird;

    public final int WIDTH = 800, HEIGH = 800;
    public Renderer renderer;

    public Rectangle bird;

    public ArrayList<Rectangle> columns;

    public int ticks, yMotion, score;

    public boolean gameOver, started;

    public Random rand;

    public FlappyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);


        renderer = new Renderer();
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Burger Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGH);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGH / 2 - 10, 20 , 20);
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

        if(start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGH -  height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0,width,HEIGH - height - space));
        }
        else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGH -  height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0,width,HEIGH - height - space));
        }
    }
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.GREEN.darker());
        g.fillRect(column.x, column.y, column. width, column.height);
    }

    public void jump() {
        if(gameOver) {

            bird = new Rectangle(WIDTH / 2 - 10, HEIGH / 2 - 10, 20 , 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);
            gameOver = false;
        }
        if(!started) {
            started = true;
        } else if(!gameOver){
            if(yMotion > 0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 10;
        ticks++;

        if(started) {


            for(int i = 0; i < columns.size(); i++) {
                Rectangle column =  columns.get(i);
                column.x -= speed;
            }

            if(ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for(int i = 0; i < columns.size(); i++) {
                Rectangle column =  columns.get(i);
                if(column.x + column.width < 0) {
                    columns.remove(column);
                    if(column.y == 0) {
                        addColumn(false);
                    }
                }
            }

            bird.y += yMotion;

            for(Rectangle column : columns) {
                if(column.y == 0 &&bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2  < column.x + column.width / 2 + 10) {
                    score++;
                }
                if(column.intersects(bird)) {
                    gameOver = true;
                    bird.x = column.x - bird.width;
                }
            }
            if(bird.y > HEIGH - 120 || bird.y < 0) {

                gameOver = true;
            }
            if(bird.y + yMotion >= HEIGH  - 120) {
                bird.y = HEIGH - 120 - bird.height;
            }
        }

        renderer.repaint();
    }

    public void repaint(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0,0, WIDTH, HEIGH);

        g.setColor(Color.orange);
        g.fillRect(0,HEIGH - 120,WIDTH, 120);

        g.setColor(Color.GREEN);
        g.fillRect(0,HEIGH - 120,WIDTH, 20);


        g.setColor(Color.RED);
        g.fillRect(bird.x, bird.y , bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column );
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",1,100));
        if (!started) {

            g.drawString("Click to Start!",100,HEIGH / 2 - 50);
        }
        if (gameOver) {

            g.drawString("Game Over!",100,HEIGH / 2 - 50);
        }
        if(!gameOver && started) {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100 );
        }
    }
    public static void main(String[] args) {
        flappyBird = new FlappyBird();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        jump();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
