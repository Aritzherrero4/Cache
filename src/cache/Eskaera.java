package cache;

public class Eskaera {
	int Helbidea,Hitza, Blokea,Multzoa,tag,Hblko;
	int Htam;
	int denbora=0;
	boolean hit;
	
	public Eskaera(int H, CacheEstructure cache) {
		this.Helbidea=H;
		this.Htam=cache.Htam;
		this.Hitza=this.Helbidea/cache.Htam;
		this.Blokea=this.Helbidea/cache.Btam;
		this.Hblko=cache.Btam/this.Htam;
		
		if(cache.Mtam==8) {
			//guztiz elkargarria
			tag=Blokea;
			Multzoa=0;
		}
		else if(cache.Mtam==1) {
			//zuzenekoa
			this.tag=Blokea/8;
			this.Multzoa=Blokea%8;
			
		}
		else {
			//Multzotako elkargarria
			int mkop=8/cache.Mtam;
			this.tag=this.Blokea/mkop;
			this.Multzoa=this.Blokea%mkop;
			
		}
	}
	
	public boolean MemoriaEguneratu(int ldst, CacheEstructure cache) {
		int pos = 0;
		//Konprobatu memoria libre dagoen:
		boolean libre=false;
		boolean hit=false;
		//Multzoen kontrolerako informazioa
		int blhasi=Multzoa*cache.Mtam;
		int blbuk=blhasi+cache.Mtam;
		
		//konprobaketa Asmatzea den eta bestela, posizioa libre dagoen.
		for(int i=blhasi;i<blbuk;i++) {
			if(cache.Memoria[i][4]==Blokea&&cache.Memoria[i][0]==1) {
				hit=true;	
				pos=i;
				break;
			}
			if(cache.Memoria[i][0]==0 && libre==false) {
				libre=true;
				pos=i;
				
			}

		}
		//Memorian egondako denbora eguneratu:
		for (int j=0;j<8;j++) {
			if(cache.Memoria[j][0]==1) {
				cache.Memoria[j][3]+=1;
			}
		}
		//Asmatzea izan bada
		if(hit) {
			if (cache.politika==0) {
				cache.Memoria[pos][3]=0;//ord bakarrik LRU denean
			}
			cache.ExekuzioDenbora+=2;
			System.out.println("ASMATZEA");
			if(ldst==1) {
				//idazketa izatekotan eguneratu kontrol bita.
				cache.Memoria[pos][1]=ldst;	
			}
			
				
		}
		//Hutsa izan bada:
		else {
			System.out.println("Hutsa");
			if (!libre) {
				
				//bilatu denbora gehien erabili gabe egon dena
				int max=0;
				for(int i=blhasi;i<blbuk;i++) {
					if (max<cache.Memoria[i][3]) {
						max=cache.Memoria[i][3];
							pos=i;
					}	
				}

			}
			//Behar den memoria posizioa eguneratu eskaerarekin.
			cache.Memoria[pos][0]=1;//okup
			cache.Memoria[pos][1]=ldst;//ald
			cache.Memoria[pos][2]=tag;//tag
			cache.Memoria[pos][3]=0;//ord
			cache.Memoria[pos][4]=Blokea;//blokea;
			cache.ExekuzioDenbora+=2+20+(cache.Btam/cache.Htam)-1;
		}
		
		//LRU denean, orain erabili den datua 0 balioa izan behar du
		// ord aldagaian, azken erabilia aldatuko delako lekua ez dagoenean
		if (cache.politika==0) {
		cache.Memoria[pos][3]=0; 
		}
		
		//Asmatzea edo hutsa den gestioa osatzeko, programa naguzira bueltatu boolearra.
		return hit;
		
	}

	
	public void IdatziEskaera() {
		System.out.println("Helbidea: " + Helbidea +" - Hitza: "+ Hitza+ " - Blokea: "+ Blokea +"("+Blokea*Hblko+" - "+ (Blokea*Hblko+ Hblko-1)+" hitzak)");
		System.out.println("Mulzoa: "+Multzoa+" - Tag: "+tag);
	}

	
}
