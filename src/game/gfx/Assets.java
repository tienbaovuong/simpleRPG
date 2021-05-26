package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 32, height = 32;
    public static BufferedImage dirt, grass, stone, tree, rock, fruit, water, bridge,gameover,
                                stonegrass,  tutorial, wood, scoreImage, door,backgroundMenu,logoMenu, item;
    public static BufferedImage[] player_down, player_up, player_left, player_right, bullet;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] zombie1_down, zombie1_up, zombie1_left, zombie1_right;
    public static BufferedImage[] monster_down, monster_up, monster_left, monster_right;
    public static BufferedImage btn_start, how_to_play_button, quit_button;
    public static BufferedImage tym= ImageLoader.loadImage("/textures/heart.png");
    public static BufferedImage key= ImageLoader.loadImage("/textures/key.png");
    public static void init() {
        SpriteSheet mapIcon = new SpriteSheet(ImageLoader.loadImage("/textures/mapchip.png"));
        SpriteSheet background1 = new SpriteSheet(ImageLoader.loadImage("/textures/background.png"));
        SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/10oop.png"));
        SpriteSheet doors = new SpriteSheet(ImageLoader.loadImage("/textures/door.png"));
		SpriteSheet hust = new SpriteSheet(ImageLoader.loadImage("/textures/husterplayer.png"));
        SpriteSheet floor = new SpriteSheet(ImageLoader.loadImage("/textures/floortileset.png"));
        SpriteSheet quit = new SpriteSheet(ImageLoader.loadImage("/textures/Newbutton.png"));
        SpriteSheet scoreImages = new SpriteSheet(ImageLoader.loadImage("/textures/score.png"));
        SpriteSheet bullets = new SpriteSheet(ImageLoader.loadImage("/textures/bomb.png"));
        SpriteSheet bullets2 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb2.png"));
        SpriteSheet bullets3 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb3.png"));
        SpriteSheet bullets4 = new SpriteSheet(ImageLoader.loadImage("/textures/bomb4.png"));
        SpriteSheet background = new SpriteSheet(ImageLoader.loadImage("/textures/backgroud1.jpg"));
        SpriteSheet logo = new SpriteSheet(ImageLoader.loadImage("/textures/logo.png"));
        SpriteSheet gameOver = new SpriteSheet(ImageLoader.loadImage("/textures/gameover.jpg"));

        gameover = gameOver.crop(0,0,800,608);

        logoMenu = logo.crop(0,0,371,76);
        backgroundMenu = background.crop(0,0,800,608);
        scoreImage = scoreImages.crop(0, 0, 108, height);

        wood = background1.crop(352, 928, width, height);
        tutorial = ImageLoader.loadImage("/textures/Tutorial.png");

        btn_start = quit.crop(0, 0, 270, 110);
      
        how_to_play_button = quit.crop(0,112,270,110);
        
        quit_button = quit.crop(0, 230, 270, 110);

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_down[0] = hust.crop(0, 0, width, height);
        player_down[1] = hust.crop(width , 0, width, height);
        player_up[0] = hust.crop(0, height*3, width, height);
        player_up[1] = hust.crop(width , height*3, width, height);
        player_left[0] = hust.crop(0, height, width, height);
        player_left[1] = hust.crop(width , height, width, height);
        player_right[0] = hust.crop(0, height*2, width, height);
        player_right[1] = hust.crop(width, height*2, width, height);
        
        
        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
	
        zombie_down[0] 	= hust.crop(width*12, 0, width, height);
		zombie_down[1] 	= hust.crop(width*13, 0, width, height);
        zombie_up[0]   	= hust.crop(width * 12, height * 3, width, height);
		zombie_up[1]    = hust.crop(width * 13, height * 3, width, height);
		zombie_right[0] = hust.crop(width * 12, height * 2, width, height);
		zombie_right[1] = hust.crop(width * 13, height * 2, width, height);
		zombie_left[0] 	= hust.crop(width * 12, height * 1, width, height);
		zombie_left[1] 	= hust.crop(width * 13, height * 1, width, height);


		zombie1_down = new BufferedImage[2];
		zombie1_up = new BufferedImage[2];
		zombie1_left = new BufferedImage[2];
		zombie1_right = new BufferedImage[2];

		zombie1_down[0] 	= hust.crop(width*9, 4*height, width, height);
		zombie1_down[1] 	= hust.crop(width*10, 4*height, width, height);
		zombie1_up[0]   	= hust.crop(width * 9, height * 7, width, height);
		zombie1_up[1]    = hust.crop(width * 10, height * 7, width, height);
		zombie1_right[0] = hust.crop(width * 9, height * 6, width, height);
		zombie1_right[1] = hust.crop(width * 10, height * 6, width, height);
		zombie1_left[0] 	= hust.crop(width * 9, height * 5, width, height);
		zombie1_left[1] 	= hust.crop(width * 10, height * 5, width, height);
		

                
		monster_down = new BufferedImage[3];
		monster_up = new BufferedImage[3];
		monster_left = new BufferedImage[3];
		monster_right = new BufferedImage[3];
		
		monster_down[0] = hust.crop(0, height * 4, width , height );
		monster_down[1] = hust.crop(width * 1, height * 4, width , height );
		monster_down[2] = hust.crop(width * 2, height * 4, width , height );
		monster_up[0] = hust.crop(width * 0, height * 5, width , height );
		monster_up[1] = hust.crop(width * 1, height * 5, width , height );
		monster_up[2] = hust.crop(0, height * 2, width *5, height );
		monster_right[0] = hust.crop(width * 0, height * 6, width , height );
		monster_right[1] = hust.crop(width * 1, height * 6, width , height );
		monster_right[2] = hust.crop(width * 2, height * 6, width , height );
		monster_left[0] = hust.crop(width * 0, height * 7, width , height );
		monster_left[1] = hust.crop(width * 1, height * 7, width , height );
		monster_left[2] = hust.crop(width * 2, height * 7, width , height );
		
        // solid
		door = doors.crop(0,0,width,height);
		tree = mapIcon.crop(width * 7, height * 0, width, height);
        dirt = mapIcon.crop(width*2,height, width, height);
        grass = mapIcon.crop(3*32, height * 0, width, height);
        stone = mapIcon.crop(32, 0, width, height);
        rock = background1.crop(320, 928, width, height);
        fruit = mapIcon.crop(0, height * 1 , width , height);
        water = floor.crop(width * 3, height * 5, width, height);
        stonegrass = mapIcon.crop(width * 4, 0, width, height);
        bridge =   mapIcon.crop(width * 6, 32, width, height);

        // bullet
        bullet = new BufferedImage[4];
        bullet[0] = bullets.crop(0, 0, width, height);
        bullet[1]= bullets2.crop(0, 0, width, height);
        bullet[2]= bullets3.crop(0, 0, width, height);
        bullet[3]= bullets4.crop(0, 0, width, height);

        //item
        item = items.crop(0, 0, width-1, height);





    }
}
