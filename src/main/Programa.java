package main;


import java.io.FileNotFoundException;

import cache.*;


public class Programa {
	public static void main(String[] args) throws FileNotFoundException {
		

		System.out.println("Aritz Herrero Perez de Albeniz. EHU-2018");
		CacheEstructure cache= new CacheEstructure();
		
		
		
		cache.datuakIrakurri();
		int EskaeraKop=0;
		int AsmatzeKop=0;
		boolean hit=false;
		
		//cachean egingo diren eragiketak irakrtzeko beharrezkoa den while
		// lehenengo Eskaera prozesatuko du(Helbidea irakurri) eta gero
		// load edo store den kontuan hartuz cahcea eguneratuko du.
		while(true) {
			
			System.out.println("Adierazi helbide bat(-1 amaitzeko): ");
			int H=cache.sarrera.nextInt();
			if (cache.source==0) {
				System.out.println(H);
			}
			
			// -1 irakurtzean programa bukatuko da.
			if (H==-1) {
				System.out.println("Programaren amaiera");
				cache.sarrera.close();
				break;
			}
			
			EskaeraKop++;
			Eskaera E =new Eskaera(H, cache);
			int ldst=-1;
			while(ldst!=0&&ldst!=1) {
				System.out.println("Eragiketa Load(0) Store(1): ");
				ldst=cache.sarrera.nextInt();
				if (cache.source==0) {
					System.out.println(ldst);
				}
				
			}
			E.IdatziEskaera();
			//Cache memoria eguneratu, boolearra bueltatu asmatzea edo hutsa den jakiteko.
			hit=E.MemoriaEguneratu(ldst,cache);
			
			cache.memoriaBistaratu();
	
			if (hit==true) {
				AsmatzeKop++;
			}
			System.out.println(AsmatzeKop);
		}
		double h=(float)AsmatzeKop/EskaeraKop;
		
		System.out.println("Eskaera kopurua: "+EskaeraKop +" Asmatze tasa:" + h);
		System.out.println("Ziklo kopurua: " +cache.ExekuzioDenbora);
	}
}
