package database;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDatabase {
	private Document document;

	public XMLDatabase(InputStream xmlInputStream) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(xmlInputStream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[][] getArticleNames(){
		NodeList nodes = document.getElementsByTagName("artikel");
		String[][] articleNames = new String[nodes.getLength()][2];
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element nodeElement = (Element)node;
				String articleID = nodeElement.getElementsByTagName("Artikelid").item(0).getTextContent();
				articleNames[i][0] = articleID;
				String articleName = nodeElement.getElementsByTagName("Namn").item(0).getTextContent();
				articleNames[i][1] = articleName;
			}
		}
		return articleNames;
	}
	
	public String[][] getArticleDetails(String selectedArticleID){
		Node selectedNode = null;
		NodeList nodes = document.getElementsByTagName("artikel");
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element nodeElement = (Element)node;
				String articleID = nodeElement.getElementsByTagName("Artikelid").item(0).getTextContent();
				if (articleID.equals(selectedArticleID))
				{
					selectedNode = node;
					break;
				}
				
			}
		}
		if (selectedNode == null){
			return null;
		}
		NodeList children = selectedNode.getChildNodes();
		String[][] details = new String[children.getLength()][2];
		for (int i = 0; i < children.getLength(); i++){
			Node child = children.item(i);
			details[i][0] = child.getNodeName();
			details[i][1] = child.getTextContent();
		}
		return details;
	}
	
	public String[][] searchForArticle(String searchedArticleName){
		searchedArticleName = searchedArticleName.toLowerCase();
		ArrayList<String[]> tempResults = new ArrayList<String[]>();
		NodeList nodes = document.getElementsByTagName("artikel");
		String[][] articleNames = new String[nodes.getLength()][2];
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element nodeElement = (Element)node;
				String articleID = nodeElement.getElementsByTagName("Artikelid").item(0).getTextContent();
				articleNames[i][0] = articleID;
				String articleName = nodeElement.getElementsByTagName("Namn").item(0).getTextContent();
				articleName = articleName.toLowerCase();
				if (articleName.startsWith(searchedArticleName) || articleName.endsWith(searchedArticleName)){
					String[] entry = new String[2];
					entry[0] = articleID;
					entry[1] = articleName;
					tempResults.add(entry);
				}
			}
		}
		int nbrResults = tempResults.size();
		if (nbrResults <= 0){
			return null;
		}
		String[][] searchResults = new String[nbrResults][2];
		for (int i = 0; i < nbrResults; i++){
			String[] entry = tempResults.get(i);
			searchResults[i][0] = entry[0];
			searchResults[i][1] = entry[1];
		}
		return searchResults;
	}

}
