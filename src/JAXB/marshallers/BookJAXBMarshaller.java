package jaxb.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.book.BookRoot;
import jaxb.generated.book.BookType;
import jaxb.generated.book.ObjectFactory;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006

public class BookJAXBMarshaller
{
	public void generateXMLDocument()
    {
		try {
			JAXBContext jaxbContext =
                    JAXBContext.newInstance("jaxb.generated.book");
			Marshaller marshaller = jaxbContext.createMarshaller();
			ObjectFactory factory = new ObjectFactory();

         BookRoot book = factory.createBookRoot();
         List<BookType> bookList = book.getBookInfo();

         BookType bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("1"));
         bookInfo.setPubDate("2008");
         bookInfo.setTitle("Bag of Bones");
         bookInfo.setIsbnId(new BigInteger("1"));
         bookInfo.setPubId(new BigInteger("1"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("2"));
         bookInfo.setPubDate("1933");
         bookInfo.setTitle("Lost Horizon");
         bookInfo.setIsbnId(new BigInteger("2"));
         bookInfo.setPubId(new BigInteger("1"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("3"));
         bookInfo.setPubDate("1923");
         bookInfo.setTitle("Bambi: A Life in the Woods");
         bookInfo.setIsbnId(new BigInteger("3"));
         bookInfo.setPubId(new BigInteger("1"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("4"));
         bookInfo.setPubDate("2008");
         bookInfo.setTitle("Database Systems, the Complete Book");
         bookInfo.setIsbnId(new BigInteger("4"));
         bookInfo.setPubId(new BigInteger("2"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("5"));
         bookInfo.setPubDate("1998");
         bookInfo.setTitle("Algebra: Tools for a Changing World");
         bookInfo.setIsbnId(new BigInteger("5"));
         bookInfo.setPubId(new BigInteger("3"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("6"));
         bookInfo.setPubDate("2009");
         bookInfo.setTitle("Under the Dome");
         bookInfo.setIsbnId(new BigInteger("6"));
         bookInfo.setPubId(new BigInteger("4"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("7"));
         bookInfo.setPubDate("2002");
         bookInfo.setTitle("Murder at School");
         bookInfo.setIsbnId(new BigInteger("7"));
         bookInfo.setPubId(new BigInteger("5"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("8"));
         bookInfo.setPubDate("1977");
         bookInfo.setTitle("The Shining");
         bookInfo.setIsbnId(new BigInteger("8"));
         bookInfo.setPubId(new BigInteger("4"));
         bookList.add(bookInfo);

         bookInfo = factory.createBookType();
         bookInfo.setBookId(new BigInteger("9"));
         bookInfo.setPubDate("1975");
         bookInfo.setTitle("Salem's Lot");
         bookInfo.setIsbnId(new BigInteger("9"));
         bookInfo.setPubId(new BigInteger("6"));
         bookList.add(bookInfo);

			JAXBElement<BookRoot> bookElement =
                    factory.createBook(book);
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(bookElement, System.out);
		}
        catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] argv)
    {
		BookJAXBMarshaller jaxbMarshaller = new BookJAXBMarshaller();
		jaxbMarshaller.generateXMLDocument();
	}
}
