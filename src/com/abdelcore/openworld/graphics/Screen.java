package com.abdelcore.openworld.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;

	//Our tile size is  16x16 (decided)
	public int[] tiles = new int[64 * 64];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		//I created an integer for each pixel 
		pixels = new int[width * height]; //50400

		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xff00ff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height)
				break;
			//control that the pixel don't exceed the limits of the border
			for (int x = 0; x < width; x++) {
				//control that the pixel don't exceed the limits of the border
				if (x < 0 || x >= width)
					break;
				//64 is the map width
				// >> makes it optimazed and faster (specially in this nested loops)
				int tileIndex = (x >> 4) + (y >> 4) * 64;//find the tile that is needed to be rendered at a particular position
				//(x >> 4) (x is shifted twice to 4 <======> (y / 16))
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
		//throw new ArrayIndexOutOfBoundsException();
	}
	/** The reason why we did this (x / 16) and (y / 16) is that when we are at x = 2 , we have already a tile
	 * of 16 of width in this area, so we should jump up to the next area which is the x=2 * 16 to fill in the
	 * next tile ===> tiles here is 16 by 16*/
}
