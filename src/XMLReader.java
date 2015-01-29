import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLReader {
	
	public static void main(String[] args) throws SAXException, IOException {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter number of article: ");
			int num = input.nextInt();
			URL url = new URL("http://www.klix.ba/rss/svevijesti");
			URLConnection connection = url.openConnection();
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			String homeDir = System.getProperty("user.home");
			String path = homeDir + File.separator + "workspace" + File.separator + 
					 "XMLHomework" + File.separator + "Folder" + File.separator + 
					 File.separatorChar + "klix.xml";
			Document xmlDoc = db.parse(connection.getInputStream());
			NodeList xmlArticles = xmlDoc.getElementsByTagName("item");
			LinkedList<Article> articles = new LinkedList<Article>();
			Article ar = null;
			String childTitle = null;
			String childArticle = null;
			for (int i=0; i<xmlArticles.getLength(); i++)
			{
				Node current = xmlArticles.item(i);
				if (current instanceof Element)
				{
					NodeList xmlChildren = current.getChildNodes();
					for (int j=0; j<xmlChildren.getLength(); j++)
					{
						Node currentChild = xmlChildren.item(j);
						if (currentChild instanceof Element)
						{
							Element currentChildElement = (Element) currentChild;
							if (currentChildElement.getTagName().equals("title")) {
								childTitle = currentChildElement.getTextContent();
							}
							if (currentChildElement.getTagName().equals("clanak")) {
								childArticle = currentChildElement.getTextContent();
							}
							ar = new Article(childTitle, childArticle);
						}
					}
					articles.add(ar);
				}
			}
			Iterator<Article> it = articles.iterator();
			System.out.println(articles.get(num).printArticle());
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
