package gui;
import javax.swing.JFrame;

import database.XMLDatabase;


public class SearchResultsArticlePanel extends ArticlesPanel {
	private String[][] articles;

	public SearchResultsArticlePanel(JFrame parentFrame, XMLDatabase xmlData, String[][] articles){
		super(parentFrame, xmlData);
		this.articles = articles;
	}
	
	public void updateArticles(){
		articleTable.setModel(new ArticleTableModel(articles,articleTableHeader));
	}
	
	protected void addFilterPanel(){
		return;
	}
}
