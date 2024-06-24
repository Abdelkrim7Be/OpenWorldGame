package com.abdelcore.openworld.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	

	//Our tile size is  16x16 (decided)
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		//I created an integer for each pixel 
		pixels = new int[width * height]; //50400

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xff00ff);
			tiles[0] = 0;
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			//if (yy < 0 || yy >= height)
			//break;
			//control that the pixel don't exceed the limits of the border
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				//control that the pixel don't exceed the limits of the border
				//if (xx < 0 || xx >= width)
				//break;
				//MAP_SIZE is the map width
				// >> makes it optimized and faster (specially in this nested loops)
				//when x >> 4 becomes bigger than MAP_SIZE_MASK, it will return to 0 again
				int tileIndex = ((xx >> 16) & MAP_SIZE_MASK)+ ((yy >> 16) & MAP_SIZE_MASK) * MAP_SIZE_MASK;//find the tile that is needed to be rendered at a particular position
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
