package com.keatonlewis.game;

import java.util.ArrayList;

public class Tile {

	private final int x;
	private final int y;
	private final ArrayList<Tile> nieghborsList;
	private final boolean mine;
	private int numOfMines;

	public Tile(int x, int y, boolean mine) {
		this.x = x;
		this.y = y;
		this.nieghborsList = new ArrayList<Tile>();
		this.mine = mine;
		numOfMines = -1;
	}

	public boolean isMine() {
		return mine;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Tile> getNieghborsList() {
		return nieghborsList;
	}

	public int getNumOfMines() {
		return numOfMines;
	}

	public void setNumOfMines() {
		if (nieghborsList.isEmpty()) {
			throw new ExceptionInInitializerError("NieghborsList must be initilazed first");
		}

		int count = 0;
		for (Tile nieghbor : nieghborsList) {
			if (nieghbor.isMine()) {
				count++;
			}
		}

		this.numOfMines = count;

	}

	public void addNieghbor(Tile tile) {
		nieghborsList.add(tile);
	}

}
