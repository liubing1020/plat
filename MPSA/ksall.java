/*
  Input: frequency data stored in Nkx.txt files
  Output: global sensitivities 
  Example (toy): $java ksall 100 3 5
*/

import java.io.*;
import java.util.*;
import java.math.*;

class ksall{
    public static void main(String [] args) throws Exception{
	String line;
	FileInputStream fis;
	BufferedReader stdin;
	StringTokenizer st;
	if(args.length<3){ 
		System.out.println("format: $java ksall [sampleNo] [parNo] [dim]");
	    return;	
	}
	int N=Integer.parseInt(args[0]);  // sample number
	int numPar=Integer.parseInt(args[1]); // parameter number
	int numCol=Integer.parseInt(args[2]); // interval number
	double result[][]=new double[numPar][numCol*2+2];

    // load the frequency data and compute the Kolmogorov–Smirnov (KS) statistics
	for(int k=0;k<numPar;k++){
	    double sum[]=new double[numCol+1];sum[0]=0;
	    double sum2[]=new double[numCol+1];sum2[0]=0;
	    double cumfreq[]=new double[numCol+1];
	    double cumfreq2[]=new double[numCol+1];
	    double max=-1;
	    fis = new FileInputStream("Nk"+k+".txt");
	    stdin = new BufferedReader(new InputStreamReader(fis));
	    for(int i=1;i<N-1;i++) stdin.readLine();
	    line=stdin.readLine();
	    st = new StringTokenizer(line, " \t");
	    for(int i=0;i<numCol;i++){
		double tmp=Double.parseDouble(st.nextToken());
		sum[i+1]=sum[i]+tmp;
		sum2[i+1]=sum2[i]+(1-tmp);
	    }
	    for(int i=0;i<numCol+1;i++){
		cumfreq[i]=sum[i]/sum[numCol];
		cumfreq2[i]=sum2[i]/sum2[numCol];
		double diff=Math.abs(cumfreq[i]-cumfreq2[i]);
		if(diff>max) max=diff;

		result[k][2*i]=cumfreq[i];
		result[k][2*i+1]=cumfreq2[i];		
	    }
	    System.out.println(k+"\t"+max);  // max is the global sensitivity for parameter k
	    
	}
	
	// generate a file is used for plotting the frequency graph
	FileOutputStream outfile=new FileOutputStream("mpsa-all.txt");	    
	PrintWriter out= new PrintWriter(outfile);
	for(int i=0;i<numCol+1;i++){
	    for(int k=0;k<numPar;k++){
		out.print(result[k][2*i]+"\t");
		out.print(result[k][2*i+1]+"\t");
	    }
	    out.println();
	}
	out.flush();
    }
}
