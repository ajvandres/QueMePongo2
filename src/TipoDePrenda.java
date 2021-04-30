public enum TipoDePrenda {
	ZAPATO(Categoria.CALZADO),
    CAMISA(Categoria.PARTE_SUPERIOR),
    PANTALON(Categoria.PARTE_INFERIOR),
    CHOMBA(Categoria.PARTE_SUPERIOR),
    ZAPATILLAS(Categoria.CALZADO),
    PANTALON_DE_VESTIR(Categoria.PARTE_INFERIOR)    
    ;
	
	Categoria categoria;
	
	TipoDePrenda(Categoria categoria) {
    this.categoria = categoria;
	}
	boolean esPrendaDeLaCategoria(Categoria categoria) {
		return this.categoria == categoria;
	}
}