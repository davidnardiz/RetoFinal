package clases;

public class TipoHistoria {

	private String cod_tipo;
	private String tipo;

	/********** METODOS **********/
	@Override
	public String toString() {
		return "TipoHistoria [cod_tipo=" + cod_tipo + ", tipo=" + tipo + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	/********** CONSTRUCOR **********/
	/********** GETTER && SETTER **********/

	public String getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(String cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
