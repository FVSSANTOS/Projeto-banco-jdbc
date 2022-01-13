package ProjetoBanco;



public class ValidarCpf {
	
	public static boolean validarCPF(String cpf) {
	int tam=cpf.length(),cont=10,digito1,digito2,soma=0,restoSoma=0,cont2=11;
    char car;
    int [][] validacao1 = new int[2][9];
    int [][] validacao2 = new int[2][10];

    for(int i=0;i<tam;i++){
        car = cpf.charAt(i);
        if(!Character.isDigit(car)){
            return false;
        }
    }
    for(int i=0;i<9;i++){ 
        validacao1[0][i]= (cpf.charAt(i)-48);
        validacao1[1][i]= cont;
        cont--;
        soma= soma + (validacao1[0][i]*validacao1[1][i]);
    }
    restoSoma = soma%11;
    if(restoSoma<2){
        digito1=0;
    }else{
        digito1=11-restoSoma;
    }
    soma = 0;

    for(int i=0;i<10;i++){ 
        validacao2[0][i]= (cpf.charAt(i)-48);
        validacao2[1][i]= cont2;
        cont2--;
        soma= soma + (validacao2[0][i]*validacao2[1][i]);
    }
    restoSoma = soma%11;
    if(restoSoma<2){
        digito2=0;
    }else{
        digito2=11-restoSoma;
    }
    if(digito1==(cpf.charAt(9)-48) && digito2==(cpf.charAt(10)-48)){
        return true;
    }else{
        return false;
    }
	}

}
