package ProjetoBanco;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class about {
	
	public static void sobre() {
		
    JFrame tela = new JFrame("Sobre o programa");
    JLabel texto = new JLabel();
    JPanel c =new JPanel();
    c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
    c.add(new JLabel("                      "));
    c.add(new JLabel("               Esse programa � um projeto que"));
    c.add(new JLabel("               simula um aplicativo de um banco"));
    c.add(new JLabel("               real, contendo nele as fun��es principais"));
    c.add(new JLabel("               de um aplicativo de um banco, como dep�sito,"));
    c.add(new JLabel("               tranfer�ncia e saque."));
   
    tela.setBounds(50, 100, 400, 150); 
    tela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    tela.add(c);
    tela.setVisible(true);
    
    
 
}
}
