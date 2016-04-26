import gui.Frame;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import database.XMLDatabase;

public class Systembolaget {

	public static void main(String[] args) {

		BufferedInputStream in;
		try {
			URL xmlurl = new URL(
					"http://www.systembolaget.se/api/assortment/products/xml");
			in = new BufferedInputStream(xmlurl.openStream());

			XMLDatabase xmlData = new XMLDatabase(in);
			
			Frame frame = new Frame(xmlData);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
