package application;

import javafx.css.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Base {
    private double angle,x, y;
    private final double SIZE = 30;
    private double speed=3;
    private Color color;
    private int  dx=1,dy=1;
    private String className;
    private final Image PaperImage = new Image(ClassLoader.getSystemResource("paper.png").toString());
    private final Image RockImage = new Image(ClassLoader.getSystemResource("rock.png").toString());
    private final Image ScissorImage = new Image(ClassLoader.getSystemResource("scissor.png").toString());

    public Base(String className){
        Random rand = new Random();
        this.x = rand.nextInt(950);
        this.y = rand.nextInt(700);
        this.angle = rand.nextInt(360);
        this.className=className;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getClassName(){
        return this.className;
    }
    public void setClassName(String className){
        this.className=className;
    }


    public void render(GraphicsContext gc){
        String name = this.className;
        Image img = null;
        if(className.equals("Rock")){img = RockImage;}
        if(className.equals("Paper")){img = PaperImage;}
        if(className.equals("Scissor")){img = ScissorImage;}
        gc.drawImage(img,getX(),getY(),SIZE, SIZE);
    }

    public boolean isCollide(Base ob){
        if(this.getClassName() == ob.getClassName()){
            return false;
        }

        double dx = this.getX()-ob.getX();
        double dy = this.getY()-ob.getY();
        boolean result = Math.pow(Math.pow(dx,2) + Math.pow(dy,2),0.5) <= SIZE;
        if(result){
            this.dx*=-1;
            this.dy*=-1;
            ob.dx*=-1;
            ob.dy*=-1;
        }
        return result;
    }

    public boolean isWin(Base ob){
        if(this.getClassName().equals("Rock")){
            if(ob.getClassName().equals("Scissor")){
                return true;
            }
            else{
                return false;
            }
        }
        else if(this.getClassName().equals("Scissor")){
            if(ob.getClassName().equals("Paper")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(ob.getClassName().equals("Rock")){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public void move(){
        this.x += dx*Math.cos(angle) * speed * MainApplication.Xspeed;
        if(this.getX() > 975 ){
            dx*=-1;
            this.x=975;
        }

        if(this.getX()<0){
            dx*=-1;
            this.x=0;
        }

        this.y += dy*Math.sin(angle) * speed * MainApplication.Xspeed;
        if(this.getY() > 725){
            dy*=-1;
            this.y=725;
        }
        if(this.getY()<0){
            dy*=-1;
            this.y=0;
        }

    }
}
