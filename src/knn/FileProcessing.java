package knn;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FileProcessing
{
	public static String [][] toArray(String fileName)
	{
		String [][] args;
		args=new String[150][5];
		
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			BufferedReader rf = new BufferedReader(new InputStreamReader(fstream));
			
			int i;
			String strline;	
			for(i=0;(strline=rf.readLine())!=null;i++)
			{
				args[i]=strline.split(",");
			}
			
			return args;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return args;
	}
	public static double [][] getInput()
	{
		String [][] arr = toArray("iris.data");
		double [][] input = new double[150][4];
		double [] max = new double[4];
		double [] min = new double[4];
		
		for(int i=0; i<150; i++)
		{
			for(int j=0; j<4; j++)
			{
				input[i][j]=Double.parseDouble(arr[i][j]);
				max[j]=Math.max(max[j], input[i][j]);
				min[j]=Math.min(min[j], input[i][j]);
			}
		}
		for(int i=0; i<150; i++)
		{
			for(int j=0; j<4; j++)
			{
				input[i][j]=(input[i][j]-min[j])/(max[j]-min[j]);
			}
		}
		return input;
	}
	public static double [] getOutput()
	{
		String [][] arr = toArray("iris.data");
		double [] output = new double[150];
		for(int i=0; i<150; i++)
		{
			if(arr[i][4].equals("Iris-setosa"))	
			{
				output[i]=1;
			}
			else if(arr[i][4].equals("Iris-versicolor"))
			{
				output[i]=2;
			}
			else if(arr[i][4].equals("Iris-virginica"))
			{
				output[i]=3;
			}
		}
		return output;
	}
}