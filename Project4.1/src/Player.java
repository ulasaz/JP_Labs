import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 420;
        speed = 10;
        jumpHeight = 200;
        isJumping = false;
        ground = 420;
        jumpSpeed = 15;
        direction = "down";
    }

    public void getPlayerImage() {
        try{

            onPlace = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_right_2.png")));
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_up.png")));

        } catch (IOException e){
            System.out.println("Error loading image: /Player/boy.png");
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
            if (x < 0) {
                x = 0; // Ограничение слева
            }
        }
        if (keyH.rightPressed) {
            direction = "right";
            if (x + speed <= 750) { // Проверка: не превышает ли x границу 200
                x += speed; // Если не превышает, то продолжаем движение
            } else {
                x = 750; // Если x уже больше 200, фиксируем его на границе
            }
        }


        if (keyH.upPressed && !isJumping && y == ground) {
            direction = "up";
            isJumping = true; // Устанавливаем флаг прыжка
        }

        if (isJumping) {
            if (y > ground - jumpHeight) { // Поднимаемся, пока не достигнем максимальной высоты
                y -= jumpSpeed;
            } else {
                isJumping = false; // Завершаем прыжок
            }
        }

        if (!isJumping && y < ground) {
            y += jumpSpeed;
            if (y > ground) {
                y = ground;
            }
        }else if (!keyH.leftPressed && !keyH.rightPressed && !isJumping) {
            direction = "onPlace";
        }


        spriteCounter++;
        if (spriteCounter >= 10) { // Change every 10 updates
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "onPlace":
                image = onPlace;
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }if(spriteNum == 2){
                    image = left2;
            }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }if(spriteNum == 2){
                    image = right2;
                }
                break;
            case "up":
                image = up;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
