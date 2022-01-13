package ProjetoBanco;

import java.util.Scanner;

public class EscolherConta {
	
public static Conta escolhaConta(String nConta, Cliente cliente,Double saldoInicial) {	

	    Scanner in = new Scanner(System.in);
	
	    int tipoConta;
	    Conta conta;
	    
		System.out.println("======= ESCOLHA UM TIPO DE CONTA =======");
		System.out.println();
		System.out.println("1 - Criar conta corrente");
		System.out.println("2 - Criar conta poupança");
		System.out.print("Informe a opção:");
		tipoConta = in.nextInt();
		System.out.println();
		System.out.println("=========================================");
		
		if(tipoConta == 1) {
		  	  conta = new ContaCorrente(nConta, new Agencia(), cliente, saldoInicial);	
		  	  return conta;
	   }else if(tipoConta == 2) {
		      conta = new Poupança(nConta, new Agencia(), cliente, saldoInicial);
		      return conta;
	   }else {
		      return null;
	  }		
	
	}
}
