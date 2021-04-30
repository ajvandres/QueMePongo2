import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueMePongo {
	public static void main(String[] args) {		
		System.out.println("Hola Mundo");
		Prenda zapato = new Prenda(TipoDePrenda.ZAPATO);
		zapato.setColorMaterialTrama(Material.CUERO, Trama.LISA,new Color(0,0,0), null);
	}
}

class Prenda {
	TipoDePrenda tipoDePrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;	

	Prenda(TipoDePrenda tipoDePrenda) {
		if(tipoDePrenda == null) {throw new PrendaInvalidaException("Falta el tipo de prenda");}
		this.tipoDePrenda = tipoDePrenda;		
	}	
	
	void setColorMaterialTrama(Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
		if(material == null) {throw new PrendaInvalidaException("Falta el material");}
		if(colorPrimario == null) {throw new PrendaInvalidaException("Falta el color");}
		if(this.material != null) {throw new PrendaInvalidaException("No se puede volver a cargar los atributos");}
		ValidadorMaterialTipoPrenda validador = new ValidadorMaterialTipoPrenda();
		validador.esCombinacionValida(this.tipoDePrenda, material);
		this.material = material;
		material.setTrama(trama);
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;				
	}	
	
	TipoDePrenda getTipoDePrenda() {
		return tipoDePrenda;
	}
}

class Color {
	int rojo, verde, azul;
	
	Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}
}

class PrendaInvalidaException extends RuntimeException {
	
	PrendaInvalidaException(String causa) {
		super("La Prenda es invalida porque: " + causa);
	}
}

class UniformeInvalidoException extends RuntimeException {
	
	UniformeInvalidoException(String causa) {
		super("El uniforme es invalido porque: " + causa);
	}
}

class Borrador {
	Prenda prenda;
	
	void crearPrenda(TipoDePrenda tipoDePrenda) {
		this.prenda = new Prenda(tipoDePrenda);
	}
	Prenda terminarPrenda(Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
		prenda.setColorMaterialTrama(material, trama, colorPrimario, colorSecundario);
		return this.prenda;
	}
}

class ValidadorMaterialTipoPrenda {
	Map<TipoDePrenda,List<Material>> listadoValidos = new HashMap<>();
	List<Material> pantalon = new ArrayList<>();
	List<Material> camisa = new ArrayList<>();
	List<Material> zapatos = new ArrayList<>();
	List<Material> chomba = new ArrayList<>();
	List<Material> zapatillas = new ArrayList<>();
	
	ValidadorMaterialTipoPrenda() {
		pantalon.add(Material.ALGODON);
		pantalon.add(Material.JEAN);
		pantalon.add(Material.ACETATO);
		camisa.add(Material.ALGODON);
		camisa.add(Material.JEAN);
		zapatos.add(Material.CUERO);
		zapatillas.add(Material.CUERO);
		chomba.add(Material.PIQUE);
		
		listadoValidos.put(TipoDePrenda.PANTALON, pantalon);
		listadoValidos.put(TipoDePrenda.PANTALON, camisa);
		listadoValidos.put(TipoDePrenda.CAMISA, zapatos);
		listadoValidos.put(TipoDePrenda.ZAPATILLAS, zapatillas);
		listadoValidos.put(TipoDePrenda.CHOMBA, chomba);
	}
	
	void esCombinacionValida(TipoDePrenda tipoDePrenda, Material material) {
		List<Material> materiales = listadoValidos.get(tipoDePrenda);
		if(!materiales.contains(material) ) {
			throw new PrendaInvalidaException("Materiales inconsistente con el tipo de prenda");
		}
	
	}
}

class Uniforme {
	Prenda prendaSuperior;
	Prenda prendaInferior;
	Prenda prendaCalzado;
	
	Uniforme(Prenda prendaSuperior, Prenda prendaInferior, Prenda prendaCalzado) {
		if(prendaSuperior.getTipoDePrenda().esPrendaDeLaCategoria(Categoria.PARTE_SUPERIOR) &&
		   prendaInferior.getTipoDePrenda().esPrendaDeLaCategoria(Categoria.PARTE_INFERIOR) &&
		   prendaSuperior.getTipoDePrenda().esPrendaDeLaCategoria(Categoria.CALZADO)  ) {
			 throw new UniformeInvalidoException("No cumple con prenda superior, inferior y calzado");
		}
		 this.prendaSuperior = prendaSuperior;
		 this.prendaInferior = prendaInferior;
		 this.prendaCalzado = prendaCalzado;
	}	
}

class Sugerencias {
	List<Uniforme> uniformes = new ArrayList<>();
	
	Sugerencias(Uniforme uniforme) {
		this.uniformes.add(uniforme);
	}
}

interface Institucion {
	
}

class ColegioSanJuan implements Institucion {
	Uniforme uniforme;
	
	ColegioSanJuan(Borrador borrador) {
		borrador.crearPrenda(TipoDePrenda.CHOMBA);
		Prenda prendaSuperior = borrador.terminarPrenda(Material.PIQUE, null, new Color(0,128,0), null);
		borrador.crearPrenda(TipoDePrenda.PANTALON);
		Prenda prendaInferior = borrador.terminarPrenda(Material.ACETATO, null, new Color(128,128,128), null);
		borrador.crearPrenda(TipoDePrenda.ZAPATILLAS);
		Prenda calzado = borrador.terminarPrenda(Material.CUERO, null, new Color(255,255,255), null);
		uniforme = new Uniforme(prendaSuperior, prendaInferior, calzado);
	}
}

class InstitutoJohnson implements Institucion {
	Uniforme uniforme;
	
	InstitutoJohnson(Borrador borrador) {
		borrador.crearPrenda(TipoDePrenda.CAMISA);
		Prenda prendaSuperior = borrador.terminarPrenda(Material.ALGODON, null, new Color(255,255,255), null);
		borrador.crearPrenda(TipoDePrenda.PANTALON);
		Prenda prendaInferior = borrador.terminarPrenda(Material.ALGODON, null, new Color(0,0,0), null);
		borrador.crearPrenda(TipoDePrenda.ZAPATO);
		Prenda calzado = borrador.terminarPrenda(Material.CUERO, null, new Color(0,0,0), null);
		uniforme = new Uniforme(prendaSuperior, prendaInferior, calzado);
	}
}