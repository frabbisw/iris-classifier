package pca;

import java.util.ArrayList;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.Collections;

public class PCA {
	double [][] input;
	double [][] newInput;
	int k,row,column;
	RealMatrix outMatrix;
	
	public PCA(double [][] input, int k)
	{
		this.input=input;
		this.k=k;
		this.row=input.length;
		this.column=input[0].length;
		
		editDataset();
	}
	private void editDataset() 
	{
		RealMatrix inputMatrix = MatrixUtils.createRealMatrix(input);
		RealMatrix cov = new Covariance(inputMatrix).getCovarianceMatrix();
		EigenDecomposition decomposition = new EigenDecomposition(cov);
		
		ArrayList<EigenRow>vectors=new ArrayList<>();
		for(int i=0; i<column; i++)
		{
			vectors.add(new EigenRow(decomposition.getRealEigenvalue(i), decomposition.getEigenvector(i)));
		}
		Collections.sort(vectors);
		double [][] temp = new double [column][k];
		
		for(int i=0; i<k; i++)
		{
			double [] arr = vectors.get(i).getVector();
			for(int j=0; j<column; j++)
			{
				temp[j][i]=arr[j];
			}
		}
		
		RealMatrix eigenMatrix = MatrixUtils.createRealMatrix(temp);
		outMatrix = inputMatrix.multiply(eigenMatrix);
	}
	public double [][] getNewInput()
	{
		return outMatrix.getData();
	}
}