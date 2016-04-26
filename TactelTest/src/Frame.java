import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Frame extends JFrame{

	
	public Frame(XMLDatabase xmlData){
		setTitle("Systembolaget");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel searchPanel = new JPanel();
		JTextField searchField = new JTextField(20);
		JButton searchButton = new JButton("Sök");
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		add(searchPanel,BorderLayout.NORTH);
		
		ArticlesPanel articlesPanel = new ArticlesPanel(this,xmlData);
		add(articlesPanel);
		
		pack();
	}
	
	

}
