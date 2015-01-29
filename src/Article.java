import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Article {
	
	private String title;
	private String content;
	private LinkedList<Article> articles;
	
	public Article(String title, String content) 
	{
		this.articles = new LinkedList<Article>();
		this.title = title;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.length()<1)
		{
			throw new IllegalArgumentException("Title cannot be empty field!");
		}
		else
		{
			this.title = title;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content.length() < 1)
		{
			throw new IllegalArgumentException("Content cannot be empty field!");
		}
		else
		{
			this.content = content;
		}
	}
	
	public String printArticle()
	{
		return title + "\n" + content;
	}

}
