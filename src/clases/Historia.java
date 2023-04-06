package clases;

public class Historia extends Publicacion {

	private boolean mejores_amigos;
	private String cod_tipo;

	/********** METODOS **********/
	@Override
	public String toString() {
		return "Historia [mejores_amigos=" + mejores_amigos + ", cod_tipo=" + cod_tipo + "]";
	}

	/********** CONSTRUCOR **********/
	/********** GETTER && SETTER **********/
	public boolean isMejores_amigos() {
		return mejores_amigos;
	}

	public void setMejores_amigos(boolean mejores_amigos) {
		this.mejores_amigos = mejores_amigos;
	}

	public String getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(String cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	/********** METODOS **********/
	/********** CONSTRUCOR **********/
	/********** GETTER && SETTER **********/
}
