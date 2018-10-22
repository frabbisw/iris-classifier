package pca;

import java.util.Arrays;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class IrisNetwork2 {
	double [][] input;
	double [][] desiredOutput;
	double [][] actualOutput;
	double [][] coMatrix; 
	int inputSize,outputSize,hiddenSize;
	int dataSize;
	public IrisNetwork2(double [][] input, double [][] desiredOutput, int hiddenSize)
	{
		this.dataSize=input.length;
		this.input=input;
		this.desiredOutput=desiredOutput;
		this.actualOutput=new double[desiredOutput.length][desiredOutput[0].length];
		inputSize=input[0].length;
		outputSize=desiredOutput[0].length;
		this.hiddenSize=hiddenSize;
		this.coMatrix=new double[outputSize][outputSize];
	}
	public void calculateResult()
	{
		DataSet trainingSet = new DataSet(inputSize,outputSize);
		for(int i=0; i<dataSize; i++)
			trainingSet.addRow(new DataSetRow(input[i],desiredOutput[i]));		
		
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputSize, hiddenSize, outputSize);
		myMlPerceptron.learn(trainingSet);
		
		for(int i=0; i<trainingSet.size(); i++)
		{
			DataSetRow dataRow = trainingSet.getRowAt(i);
			myMlPerceptron.setInput(dataRow.getInput());
			myMlPerceptron.calculate();
			actualOutput[i] = myMlPerceptron.getOutput().clone();
			for(int j=0; j<actualOutput[i].length; j++)	actualOutput[i][j]=Math.round(actualOutput[i][j]);
		}
	}
	public void showResult()
	{
		for(int i=0; i<desiredOutput.length; i++)
		{
			System.out.println("Desired Output: ");
			for(int j=0; j<desiredOutput[i].length; j++)
				System.out.print(Math.round(desiredOutput[i][j])+" ");
			System.out.println();
			System.out.println("Actual Output: ");
			for(int j=0; j<actualOutput[i].length; j++)
				System.out.print(Math.round(actualOutput[i][j])+" ");
			System.out.println("\n");
		}
	}
	public void showAccuracy()
	{
		int cnt=0;
		for(int i=0; i<dataSize; i++)
		{
			double [] y = actualOutput[i];
			double [] z = desiredOutput[i];
			coMatrix[(int)(y[0]*0+y[1]*1+y[2]*2)][(int)(z[0]*0+z[1]*1+z[2]*2)]++;
			
			String a = Arrays.toString(y);
			String b = Arrays.toString(z);
			cnt+=(a.equals(b)==true ? 1 : 0);
		}
		
		for(int i=0; i<coMatrix.length; i++)
		{
			for(int j=0; j<coMatrix[i].length; j++)
			{
				System.out.print(coMatrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("Accuracy: "+100*(double)cnt/dataSize+"%");
	}
}