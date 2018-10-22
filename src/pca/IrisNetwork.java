package pca;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class IrisNetwork {
	double [][] input;
	double [][] output;
	int inputSize,outputSize,hiddenSize=7;
	public IrisNetwork(double [][] input, double [][] output, int hiddenSize)
	{
		this.input=input;
		this.output=output;
		inputSize=input[0].length;
		outputSize=output[0].length;
		this.hiddenSize=hiddenSize;
	}
	public void showResult()
	{
		DataSet trainingSet = new DataSet(inputSize,outputSize);
		for(int i=0; i<input.length; i++)
			trainingSet.addRow(new DataSetRow(input[i],output[i]));		
		
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputSize, hiddenSize, outputSize);
		myMlPerceptron.learn(trainingSet);
		
		System.out.println("Testing trained neural network");
		
		for(int i=0; i<trainingSet.size(); i++)
		{
			DataSetRow dataRow = trainingSet.getRowAt(i);
			myMlPerceptron.setInput(dataRow.getInput());
			myMlPerceptron.calculate();
			double[] networkOutput = myMlPerceptron.getOutput();
			System.out.println("Desired Output: ");
			for(double b : dataRow.getDesiredOutput())	System.out.print(Math.round(b)+" ");
			System.out.println();
			System.out.println("Actual Output: ");
			for(double b : networkOutput)	System.out.print(Math.round(b)+" ");
			System.out.println("\n");
		}
	}
}
