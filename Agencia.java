package ProjetoBanco;

public class Agencia {

	String numero;

	public Agencia() {
		this.numero = "01";
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String toString() {
		return ""+getNumero();
	}
}
