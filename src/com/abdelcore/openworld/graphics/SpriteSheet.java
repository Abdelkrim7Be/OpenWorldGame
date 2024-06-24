package com.abdelcore.openworld.graphics;

public class SpriteSheet {
	private String path;
	private final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;//The size of the spritesheet
		pixels = new int[SIZE * SIZE];
	}
	
	private void
}
