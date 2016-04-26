package gui;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.XMLDatabase;


public class ArticlesPanel extends JPanel implements ListSelectionListener{
	protected JTable articleTable;
	protected static final String[] articleTableHeader = {"ID","Namn"};
	private JPanel detailsPanel;
	private JFrame parentFrame;
	private XMLDatabase xmlData;
	
	public ArticlesPanel(JFrame parentFrame, XMLDatabase xmlData){
		this.parentFrame = parentFrame;
		this.xmlData = xmlData;
		JScrollPane articlesPane = new JScrollPane();
		articleTable = new JTable();
		articlesPane.setViewportView(articleTable);
		add(articlesPane,BorderLayout.WEST);
		articleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		articleTable.getSelectionModel().addListSelectionListener(this);
		
		JScrollPane detailsPane = new JScrollPane();
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPane.setViewportView(detailsPanel);
		add(detailsPane,BorderLayout.EAST);
	}
	
	public void updateArticles(){
		articleTable.setModel(new ArticleTableModel(xmlData.getArticleNames(),articleTableHeader));
	}
	
	private void updateArticleDetails(String articleID){
		String[][] details = xmlData.getArticleDetails(articleID);
		detailsPanel.removeAll();
		for (int i = 0; i < details.length; i++){
			String detailString = details[i][0] + ": " + details[i][1];
			detailsPanel.add(new JLabel(detailString));
		}
		parentFrame.pack();
	}

	public void valueChanged(ListSelectionEvent e) {
		int selectedRow = articleTable.getSelectedRow();
		String selectedArticleID = (String) articleTable.getValueAt(selectedRow, 0);
		updateArticleDetails(selectedArticleID);
	}

}
