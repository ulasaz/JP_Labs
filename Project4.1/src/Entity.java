import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int jumpHeight;
    public int jumpSpeed;
    public boolean isJumping;
    public int ground;

    public BufferedImage onPlace,left1,left2,right1,right2, up;
    public String direction;

    public  int spriteCounter = 0;
    public int spriteNum = 1;


}
