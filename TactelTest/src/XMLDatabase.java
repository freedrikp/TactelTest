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
	
	public String[] getArticleNames(){
		NodeList nodes = document.getElementsByTagName("artikel");
		String[] articleNames = new String[nodes.getLength()];
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element nodeElement = (Element)node;
				String articleName = nodeElement.getElementsByTagName("Namn").item(0).getTextContent();
				articleNames[i]=articleName;
			}
		}
		return articleNames;
	}

}
