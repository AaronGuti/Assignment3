package assignment3;
/**
 * A class containing Matrix constructors as well as methods that allow for:
 * -Addition
 * -Multiplication
 * -Transposition by Matrices and doubles
 * @author Aaron Gutierrez
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
	//Properties
	private double [][] matrix; 
	private int row; 
	private int col; 
	
	/**
	 * Creates a matrix out of a two dimensional array.
	 * The row and col instance variables are assigned to the sizes to the respective rows and columns.
	 * @param aMatrix  a two dimensional array
	 */
	public Matrix(double[][] aMatrix) { 
	row = aMatrix.length;
	col = aMatrix[0].length; 
	matrix = aMatrix; 
	}
	
	/**
	 * Creates a file of the string entered as a parameter and reads it in to create a matrix out of the content of the file.
	 * @param filename  the name of the file to be read-in 
	 */
	public Matrix(String filename) { 
	File file = new File(filename); 
	this.matrix = readFile(file); 
	}
	
	/**
	 * The toString function outputs the matrix that evokes this method as a string in the format of a two-dimensional array.
	 */
	public String toString() {
	StringBuffer result = new StringBuffer(); 
	for(int i = 0; i < matrix.length; i++) { 
		for(int j = 0; j < matrix[i].length; j++) { 
			result.append(matrix[i][j] + " "); 
			if (j == matrix[i].length - 1) {
					result.append("\n");
				}
			}
		}
	return row+ "x" + col + " matrix \n" + result; 
	}
	
	/**
	 * Compares the values in two matrices to compare if they are equivalent
	 * @param aMatrix  the matrix to be compared to the matrix that evoked the method
	 * @return comparison  which would be false if the comparison is not true and true if it is.
	 */
	public boolean equals(Matrix aMatrix) {
	    boolean comparison = false;
	     for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[i].length; j++) { 
	        		for (int k = 0; k < aMatrix.matrix.length; k++) {
	        			for(int l = 0; l < aMatrix.matrix[k].length; l++) {
	        				comparison = (matrix[i][j] == aMatrix.matrix[k][l]);
	        			}
	        		}
	         }
	     }
	    return comparison;
	}

	/**
	 * Traverses a matrix and adds the values of each cell to the "original" matrix.
	 * @param aMatrix  Matrix to be added to the matrix that evoked the method
	 * @return A new matrix with each cell being the result of the addition.
	 */
	public Matrix add(Matrix aMatrix) {   
	double[][] result = new double[matrix.length][matrix[0].length]; 
	for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) { 
	        		result[i][j] = matrix[i][j] + aMatrix.matrix[i][j]; 
	        		}
	    }
	return new Matrix(result);
	} 

	/**
	 * Multiplies the values in the parametric matrix with the "original" and places the values in the matrix holding the results. 
	 * @param aMatrix  the Matrix to be multiplied with the "original" matrix
	 * @return A matrix whose values is the results of the multiplication.
	 */
	public Matrix mult(Matrix aMatrix) {
		double[][] result = new double[matrix.length][aMatrix.matrix[0].length]; 
		for (int i = 0; i < result.length; i++) 
	        for (int j = 0; j < result[0].length; j++)  
	        		for (int k = 0; k < matrix[0].length; k++)  
	        			result[i][j] += (matrix[i][k] * aMatrix.matrix[k][j]); 
					return new Matrix(result);
	}
	
	/**
	 * Multiplies the number provided with all elements in a matrix
	 * @param aNumber the number to be used 
	 * @return A matrix with the values being the product of the multiplication of the original elements and the number.
	 */
	public Matrix mult(double aNumber) {
	double[][] result = new double[matrix.length][matrix[0].length]; 
	for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) { 
	        result[i][j] = (matrix[i][j] * aNumber); 
	        }
	    }
	return new Matrix(result);
		}
	
	/**
	 * The transpose method swaps the rows into column and columns into rows.
	 * @return A matrix whose rows and columns have swapped places. 
	 */
	public Matrix transpose() {
	      double[][] result = new double[this.matrix[0].length][this.matrix.length];
	      for (int i = 0; i < this.matrix.length; i++)
	          for (int j = 0; j < this.matrix[0].length; j++)
	              result[j][i] = this.matrix[i][j];
	      return new Matrix(result);
	    }

	/**
	 * The readFile method reads each line as a row in the matrix and each element is distinguished by the space between them.
	 * @param infile  the file to be read into and parsed into a Matrix
	 * @return A matrix where each element is read from the file that was taken in
	 */
	public double[][] readFile(File infile) {
		Scanner s = null;
		try {
			s = new Scanner(infile);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(s.hasNextLine()) {
			row++;
			s.nextLine();
		}
		s.close();
	
	double[][] newMatrix = new double[row][];
	try {
		s = new Scanner(infile);
		} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	for(int i = 0; i < row; ++i) {
	   String line = s.nextLine();
	   String[] col = line.split(" ");
	   newMatrix[i] = new double[col.length];
	   	for(int j = 0; j < col.length; ++j)
	   		newMatrix[i][j] = Double.parseDouble(col[j]);
	}
	s.close();
	return newMatrix; 
	}
}

