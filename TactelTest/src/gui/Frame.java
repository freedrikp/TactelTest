package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.XMLDatabase;

public class Frame extends JFrame implements ActionListener {
	private XMLDatabase xmlData;
	private JTextField searchField;

	public Frame(XMLDatabase xmlData) {
		this.xmlData = xmlData;
		setTitle("Systembolaget");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel searchPanel = new JPanel();
		searchField = new JTextField(20);
		JButton searchButton = new JButton("Sök");
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		add(searchPanel, BorderLayout.NORTH);
		searchButton.addActionListener(this);

		ArticlesPanel articlesPanel = new ArticlesPanel(this, xmlData);
		add(articlesPanel);

		articlesPanel.updateArticles();
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		String searchedArticleName = searchField.getText().trim();
		String[][] searchResults = xmlData.searchForArticle(searchedArticleName);
		if ( searchResults != null){
			JFrame resultsFrame = new JFrame("Sökresultat");
			SearchResultsArticlePanel resultsPanel = new SearchResultsArticlePanel(resultsFrame, xmlData, searchResults);
			resultsFrame.add(resultsPanel);
			resultsFrame.setVisible(true);
			resultsPanel.updateArticles();
			resultsFrame.pack();
		}		
	}

}
