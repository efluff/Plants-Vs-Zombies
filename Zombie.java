import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends animatedObject
{
    public boolean fallen = false;
    public boolean eating = false;
    public int hp;
    public int maxHp;
    public double walkSpeed;
    public MyWorld MyWorld;
    public boolean spawnHead = false;
    public int eatSpeed;
    public boolean isAlive = true;
    public GreenfootImage[] headless;
    public GreenfootImage[] fall;
    public boolean resetAnim = false;
    public boolean finalDeath = false;
    public boolean fixAnim = false;
    /**
     * Act - do whatever the Zombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Zombie() {
    
        headless = importSprites("zombieheadless", 7);
        fall = importSprites("zombiefall",6);
        
    }
    public void act()
    {
        if (getImage().getTransparency() <= 0) {
            getWorld().removeObject(this);
            return;
        }
        
        if (isLiving()) {
            update();
            
        } else {
            deathAnim();            
            
        }
        
    }
    
    public void update() {
        
    }
    
    public void deathAnim() {
        if (!resetAnim) {
                frame = 0;    
                resetAnim = true;
        }
        if (frame <=7) {
            if (finalDeath) {
                if (!fixAnim) {
                    fixAnim = true;
                    AudioPlayer.play(80, "zombie_falling_1.wav", "zombie_falling_2.wav");
                    setLocation(getX()-10, getY()+10);
                }
                animate(fall, 200, false);
                
            } else {
                if (!spawnHead) {
                    spawnHead = true;
                    AudioPlayer.play(80, "shoop.wav");
                    getWorld().addObject(new Head(), getX(), getY()-10);
                }
                animate(headless, 350, false);
                
                move(-walkSpeed);
            }
        } else if (!finalDeath) {
            resetAnim = false;
            finalDeath = true;
            
            for (ArrayList<Zombie> i : MyWorld.level.zombieRow) {
                if (i.contains(this)) {
                    i.remove(this);                    
                    break;
                }
            }
            
        } else {
            if (getImage().getTransparency()-3 <= 0) {
                getImage().setTransparency(0);
            } else {
                getImage().setTransparency(getImage().getTransparency()-3);
            }
        }

    }
    @Override
    protected void addedToWorld(World world) {
        MyWorld = (MyWorld)getWorld();
        
    }
    public boolean isLiving() {
        if (hp <=0) {
            isAlive = false;
        } else {
            isAlive = true;
        }
        return isAlive;
    }
    public void hit(int dmg) {
        
    }
    public boolean isEating() {
        var row = MyWorld.board.Board[getYPos()];
        for (int i = 0; i < MyWorld.board.Board[0].length; i++) {
            if (row[i] != null) {
                
                if (Math.abs(row[i].getX() - getX()+10) < 25) {
                    eating = true;
                    
                    return eating;
                }
            }
        }
        eating = false;
        return eating;
        
        
        
    }
    public int getYPos() {
        return ((getY()-MyWorld.level.yOffset)/MyWorld.level.ySpacing);
    }
    public int getXPos() {
        return getX();
    }
    
}
