package minecraft_question;

import java.util.ArrayList;
import java.util.List;

public class MinecraftGame {

	/**
	 * Match an game canvas with an object pattern, the canvas should have one and only one instance of the object in order to return True.
	 *
	 * @param      objectPattern    matrix of the object pattern, empty cell is 0, non-empty cell is non-zero integer
	 * @param      canvas    matrix of the canvas, cell not occupied is 0, non-empty cell is non-zero integer
	 * @exception  NotMatrixException     if the input 2D array is not a matrix
	 * 
	 */
	public Boolean matchObject(int[][] objectPattern, int[][] canvas)
			throws NotMatrixException {
		
		//convert the canvas into 1d array, odd element is the type of the non-empty cell, even element is the distance between two consecutive non-empty cells
		List<Integer> canvasArray = analyzeCanvas(canvas);

		int canvasWidth = canvas[0].length;

		//convert the object pattern into 1d array, odd element is the type of the non-empty cell, even element is the distance between two consecutive non-empty cells
		List<Integer> patternArray = analyzePattern(objectPattern,	canvasWidth);

		int canvasLength=canvasArray.size();
		int patternLength=patternArray.size();

		//matching the object is simply the matching between two 1d arrays
		//overall complexity O(n+m), n is the number of cells in the canvas, m is the number of cells in the object pattern
		//need two 1d array, their size depends on the number of non-empty cells in the canvas/object pattern
		if(canvasLength==patternLength){
			for (int i = 1; i < canvasLength; i++) {
				if(canvasArray.get(i)!=patternArray.get(i)){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}

	}

	private List<Integer> analyzeCanvas(int[][] canvas) throws NotMatrixException {
		int canvasWidth = canvas[0].length;
		int canvasHeight = canvas.length;

		List<Integer> canvasArray = new ArrayList<Integer>();
		int dist = 0;

		for (int i = 0; i < canvasHeight; i++) {

			if (canvasWidth == canvas[i].length) {
				for (int j = 0; j < canvasWidth; j++) {
					if (canvas[i][j] != 0) {
						canvasArray.add(dist);
						canvasArray.add(canvas[i][j]);
						dist = 0;
					}
					dist++;
				}
			} else {
				throw new NotMatrixException("The canvas input is not matrix");
			}
		}
		return canvasArray;
	}

	private List<Integer> analyzePattern(int[][] objectPattern, int canvasWidth)
			throws NotMatrixException {

		int objectWidth = objectPattern[0].length;
		int objectHeight = objectPattern.length;
		int widthDiff=canvasWidth-objectWidth;

		List<Integer> convertedPattern = new ArrayList<Integer>();
		int dist = 0;//distance between two consecutive occupied cells in the canvas instead of in the object pattern..

		for (int i = 0; i < objectHeight; i++) {
			if (objectWidth == objectPattern[i].length) {
				dist+=widthDiff;
				for (int j = 0; j < objectWidth; j++) {

					if (objectPattern[i][j] != 0) {
						convertedPattern.add(dist);
						convertedPattern.add(objectPattern[i][j]);
						dist = 0;
					}
					dist++;
				}
			} else {
				throw new NotMatrixException("The object pattern input is not matrix");
			}
		}

		return convertedPattern;

	}
	
	public static class NotMatrixException extends Exception{
	    public NotMatrixException(String message) {
	        super(message);
	    }
	}

}
