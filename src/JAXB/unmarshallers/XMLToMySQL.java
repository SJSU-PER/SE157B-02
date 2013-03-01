package JAXB.unmarshallers;

import java.io.File;

/**

 @author Team Cosmo Erni Ali, Randy Zaatri, Philip Vaca

 This class is responsible for calling all of the unmarshallers for each of the
 objects: genre, author, publisher, book and creates the XML document
 corresponding to the java objects.
 */
public class XMLToMySQL
{
   /**
    Main method for calling XMLToMySQL class, unmarshalls the xml documents for
    each class.

    @param argv list of string arguments for this method
    */
   public static void main(String[] argv)
   {
      Class klasses[] =
      {
         Genre.class, Author.class,
         Book.class, ISBN.class,
         Publisher.class
      };
      HibernateContext.addClasses(klasses);
      HibernateContext.createSchema();

      //Unmarshall for genre.
      File xmlDocument = new File("src/genre.xml");
      Genre jaxbGenreUnmarshaller = new Genre();
      jaxbGenreUnmarshaller.unMarshall(xmlDocument);

      //Unmarshall for author.
      xmlDocument = new File("src/author.xml");
      Author jaxbAuthorUnmarshaller = new Author();
      jaxbAuthorUnmarshaller.unMarshall(xmlDocument);

      //Unmarshall for publisher.
      xmlDocument = new File("src/publisher.xml");
      Publisher jaxbPubUnmarshaller = new Publisher();
      jaxbPubUnmarshaller.unMarshall(xmlDocument);

      //Unmarshall for book and isbn.
      xmlDocument = new File("src/book.xml");
      File isbnXML = new File("src/isbn.xml");
      Book jaxbBookUnmarshaller = new Book();
      jaxbBookUnmarshaller.unMarshall(xmlDocument, isbnXML);
   }
}
