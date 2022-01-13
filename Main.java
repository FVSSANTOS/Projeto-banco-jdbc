package ProjetoBanco;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		
		Scanner in = new Scanner(System.in);
		
		String cpf,cpfD, nConta, nContaD;
		int op=10;
	    boolean result;
	    double valor;
	    boolean resultadoOp;
        
		do {	
			menu();
			op = in.nextInt();
			in.nextLine();		
			
			switch(op) {
			
			case 1:
				  
				  System.out.println();				  
				  cadastroCliente();
				 
				break;
			case 2: 
				 
				 System.out.print("Informe o CPF do titular da conta: ");
				  cpf = in.next();
				  System.out.print("Informe o n�mero da conta: ");
				  nConta = in.next();
				  result = Repositorios.procurar(cpf, nConta);
				  if(result == false) {
					  System.out.print("N�O EXISTE UMA CONTA V�NCULADA A ESSE CPF!!");
				  }else {
				  			  
				  do {
					  operacoes();
					  op = in.nextInt();in.nextLine();
				  switch(op) {
				  
				  case 1:
					  System.out.print("Informe o valor do dep�sito: R$");
					  valor = in.nextDouble();
					  resultadoOp = Repositorios.deposito(valor, cpf);
					  if(resultadoOp) {
					  System.out.println("Opera��o conclu�da!");
					  }else {
					  System.out.println("Falha ao realizar opera��o!");
					  }
					  break;
					  
				  case 2:
					  System.out.print("Informe o n�mero da conta do destinat�rio: ");
					  nContaD = in.next();
					  System.out.print("Informe o n�mero do CPF do destinat�rio: ");
					  cpfD = in.next();
					  System.out.print("Informe o valor da transfer�ncia: ");
					  valor = in.nextDouble();
					  if(Repositorios.transferencia(valor, nConta, cpfD, cpf)) {
					  System.out.println("Opera��o conclu�da!");
					  }else {
				      System.out.println("Falha ao realizar opera��o!");
					  }
					  break;
					
				  case 3:
					  System.out.print("Informe o valor do saque: R$");
					  valor = in.nextDouble();
					  if( Repositorios.saque(valor, nConta, cpf)) {
					  System.out.println("Opera��o conclu�da!");
					  }else {
					  System.out.println("Falha ao realizar opera��o!");
					  }
					  break;
				  case 0:
					  System.out.print("FIM DO PROGRAMA!!!");
					break;
					
				   default:
					   System.out.println("OP��O INV�LIDA!!!");
				  }
				  }while(op != 0);
				  }
				  break;
				  
			     case 3:
			    	 about.sobre();
			    	 break;
			    	 
			      case 0:
			    	  System.out.print("FIM DO PROGRAMA");
			    	  break;
			default:				
			    System.out.println("OP��O INV�LIDA!!!");
			}
		}while(op!=0);
		
		Repositorios.fecharDB();
	}

	
	public static void menu() {
	
		System.out.println("======= ESCOLHA UMA DAS OP��ES =======");
		System.out.println();
		System.out.println("1 - Criar uma conta");
		System.out.println("2 - Acessar uma conta");
		System.out.println("3 - Sobre o programa");
		System.out.println("0 - Sair");
		System.out.print("Informe a op��o:");
		
	}
	
	
	
	public static String gerarNumero() {
		String result = "";
		Random aleatorio = new Random();
		for (int i = 0; i < 6; i++) {
			int valor = aleatorio.nextInt(9);
			String e = Integer.toString(valor);
			result = result.concat(e);
		}
		return result;
	}
	
	public static void cadastroCliente() throws ParseException {
		
	      Scanner in = new Scanner(System.in);
	      
		  String nome,cpf, nConta;
		  Double saldoInicial;
		  Conta conta;
		  
		  System.out.println("======= INSIRA AS INFORMA��ES DO TITULAR =======");
		  System.out.println();
		  System.out.print("Informe o nome do titular da conta: ");
		  nome = in.nextLine();
	do {
		System.out.print("Informe o CPF do titular: ");
		  cpf = in.next();
	}while(cpf.length()!=11);
		  if(ValidarCpf.validarCPF(cpf)) {
			  while(Repositorios.procurarCpf(cpf)) {
				  System.out.println("J� existe uma conta com esse CPF! TENTE NOVAMENTE!");
				  cadastroCliente();
			  }
		  }else{
			  while(!ValidarCpf.validarCPF(cpf)){
			  System.out.print("Informe um CPF v�lido: ");
			  cpf = in.next();			  
		  }
		  }
		  System.out.print("Informe o saldo inicial: R$");
		  saldoInicial = in.nextDouble();
		  in.nextLine();
		  System.out.println();
		  System.out.println("=========================================");
		  Cliente cliente = new Cliente(nome, cpf);
		  
		  nConta = gerarNumero();
		  while(Repositorios.procurarNumeroConta(nConta)) {
			  nConta = gerarNumero();
		  }
		  
		  conta = EscolherConta.escolhaConta(nConta, cliente, saldoInicial);
		  
		  if(conta == null) {
			  System.out.println("OP��O INV�LIDA!!!");
		  }
		  
		  InforConta tela = new InforConta(conta);
		  
		  Repositorios.conexao(nome, cpf, conta, saldoInicial);
		  
	}
	
   
   public static void operacoes() {
	   System.out.println("======= O QUE VOC� DESEJA? =======");
	   System.out.println();
	   System.out.println("1 - Dep�sito");
	   System.out.println("2 - Transfer�ncia");
	   System.out.println("3 - Saque");
	   System.out.println("0 - Sair");
	   System.out.print("Informe a op��o: ");
   }
}
