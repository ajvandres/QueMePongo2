# QueMePongo2
Traté de respetar el modelado correcto de objetos, pero a medida que pasaban las horas y se acababa el tiempo, procuré satisfacer el requerimiento

No tuve tiempo de hacer el diagrama, asi que puedo decir:

clase Prenda conoce a: enum TipoDePrenda, enum Material, enum Color
               usa a : clase PrendaInvalidaException, clase ValidadorMaterialTipoPrenda, clase UniformeInvalidoException
               
enum TipoDePrenda conoce a: enum Categoria

enum Material conoce a: enum Trama

clase Borrador conoce a: clase Prenda
                  usa a: enum TipoDePrenda, enum Material, enum Color, enum Trama

clase ValidadorMaterialTipoPrenda conoce a: enum TipoDePrenda, enum Material

clase Uniforme conoce a: clase Prenda

clase Sugerencias conoce a: clase Uniforme

interface Institucion usa a: clase Borrador, clase Prenda, enum TipoDePrenda, enum Material, enum Color, enum Trama


PD: voy a llevar el control de horas de TP, como en el laburo
