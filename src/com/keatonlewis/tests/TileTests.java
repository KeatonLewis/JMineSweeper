package com.keatonlewis.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.keatonlewis.game.Board;
import com.keatonlewis.game.Tile;

public class TileTests {

	private Tile[][] gameBoard;
	private Tile[][] boolGameBoard;
	private int numRows;
	private int numCols;
	private int boolRows;
	private int boolCols;

	@BeforeEach
	public void setup() {
		Board board = new Board(5, 5);
		this.gameBoard = board.getGameBoard();
		this.numRows = board.getNumRows();
		this.numCols = board.getNumCols();

		boolean[][] bools = { { true, true, false, false, false }, { true, false, false, false, false },
				{ false, false, true, true, true }, { false, false, true, false, true },
				{ false, false, true, true, true } };

		Board boolBoard = new Board(bools);
		this.boolGameBoard = boolBoard.getGameBoard();
		this.boolRows = boolBoard.getNumRows();
		this.boolCols = boolBoard.getNumCols();
	}

	@AfterEach
	public void tearDown() {
		this.gameBoard = null;
		this.boolGameBoard = null;
		this.numCols = -1;
		this.numRows = -1;
		this.boolCols = -1;
		this.boolRows = -1;
	}

	@Test
	void gameBoardShouldContainTileObjects() {
		assertTrue(gameBoard[0][0] instanceof Tile);
	}

	@Test
	void topAndBottomMiddleTileShouldHave5Nieghbors() {
		assertEquals(5, gameBoard[0][numCols / 2].getNieghborsList().size());
		assertEquals(5, gameBoard[numRows - 1][numCols / 2].getNieghborsList().size());
	}

	@Test
	void middleRowCellsShouldHave8Nieghbors() {
		assertEquals(8, gameBoard[numRows / 2][numCols / 2].getNieghborsList().size());
	}

	@Test
	void leftAndRightColTilesShouldHave5Nieghbors() {
		assertEquals(5, gameBoard[numRows / 2][0].getNieghborsList().size());
		assertEquals(5, gameBoard[numRows / 2][numCols - 1].getNieghborsList().size());
	}

	@Test
	void cornerTilesShouldHave3Nieghbors() {
		assertEquals(3, gameBoard[0][0].getNieghborsList().size());
		assertEquals(3, gameBoard[numRows - 1][0].getNieghborsList().size());
		assertEquals(3, gameBoard[0][numCols - 1].getNieghborsList().size());
		assertEquals(3, gameBoard[numRows - 1][numCols - 1].getNieghborsList().size());
	}

	@Test
	void surroundTileShouldShow8Mines() {
		assertEquals(8, boolGameBoard[3][3].getNumOfMines());
	}

	@Test
	void tileOnWallShouldShow3Mines() {
		assertEquals(3, boolGameBoard[1][3].getNumOfMines());
	}

	@Test
	void tileWithNoAdjacentMinesShouldShow0Mines() {
		assertEquals(0, boolGameBoard[0][4].getNumOfMines());
	}

}
