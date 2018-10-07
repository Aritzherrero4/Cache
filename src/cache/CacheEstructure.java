package cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CacheEstructure {
	//parametroak
	int Htam, Btam, Mtam, politika, Helbidea,Eragiketa;
	// politika 0=LRU, 1==FIFO
	public int ExekuzioDenbora=0,source;
	public Scanner sarrera = new Scanner(System.in);
	
	int[][] Memoria = new int[8][5];

	
	
	public void datuakIrakurri() throws FileNotFoundException {
		System.out.println("Memoriaren konfigurazioa");
		boolean egokia=false;
		while (egokia==false) {
			System.out.println("Adierazi hitzaren tamaina 4-8");
			Htam=sarrera.nextInt();
			if (Htam==4 || Htam==8) {
				egokia=true;
			}
			
		}
		egokia=false;
		while (egokia==false) {
			System.out.println("Adierazi blokearen tamaina(32 edo 64)");
			Btam=sarrera.nextInt();
			if (Btam==32 || Btam==64) {
				egokia=true;
			}
			
		}
		egokia=false;
		while (egokia==false) {
			System.out.println("Adierazi multzoen tamaina(1, 2, 4 edo 8)");
			Mtam=sarrera.nextInt();
			if (Mtam==1 || Mtam==2||Mtam==4 || Mtam==8) {
				egokia=true;
			}
			
		}
		egokia=false;
		
		while (egokia==false) {
			System.out.println("Adierazi Ordezkapen politika (0 LRU edo 1 FIFO)");
			politika=sarrera.nextInt();
			if (politika==1 || politika==0) {
				
				egokia=true;
			}	
		}
		egokia=false;
		
		while (egokia==false) {
			
			System.out.println("Aukeratu datuak irakurtzeko modua: (Fitxategia(0) Teklatua(1))");
			source= sarrera.nextInt();
			if (source==1 || source==0) {
				
				egokia=true;
			}	
		}
		
		if (source==0) {
			System.out.println("Aukeratu nahi duzun fitxategia(1-2):");
			int zenb = sarrera.nextInt();
			String FileName="inputs/datuak"+zenb+".txt";
			sarrera=new Scanner(new File(FileName));
		}else {
			 sarrera = new Scanner(System.in);
		}


		
	}
	
	
    public void memoriaBistaratu() {
    	System.out.println("---------------------------------------------");
    	System.out.println("|  Okup |  ald |  tag |  ord ||  blokea |");
    	for (int i=0;i<Memoria.length;i++ ) {
    		if(i%Mtam==0) {
    			System.out.println("|--------------------------------------------");
    		}
    		System.out.print("|");

    		for (int j=0;j<Memoria[i].length-1;j++ ) {
    			System.out.print( "   "+Memoria[i][j]+"   ");
    		}
    		System.out.println("||    "+Memoria[i][4]+"    ");
    	}
    	 
    }
	
	public CacheEstructure() {}

	
	
	
	
	
}


