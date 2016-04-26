package gui;
import javax.swing.table.DefaultTableModel;

public class ArticleTableModel extends DefaultTableModel {

	public ArticleTableModel(String[][] data, String[] header){
		super(data,header);
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
