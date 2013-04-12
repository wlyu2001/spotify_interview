package minecraft_question.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import minecraft_question.MinecraftGame;
import minecraft_question.MinecraftGame.NotMatrixException;

import org.junit.Test;

public class MinecraftGameTestCase {

	@Test
	public void testMatchObject() {
		int[][] canvas=new int[][]{
				{0,0,0},
				{0,1,0},
				{0,2,3}
		};
		int[][] objectPattern=new int[][]{
				{1,0},
				{2,3}
		};
		
		MinecraftGame game=new MinecraftGame();
			try {
				Assert.assertTrue(game.matchObject(objectPattern,canvas));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testUnMatchObject1() {
		int[][] canvas=new int[][]{
				{0,0,0},
				{0,0,1},
				{0,2,3}
		};
		int[][] objectPattern=new int[][]{
				{1,0},
				{2,3}
		};
		
		MinecraftGame game=new MinecraftGame();
			try {
				Assert.assertFalse(game.matchObject(objectPattern,canvas));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testUnMatchObject2() {
		int[][] canvas=new int[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,2,3,0},
				{0,0,0,0}
		};
		int[][] objectPattern=new int[][]{
				{1,0},
				{2,3}
		};
		
		MinecraftGame game=new MinecraftGame();
			try {
				Assert.assertFalse(game.matchObject(objectPattern,canvas));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test(expected=NotMatrixException.class)
	public void testWrongObject() throws NotMatrixException {
		int[][] canvas=new int[][]{
				{0,0,0},
				{0,1,0},
				{0,2,3}
		};
		int[][] objectPattern=new int[][]{
				{0},
				{2,3}
		};
		
		MinecraftGame game=new MinecraftGame();
		game.matchObject(objectPattern,canvas);

	}
	
	@Test(expected=NotMatrixException.class)
	public void testWrongCanvas() throws NotMatrixException {
		int[][] canvas=new int[][]{
				{0,0,0},
				{0,1},
				{0,2,3}
		};
		int[][] objectPattern=new int[][]{
				{1,0},
				{2,3}
		};
		
		MinecraftGame game=new MinecraftGame();
		game.matchObject(objectPattern,canvas);
	}

}
