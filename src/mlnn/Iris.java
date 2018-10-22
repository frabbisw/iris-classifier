package mlnn;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

public class Iris 
{
	public static void main(String [] args)
	{
		double [][] input = FileProcessing.getInput();
		double [][] output = FileProcessing.getOutput();
		
		DataSet trainingSet = new DataSet(4,3);
		for(int i=0; i<input.length; i++)
			trainingSet.addRow(new DataSetRow(input[i],output[i]));		
		
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 4, 7, 3);
		
		myMlPerceptron.learn(trainingSet);
		
		System.out.println("Testing trained neural network");
		
		for(DataSetRow dataRow : trainingSet.getRows()) {
			myMlPerceptron.setInput(dataRow.getInput());
			myMlPerceptron.calculate();
			double[ ] networkOutput = myMlPerceptron.getOutput();
			System.out.println("Desired Output: ");
			for(double b : dataRow.getDesiredOutput())	System.out.print(Math.round(b)+" ");
			System.out.println();
			System.out.println("Actual Output: ");
			for(double b : networkOutput)	System.out.print(Math.round(b)+" ");
			System.out.println("\n");
		}
		
	}
}