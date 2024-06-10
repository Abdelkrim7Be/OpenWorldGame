package com.abdelcore.openworld;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import com.abdelcore.openworld.graphics.Screen;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	//The reason why we implemented runnable is because we wanted to pass 'this'
	//(the game) object to the Thread() , we couldn't if it wasn't implemented 
	public static int width = 300;
	public static int height = width / 16 * 9; // 16:9 aspect ratio
	public static int scale = 3;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	private Screen screen;
	
	//our final view and image of our application
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	//This will give us a rectangular array of pixels that we can write data to
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	//We are basically here converting the 'image' object into an array of integers that will signal which pixel
	//receives which color

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(this.width, this.height);

		frame = new JFrame();
	}

	//Why we did 'synchronized' here is to prevent overlaps and crushing our game
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			//System.out.println("Running...");
			update();//Called based on our specifications and calculations
			render();//Called as many seconds as we possibly can 
		}
	}

	public void update() {

	}

	//Handle rendering
	public void render() {
		//Empty Buffer
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		//Apply the Buffer to a graphic subject
		Graphics g = bs.getDrawGraphics(); //Link between the graphics and the buffer
		//---------------------------------------------------------------------------
		//g.setColor(new Color(0,0,0));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//---------------------------------------------------------------------------
		g.dispose();//At the end, we remove the graphics
		//Show the next buffer that has being calculated
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.frame.setResizable(false);
		game.frame.setTitle("OpenWorld");
		//Adding the game component
		game.frame.add(game);
		//Setting the window to have the me size as the component Game
		game.frame.pack();
		//When you hit the close button , that will terminate the application
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//the window is located in the center
		game.frame.setLocationRelativeTo(null);
		//Simply show our window
		game.frame.setVisible(true);

		game.start();
	}
}
