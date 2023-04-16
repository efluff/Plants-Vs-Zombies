import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class SeedBank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SeedBank extends Actor
{
    /**
     * Act - do whatever the SeedBank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   
    public MyWorld MyWorld;
    public SunCounter suncounter = new SunCounter();
    public SeedPacket[] bank;
    public SeedPacket selectedPacket = null;
    public TransparentObject image = null;
    public TransparentObject transparent = null;
    
    public static final int x1 = 200;
    public static final int x2 = 775;
    public static final int xSpacing = 64;
    public static final int y1 = 67;
    public static final int y2 = 463;
    public static final int ySpacing = 80;
    
    public SeedBank(SeedPacket[] bank) {
        
        this.bank = bank;
    }
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if (image != null) {
                if ((mouse.getX() < x1 || mouse.getX() > x2 || mouse.getY() < y1 || mouse.getY() > y2) 
                    || (MyWorld.board.Board[(int)((mouse.getY()-y1)/ySpacing)][(int)((mouse.getX()-x1)/xSpacing)] != null)) {
                    image.setTransparent(false);
                    image.setLocation(mouse.getX(), mouse.getY());
                } else {
                    int x = (int)((mouse.getX()-x1)/xSpacing);
                    int y = (int)((mouse.getY()-y1)/ySpacing);
                    image.setTransparent(true);
                    image.setLocation(x*Board.xSpacing+Board.xOffset, y*Board.ySpacing+Board.yOffset);
                }
            } else {
                
            }
            
            if (Greenfoot.mouseClicked(null)) {
                MyWorld.moveHitbox();
                
                
                if (image != null) {
                    if (mouse.getX() < x1 || mouse.getX() > x2 || mouse.getY() < y1 || mouse.getY() > y2) {
                        MyWorld.removeObject(image);
                        image = null;
                        boolean selected = false;
                        for (Object i : MyWorld.hitbox.getTouching()) {
                            if (i.equals(selectedPacket)) {
                                selected = true;
                            }
                        
                        }
                        if (!selected) {
                            selectedPacket.setSelected(false);
                            selectedPacket = null;
                        }
                        
                    } else {
                        int x = (int)((mouse.getX()-x1)/xSpacing);
                        int y = (int)((mouse.getY()-y1)/ySpacing);
                        MyWorld.board.placePlant(x, y, selectedPacket.getPlant());
                    }
                }
                
                
                
                
                
                
                for (Object i : MyWorld.hitbox.getTouching()) {
                    if (i instanceof SeedPacket) {
                        SeedPacket clicked = (SeedPacket)i;
                        
                        if (selectedPacket != clicked) {
                            if (clicked.recharged) {
                                if (selectedPacket != null) {
                                    selectedPacket.setSelected(false);
                                    selectedPacket = null;
                                }
                                selectedPacket = clicked;
                                clicked.setSelected(true);
                                AudioPlayer.play(80, "seedlift.wav");
                                image = clicked.addImage();
                            } else {
                                
                            }
                        } else {
                            if (clicked.recharged) {
                                selectedPacket = null;
                                clicked.setSelected(false);
                                AudioPlayer.play(80, "seedlift.wav");
                            }
                        }
                
                    }
            
                }
                
                
                
            }
            
            
        }
    }
    @Override
    public void addedToWorld(World world) {
        MyWorld = (MyWorld)getWorld();
        MyWorld.addObject(suncounter, 120, 50);
        for (int i = 0; i < bank.length; i++) {
            MyWorld.addObject(bank[i], 120, 120+i*50);
        }
    }
    
}
