import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SunCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SunCounter extends Actor
{
    public static final int x = 120;
    public static final int y = 50;
    public int sun = 0;
    /**
     * Act - do whatever the SunCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    MyWorld MyWorld;
    
    public void act()
    {
        
        // Add your action code here.
    }
    
    public void addedToWorld(World world) {
        MyWorld = (MyWorld)getWorld();
        updateText();
    }
    public void updateText() {
        String number = ""+sun;
        char[] text = number.toCharArray();
        getImage().clear();
        setImage("suncounter.png");
        for (int i = 0; i < text.length; i++) {
            if (text.length > 4) {
                sun = 99999;
                System.out.println("hacker");
            } else if (text.length > 3) {
                getImage().drawImage(new GreenfootImage("text"+text[i]+".png"), 30+i*12,45);
            }else if (text.length > 2) {
                getImage().drawImage(new GreenfootImage("text"+text[i]+".png"), 31+i*12,45);
            }else if (text.length > 1) {
                
                getImage().drawImage(new GreenfootImage("text"+text[i]+".png"), 38+i*12,45);
            }else if (text.length == 1) {
                
                getImage().drawImage(new GreenfootImage("text"+text[i]+".png"), 44,45);
            } else {
                
                
                //Nothing
            }
        }
    }
    public void addSun(int sun) {
        this.sun += sun;
        updateText();
    }
    
}