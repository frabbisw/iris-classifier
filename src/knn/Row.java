package knn;

public class Row implements Comparable<Row>
{
	double [] inputValues;
	double outputValue;
	double distance;
	
	public Row(double [] inputValues, double outputValue)
	{
		this.inputValues=new double[inputValues.length];
		this.inputValues=inputValues.clone();
		this.outputValue=outputValue;
	}
	public double [] getInput()
	{
		return inputValues.clone();
	}
	public double getOutput()
	{
		return outputValue;
	}
	public int inputSize()
	{
		return inputValues.length;
	}
	public double getFromInput(int index)
	{
		return inputValues[index];
	}
	public int compareTo(Row o)
	{
		return this.distance<o.distance ? -1:1;
	}
	public void calculateDistance(Row row)
	{
		double b=0;
		for(int i=0; i<this.inputSize(); i++)
		{
			double t = (this.getFromInput(i)-row.getFromInput(i));
			t = t*t;
			b+=t;
		}
		distance=Math.sqrt(b);
	}
}