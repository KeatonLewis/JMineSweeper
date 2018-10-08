package com.keatonlewis.game;

public class Board {

	private final int numRows;
	private final int numCols;
	private Tile[][] gameBoard;
	private final static double MINE_DISTRIBUTION = 0.3;

	public Tile[][] getGameBoard() {
		return gameBoard;
	}

	public Board(int rows, int cols) {
		numRows = rows;
		numCols = cols;
		gameBoard = new Tile[numRows][numCols];
		generateGameBoard();
		addAllTileNeighbors();
	}

	public Board(boolean[][] boolBoard) {
		numRows = boolBoard.length;
		numCols = boolBoard[0].length;
		gameBoard = new Tile[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				gameBoard[i][j] = new Tile(i, j, boolBoard[i][j]);
			}
		}
		addAllTileNeighbors();
	}

	private void addAllTileNeighbors() {
		for (Tile[] row : gameBoard) {
			for (Tile tile : row) {
				addSingleTileNieghbors(tile);
				tile.setNumOfMines();
			}
		}
	}

	private void addSingleTileNieghbors(Tile tile) {
		for (int rowOff = -1; rowOff <= 1; rowOff++) {
			for (int colOff = -1; colOff <= 1; colOff++) {
				if (!(rowOff == 0 && colOff == 0)) {
					int tx = tile.getX() + rowOff;
					int ty = tile.getY() + colOff;
					if (tx >= 0 && tx < numRows && ty >= 0 && ty < numCols) {
						tile.addNieghbor(gameBoard[tx][ty]);
					}
				}
			}
		}
	}

	private void generateGameBoard() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				gameBoard[i][j] = new Tile(i, j, Math.random() < MINE_DISTRIBUTION);
			}
		}
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

}
