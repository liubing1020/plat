import java.io.*;
import java.util.*;

public class bound{
    public static void main(String[] args) throws IOException{
	double Ki[][]={
	    {0.0115,0.02888669396236019,0.07256009461522228,0.18226271713302816,0.4578232461365216,1.15},
	    {3.8E-8,9.545168439736411E-8,2.3976379090247364E-7,6.022594131352235E-7,1.512807248103289E-6,3.8E-6},
	    {1.47E-8,3.692473054319085E-8,9.275072963858849E-8,2.3297929929178386E-7,5.852175407136407E-7,1.47E-6},
	    {0.142,0.3566878732743606,0.8959594291618752,2.2505483332947827,5.653121821859658,14.2},
	    {7.3E-8,1.8336770950019949E-7,4.605988614705415E-7,1.1569720304966138E-6,2.906182345040529E-6,7.3E-6},
	    {2.0E-9,5.023772863019163E-9,1.2619146889603877E-8,3.169786384922229E-8,7.962143411069942E-8,2.0E-7},
	    {5.0E-4,0.001255943215754791,0.0031547867224009695,0.007924465962305574,0.019905358527674857,0.05},
	    {1.1E-9,2.76307507466054E-9,6.940530789282132E-9,1.743382511707226E-8,4.379178876088468E-8,1.1E-7},
	    {1.0E-13,2.5118864315095815E-13,6.309573444801937E-13,1.5848931924611144E-12,3.98107170553497E-12,1.0E-11},
	    {3.0E-6,7.535659294528745E-6,1.8928720334405814E-5,4.7546795773833434E-5,1.1943215116604911E-4,3.0E-4},
	    {3.6E-5,9.042791153434495E-5,2.271446440128698E-4,5.705615492860013E-4,0.0014331858139925895,0.0036},
	    {2.0E-6,5.023772863019164E-6,1.2619146889603878E-5,3.1697863849222296E-5,7.962143411069943E-5,2.0E-4},
	    {1.0,1.2,1.4,1.5999999999999999,1.7999999999999998,2.0},
	    {1.0,1.2,1.4,1.5999999999999999,1.7999999999999998,2.0},
	    {158.4893192,199.5262314459304,251.18864309080706,316.22776595435715,398.10717048623724,501.1872335760148,630.9573444631455,794.3282346469441,999.9999999265477,1258.9254117008702,1584.893192368544,1995.262314827848,2511.8864312766145,3162.2776599121157,3981.0717052309165,5011.872336128692,6309.573445},
	    {158.4893192,199.5262314459304,251.18864309080706,316.22776595435715,398.10717048623724,501.1872335760148,630.9573444631455,794.3282346469441,999.9999999265477,1258.9254117008702,1584.893192368544,1995.262314827848,2511.8864312766145,3162.2776599121157,3981.0717052309165,5011.872336128692,6309.573445},
	};
	
	
	int x = Integer.parseInt(args[0]);
	int y = Integer.parseInt(args[1]);
	
	FileInputStream fis =new FileInputStream("output1.dat");
	BufferedReader in=new BufferedReader(new InputStreamReader(fis));
	String line;
	int bin=0;
	for(int i=0;i<16;i++){
	    if(i<14){
		line=in.readLine();
		StringTokenizer st=new StringTokenizer(line, ",");
		bin=(int)Double.parseDouble(st.nextToken());
	    }
	    else if(i==14)
		bin=8+x;
	    else if(i==15)
		bin=12+y;
	    System.out.println("lb["+i+"]="+Ki[i][bin]
			       +"; ub["+i+"]="+Ki[i][bin+1]+"; //"+bin);
	}
    }
}