package com.abdelcore.openworld.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	/**KeyListener is an interface that listens to keyboard strokes on our keyboard
	 * based on a particular component
	 * Every key has 2 states : pressed/released */
	private boolean[] keys = new boolean[120]; //65536 : max value of characters , 120 is enough
	public boolean up, down, left, right; //true if it is pressed

	//Every cycle, it will check if a particular key is pressed or released 
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_Z]; //VK_UP :  key code for the "up arrow" key. and VK_W for the 'w' keyword 
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_Q];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		
		System.out.println(up);
	}


	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
}
