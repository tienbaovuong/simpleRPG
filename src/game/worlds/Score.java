package game.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import game.entities.creatures.Player;
import game.gfx.ImageLoader;
import game.gfx.SpriteSheet;
public class Score {
	private static long score = Player.score;
	private static int width = 32, height = 32;
	private static BufferedImage[] numberImager = new BufferedImage[50];
	private static BufferedImage board = ImageLoader.loadImage("/textures/yellownumber.png");
	private static BufferedImage scoreImage;
	private static long highestScore;
	private static BufferedImage scoreImagers = ImageLoader.loadImage("/textures/score.png");
	//private static BufferedImage player = ImageLoader.loadImage("/textures/husterplayer.png");
	private static BufferedImage x= ImageLoader.loadImage("/textures/heart.png");
	private static BufferedImage bomb = ImageLoader.loadImage("/textures/bomb.png");
	private static int health;
	private static int bullet_number;
	public Score(long score, int health, int bullet_number) {
		getHighestScoreFromFile();
		setScore(score);
		if (score > highestScore) {
			highestScore = score;
			updateHighestScore(highestScore);
		}
		
		for (int i = 0; i <= 9; ++i) {
			setNumberImage(i);
		}
		setHealth(health);
		setBullet_number(bullet_number);
	}

	private static void setNumberImage(int i) {
		//numberImager[i] = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
		numberImager[i] = board.getSubimage(i*width, 0, width, height);
		
        }
	
	public static void render(Graphics g) {
        int nghin = (int)(score/1000) %10;
		int tram = (int)(score/100) %10;
		int chuc = (int)(score/10) %10 ;
		int donVi = (int) score%10;
		g.drawImage(scoreImagers, 0, 0,108, height, null);
        g.drawImage(numberImager[nghin], 108, 3,width,height, null);
		g.drawImage(numberImager[tram], 108 + 32, 3,width,height, null);
		g.drawImage(numberImager[chuc], 108 + 64, 3, width, height, null);
		g.drawImage(numberImager[donVi], 108 + 96, 3, width, height, null);
		//g.drawImage(player.getSubimage(0, 64, 32, 32),600,0, width,height,null);
		g.drawImage(x,580,0,width,height,null);
		g.drawImage(numberImager[health],612,0, width,height,null);

		g.drawImage(bomb, 664, 0, width, height, null);
		g.drawImage(numberImager[(int)(bullet_number/10)], 696, 0, width, height, null );
		g.drawImage(numberImager[(int)(bullet_number%10)], 696+32, 0, width, height, null );
	}
	
	public static void setScore(long score) {
		Score.score = score;
	}
	public static void setHealth(int health) {
		Score.health = health;
	}

	public static void setBullet_number(int bullet_number) {
		Score.bullet_number = bullet_number;
	}

	public static long getHighestScoreFromFile() {
		try {
			FileReader in = new FileReader("res/world/highestScore.txt");
			BufferedReader br = new BufferedReader(in);
			
			Score.highestScore = Long.parseLong(br.readLine());
			br.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return Score.highestScore;
	}
	
	private static void updateHighestScore(long highestScore) {
		try {
			FileWriter out = new FileWriter("res/world/highestScore.txt");
			out.write(Long.toString(highestScore));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static long getHighestScore() {
		return Score.highestScore;
	}
}