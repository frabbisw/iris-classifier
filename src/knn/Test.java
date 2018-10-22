package knn;

import java.util.ArrayList;
import java.util.Collections;

public class Test {

	public static void main(String[] args) 
	{
		int testdata=130,k=10;
		ArrayList<Row>rows=new ArrayList<>();
		double [][] input = FileProcessing.getInput();
		double [] output = FileProcessing.getOutput();
		
		for(int i=0; i<input.length; i++)
			rows.add(new Row(input[i], output[i]));
		
		Collections.shuffle(rows);
		Collections.shuffle(rows);
		Collections.shuffle(rows);
		
		Classifier classifier = new Classifier(k);
		for(int i=0; i<testdata; i++)	classifier.addRow(rows.get(i));
		
		double a=0;
		for(int i=testdata; i<input.length; i++)		
		{
			a+=Math.abs(rows.get(i).getOutput()-classifier.getOutput(rows.get(i)));
		}
		System.out.println("Accuracy: "+(100-100*a/(input.length-testdata))+"%");
	}
}
