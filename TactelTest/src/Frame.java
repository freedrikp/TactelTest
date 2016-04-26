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


public class Frame extends JFrame implements ListSelectionListener{
	private XMLDatabase xmlData;
	private JTable articleTable;
	private static final String[] articleTableHeader = {"ID","Namn"};
	private JPanel detailsPanel;
	
	public Frame(XMLDatabase xmlData){
		this.xmlData = xmlData;
		setTitle("Systembolaget");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
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
		
		
		updateArticleList();
		pack();
	}
	
	private void updateArticleList(){
		articleTable.setModel(new ArticleTableModel(xmlData.getArticleNames(),articleTableHeader));
	}
	
	private void updateArticleDetails(String articleID){
		String[][] details = xmlData.getArticleDetails(articleID);
		detailsPanel.removeAll();
		for (int i = 0; i < details.length; i++){
			String detailString = details[i][0] + ": " + details[i][1];
			detailsPanel.add(new JLabel(detailString));
		}
		pack();
	}

	public void valueChanged(ListSelectionEvent e) {
		int selectedRow = articleTable.getSelectedRow();
		String selectedArticleID = (String) articleTable.getValueAt(selectedRow, 0);
		updateArticleDetails(selectedArticleID);
	}

}
