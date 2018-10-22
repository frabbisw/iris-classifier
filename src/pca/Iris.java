package pca;
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
		
		input = FileProcessing.normalize(input);
		
		PCA pca = new PCA(input, 2);
		input = pca.getNewInput();
		
		//IrisNetwork network = new IrisNetwork(input, output, 7);
		//network.showResult();
		
		IrisNetwork2 network = new IrisNetwork2(input, output, 7);
		network.calculateResult();
		//network.showResult();
		network.showAccuracy();
	}
}