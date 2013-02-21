/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JAXB.unmarshallers;

import java.io.File;

/**

 @author Erni
 */
public class XMLToMySQL
{
   public static void main(String[] argv)
   {
      Class klasses[] =
      {
         GenreJAXBUnMarshaller.class, AuthorJAXBUnMarshaller.class,
         BookJAXBUnMarshaller.class, IsbnJAXBUnMarshaller.class,
         PublisherJAXBUnMarshaller.class, BookAuthorJAXBUnMarshaller.class
      };
      HibernateContext.addClasses(klasses);
      HibernateContext.createSchema();

      File xmlDocument = new File("src/genre.xml");
      GenreJAXBUnMarshaller jaxbGenreUnmarshaller = new GenreJAXBUnMarshaller();
      jaxbGenreUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/author.xml");
      AuthorJAXBUnMarshaller jaxbAuthorUnmarshaller = new AuthorJAXBUnMarshaller();
      jaxbAuthorUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/book.xml");
      BookJAXBUnMarshaller jaxbBookUnmarshaller = new BookJAXBUnMarshaller();
      jaxbBookUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/isbn.xml");
      IsbnJAXBUnMarshaller jaxbIsbnUnmarshaller = new IsbnJAXBUnMarshaller();
      jaxbIsbnUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/publisher.xml");
      PublisherJAXBUnMarshaller jaxbPubUnmarshaller = new PublisherJAXBUnMarshaller();
      jaxbPubUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/book_author.xml");
      BookAuthorJAXBUnMarshaller jaxbBookAuthUnmarshaller = new BookAuthorJAXBUnMarshaller();
      jaxbBookAuthUnmarshaller.unMarshall(xmlDocument);
   }

}
