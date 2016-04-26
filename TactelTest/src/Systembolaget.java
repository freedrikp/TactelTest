import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Systembolaget {

	public static void main(String[] args) {

		BufferedInputStream in;
		try {
			URL xmlurl = new URL(
					"http://www.systembolaget.se/api/assortment/products/xml");
			in = new BufferedInputStream(xmlurl.openStream());

			XMLDatabase xmlParser = new XMLDatabase(in);
			List<String> articleNames = xmlParser.getArticleNames();
			for (String articleName : articleNames){
				System.out.println(articleName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
