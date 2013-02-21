package jaxb;

import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.ObjectFactory;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006

public class JAXBMarshaller
{
	public void generateXMLDocument()
    {
		try {
			JAXBContext jaxbContext =
                    JAXBContext.newInstance("jaxb.generated");
			Marshaller marshaller = jaxbContext.createMarshaller();
			ObjectFactory factory = new ObjectFactory();

			CatalogType catalog = factory.createCatalogType();
			List<JournalType> journalList = catalog.getJournal();

			JournalType journal = factory.createJournalType();
			List<ArticleType> articleList = journal.getArticle();

			journalList.add(journal);

			ArticleType article = factory.createArticleType();
			article.setTitle("Service Oriented Architecture Frameworks");
			article.setAuthor("Naveen Balani");
			article.setLevel("Intermediate");
			article.setDate("January-2004");
			articleList.add(article);

			article = factory.createArticleType();
			article.setTitle("Advance DAO Programming");
			article.setAuthor("Sean Sullivan");
			article.setLevel("Advanced");
			article.setDate("October-2003");
			articleList.add(article);

			article = factory.createArticleType();
			article.setTitle("Best Practices in EJB Exception Handling");
			article.setAuthor("Srikanth Shenoy");
			article.setLevel("Advanced");
			article.setDate("May-2002");
			articleList.add(article);

			JAXBElement<CatalogType> catalogElement =
                    factory.createCatalog(catalog);
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(catalogElement, System.out);
		}
        catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] argv)
    {
		JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
		jaxbMarshaller.generateXMLDocument();
	}
}
