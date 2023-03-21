import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MCWindow extends JPanel{
    private ArrayList<City> cities = new ArrayList<City>();
    private int[] missileCount = new int[]{10, 10, 10};
    private ArrayList<Missile> activeMissiles = new ArrayList<Missile>();
    private ArrayList<Explosion> activeExplosions = new ArrayList<Explosion>();
    private int score;
    private static final String[] BACKGROUND_MUSIC = new String[]{"BackgroundMusic1.wav", "BackgroundMusic2.wav", "BackgroundMusic3.wav", "BackgroundMusic4.wav", "BackgroundMusic5.wav", "BackgroundMusic6.wav", "BackgroundMusic7.wav", "BackgroundMusic8.wav", "BackgroundMusic9.wav", "BackgroundMusic10.wav"};
    private static final String[] END_SOUNDS = new String[]{"EndSound1.wav", "EndSound2.wav", "EndSound3.wav", "WinSound1.wav", "WinSound2.wav"};
    private static final String[] EXPLOSION_SOUNDS = new String[]{"ExplosionSound.wav"};
    private static final String[] INTRO_MUSIC = new String[]{"IntroMusic.wav"};
    private static final String[] NUKE_SOUNDS = new String[]{"NukeSound.wav"};
    private static final String[] PING_SOUNDS = new String[]{"PingSound1.wav", "PingSound2.wav"};
    private static final String[] RELOAD_SOUNDS = new String[]{"ReloadSound1.wav", "ReloadSound2.wav"};
    private static final String[] SHOOTING_SOUNDS = new String[]{"ShootingSound1.wav", "ShootingSound2.wav"};
    private SoundEffect explosionSound = new SoundEffect("res/" + EXPLOSION_SOUNDS[0]);
    private SoundEffect shootingSound = new SoundEffect("res/" + SHOOTING_SOUNDS[0]);
    private SoundEffect nukeSound = new SoundEffect("res/" + NUKE_SOUNDS[0]);
    private SoundEffect backgroundMusic = new SoundEffect("res/" + BACKGROUND_MUSIC[0]);
    private SoundEffect introMusic = new SoundEffect("res/" + INTRO_MUSIC[0]);
    private SoundEffect endSound = new SoundEffect("res/" + END_SOUNDS[0]);
    private SoundEffect pingSound = new SoundEffect("res/" + PING_SOUNDS[0]);
    private SoundEffect reloadSound = new SoundEffect("res/" + RELOAD_SOUNDS[0]);
    private Random rng;
    private boolean stopped;
    private int enemySpeed;
    private int playerSpeed;
    
    public MCWindow(){
        super();
        setBackground(Color.BLACK);
        backgroundMusic.play();
    }

    public int getScore(){
        return score;
    }

    public void speedUp(){
        playerSpeed++;
        enemySpeed++;
    }

    public void slowDown(){
        playerSpeed--;
        enemySpeed--;
    }

    public void reload(){
        reloadSound.play();
        missileCount[0] = 10;
        missileCount[1] = 10;
        missileCount[2] = 10;
    }

    public void explodeAll(){
        Iterator<Missile> mit = activeMissiles.iterator();
        while(mit.hasNext()){
            Missile m = mit.next();
            explode(m.getCenterX(), m.getCenterY());
            mit.remove();
        }
    }

    public void setup(){
        introMusic.play();
        missileCount = new int[]{10, 10, 10};
        score = 0;
        enemySpeed = 3;
        playerSpeed = 5;
        rng = new Random();
        stopped = false;
        cities.clear();
        activeMissiles.clear();
        activeExplosions.clear();

        int w = getWidth();
        int h = getHeight();
        cities.add(new City(w/5, h - (h/10) - (w/24), w/192));
        cities.add(new City(w/5 + w/12,  h - (h/10) - (w/24), w/192));
        cities.add(new City(w/5 + w/6, h - (h/10) - (w/24), w/192));
        cities.add(new City(3*w/5, h - (h/10) - (w/24), w/192));
        cities.add(new City(3*w/5 + w/12, h - (h/10) - (w/24), w/192));
        cities.add(new City(3*w/5 + w/6, h - (h/10) - (w/24), w/192));
    }
    
    public void update(){
        Iterator<Missile> mit = activeMissiles.iterator();
        while(mit.hasNext()){
            Missile m = mit.next();
            m.move();
            if(m.atTarget()){
                explode((int) m.getTarget().getX(), (int) m.getTarget().getY());
                mit.remove();
            }else{
                Iterator<Explosion> eit2 = activeExplosions.iterator();
                while(eit2.hasNext()){
                    Explosion e = eit2.next();
                    if(e.contains(m.getHead().getX(), m.getHead().getY())){
                        if(m.isEnemy()){
                            score += 100;
                            if(score % 1000 == 0){
                                reload();
                                enemySpeed++;
                                pingSound.play();
                            }
                        }
                        explode((int) m.getHead().getX(), (int) m.getHead().getY());
                        mit.remove();
                        //Break to stop comparison of removed missile
                        break;
                    }
                }
            }
        }
        
        Iterator<Explosion> eit = activeExplosions.iterator();
        while(eit.hasNext()){
            Explosion e = eit.next();
            if(e.grow()){
                eit.remove();
            }else{
                Iterator<City> cit = cities.iterator();
                while(cit.hasNext()){
                    City c = cit.next();
                    if(e.contains(c.getHead().getX(), c.getHead().getY())){
                        cit.remove();
                    }
                }
            }
        }

        repaint();
    }

    public void shoot(int x, int y){
        int w = getWidth();
        int h = getHeight();
        int section = x / (w / 3);
        if(section == 3){
            section--;
        }
        if(missileCount[0] == 0 && missileCount[1] == 0 && missileCount[2] == 0){
            return;
        }
        if(missileCount[section] < 1){
            switch(section){
                case 0:
                    section = (missileCount[1] == 0)? 2:1;
                    break;
                case 1:
                    if(missileCount[0] == 0){
                        section = 2;
                    }else if(missileCount[2] == 0){
                        section = 0;
                    }else{
                        section = (x / (w / 2) == 0)?0:2;
                    }
                    break;
                case 2:
                    section = (missileCount[1] == 0)? 0:1;
                    break;
            }
        }
        Point[] shooterLocations = new Point[]{new Point(w/10, h-(h/4)), new Point(w/2, h-(h/4)), new Point(18*w/20, h-(h/4))};
        missileCount[section]--;
        activeMissiles.add(new Missile((int)shooterLocations[section].getX(), (int)shooterLocations[section].getY(), 10, playerSpeed, new Point(x, y)));
        shootingSound.play();
    }

    public void explode(int x, int y){
        activeExplosions.add(new Explosion(x, y, 10));
        explosionSound.play();
    }

    public void nuke(){
        nukeSound.play();
        activeExplosions.add(new Explosion(500, 200, 10, 150));
    }

    public void spawnEnemy(boolean b){
        if(b || rng.nextInt(20) > 15 - Math.floor((double) score / 1000)){
            activeMissiles.add(new Missile(rng.nextInt(getWidth()), 0, 10, enemySpeed, cities.get(rng.nextInt(cities.size())).getHead(), true));
            shootingSound.play();
        }
    }

    public boolean gameOver(){
        if(cities.size() == 0 && !stopped){
            endSound.play();
            return true;
        }
        return false;
    }

    public boolean outOfMissiles(){
        return missileCount[0] + missileCount[1] + missileCount[2] == 0;
    }

    public void pause(){
        stopped = true;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        
        g2.drawImage(new ImageIcon("background.jpg").getImage(), 0, 0, w, h, null);
        
        g2.setColor(Color.GREEN);
        int[][] offsets = new int[][]{{0, 0, w/20, w/10, 3*w/20, w/5, 9*w/20, w/2, 11*w/20, 12*w/20, 17*w/20, 18*w/20, 19*w/20, w, w},{h, h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h}};
        g2.fill(new Polygon(offsets[0], offsets[1], offsets[0].length));

        g2.setColor(Color.BLUE);
        for(City c: cities){
            g2.fill(c);
        }
        
        g2.setFont(new Font("Comic Sans", Font.BOLD, w/20));
        g2.drawString(Integer.toString(missileCount[0]), offsets[0][3], h-(h/8));
        g2.drawString(Integer.toString(missileCount[1]), offsets[0][7], h-(h/8));
        g2.drawString(Integer.toString(missileCount[2]), offsets[0][11], h-(h/8));
        
        g2.setColor(Color.WHITE);
        for(Missile m: activeMissiles){
            g2.rotate((Math.PI/2) + m.getHeading(), m.getCenterX(), m.getCenterY());
            g2.fill(m);
            g2.rotate(-(Math.PI/2) -m.getHeading(), m.getCenterX(), m.getCenterY());
            for(Rectangle r: m.spots){
                g2.fill(r);
            }
        }
        g2.drawString("Score: " + Integer.toString(score), w/2 - w/10, h/20);
        
        g2.drawImage(new ImageIcon("shooter.png").getImage(), offsets[0][3], h-(h/4), w/20, h/25, null);
        g2.drawImage(new ImageIcon("shooter.png").getImage(), offsets[0][7], h-(h/4), w/20, h/25, null);
        g2.drawImage(new ImageIcon("shooter.png").getImage(), offsets[0][11], h-(h/4), w/20, h/25, null);

        g2.setColor(Color.PINK);
        for(Explosion e: activeExplosions){
            g2.fill(e);
        }
    }
}
