package com.abdelcore.openworld;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	
	//The reason why we implemented runnable is because we wanted to pass 'this'
	//(the game) object to the Thread() , we couldn't if it wasn't implemented 
	public static int width = 300;
	public static int height = width / 16 *9; // 16:9 aspect ratio
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
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
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(running) {
			System.out.println("Running...");
		}
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
 