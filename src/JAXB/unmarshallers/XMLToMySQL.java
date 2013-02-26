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
         Genre.class, Author.class,
         Book.class, ISBN.class,
         Publisher.class
      };
      HibernateContext.addClasses(klasses);
      HibernateContext.createSchema();

      File xmlDocument = new File("src/genre.xml");
      Genre jaxbGenreUnmarshaller = new Genre();
      jaxbGenreUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/author.xml");
      Author jaxbAuthorUnmarshaller = new Author();
      jaxbAuthorUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/publisher.xml");
      Publisher jaxbPubUnmarshaller = new Publisher();
      jaxbPubUnmarshaller.unMarshall(xmlDocument);

      xmlDocument = new File("src/book.xml");
      File isbnXML = new File("src/isbn.xml");
      Book jaxbBookUnmarshaller = new Book();
      jaxbBookUnmarshaller.unMarshall(xmlDocument, isbnXML);
   }

}
