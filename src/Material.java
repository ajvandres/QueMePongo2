
public enum Material {
	ALGODON,JEAN,PLASTICO,CUERO,PIQUE,ACETATO;	
	
	Trama trama;
	
	void setTrama(Trama trama) {
		if(trama != null) {
			this.trama = trama;
		}
		else {
			this.trama = Trama.LISA;
		}
	}

}
