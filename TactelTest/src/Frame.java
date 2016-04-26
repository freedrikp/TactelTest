import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Frame extends JFrame{
	private XMLDatabase xmlData;
	private JTable articleList;
	private static final String[] articleTableHeader = {"ID","Namn"};
	
	public Frame(XMLDatabase xmlData){
		this.xmlData = xmlData;
		setTitle("Systembolaget");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane articlesPane = new JScrollPane();
		articleList = new JTable();
		articlesPane.setViewportView(articleList);
		add(articlesPane);
		
		updateArticleList();
		pack();
	}
	
	private void updateArticleList(){
		articleList.setModel(new ArticleTableModel(xmlData.getArticleNames(),articleTableHeader));
	}

}
