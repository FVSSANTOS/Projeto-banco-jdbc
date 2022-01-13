package ProjetoBanco;

public abstract class Conta {
	
	private String numero;
	private Agencia agencia;
	private Cliente cliente;
	private Double saldo;
	
	public Conta(String numero, Agencia agencia, Cliente cliente, Double saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.cliente = cliente;
		this.saldo = saldo;
	}
	
	public String getNumero() {
		return numero;
	}
	
	
	public Agencia getAgencia() {
		return agencia;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	
	public Double getSaldo() {
		return saldo;
	}
	
	@Override
	public String toString() {
		return "Nome: "+cliente.getNome()+" \n"+
	           "Agência: "+agencia.getNumero()+" \n"+
			   "Conta: "+this.numero+" \n"+
	           "Saldo: R$"+this.saldo;
				
	}
}
