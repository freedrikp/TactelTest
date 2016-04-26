import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class Frame extends JFrame{
	private XMLDatabase xmlData;
	private JList<String> articleList;
	
	public Frame(XMLDatabase xmlData){
		this.xmlData = xmlData;
		setTitle("Systembolaget");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane articlesPane = new JScrollPane();
		articleList = new JList<String>();
		articlesPane.setViewportView(articleList);
		add(articlesPane);
		
		updateArticleList();
		pack();
	}
	
	private void updateArticleList(){
		articleList.setListData(xmlData.getArticleNames());
	}

}
