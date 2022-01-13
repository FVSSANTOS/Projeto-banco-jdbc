package ProjetoBanco;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class InforConta {

	public InforConta(Conta conta) {
       JFrame f = new JFrame("About");
    	
    	JPanel c =new JPanel();
    	c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
    	c.add(new JLabel("Nome:"+conta.getCliente().getNome()));
    	c.add(new JLabel("Saldo:"+conta.getSaldo()));
    	c.add(new JLabel("Agência:"+conta.getAgencia()));
    	c.add(new JLabel("Conta:"+conta.getNumero()));
    	
    	f.setBounds(50, 100, 300, 150); 
    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	f.add(c);
    	f.setVisible(true);
	    
	}
	
	
}
