package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import jaxb.generated.book_author.BookAuthorRoot;
import jaxb.generated.book_author.BookAuthorType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "book_author")
public class BookAuthorJAXBUnMarshaller
{
   private long author_id;
   private long book_id;

   public BookAuthorJAXBUnMarshaller()
   {
   }

   public BookAuthorJAXBUnMarshaller(long author_id, long book_id)
   {
      this.author_id = author_id;
      this.book_id = book_id;
   }

   @Id
   @Column(name="author_id")
   public long getAuthor_id()
   {
      return author_id;
   }

   public void setAuthor_id(long author_id)
   {
      this.author_id = author_id;
   }

   @Id
   @Column(name="book_id")
   public long getBook_id()
   {
      return book_id;
   }

   public void setBook_id(long book_id)
   {
      this.book_id = book_id;
   }

   public void unMarshall(File xmlDocument)
   {
      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book_author");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<BookAuthorRoot> bookAuthorElement =
                 (JAXBElement<BookAuthorRoot>) unMarshaller.unmarshal(xmlDocument);
         BookAuthorRoot bookAuthor = bookAuthorElement.getValue();
         List<BookAuthorType> bookAuthorList = bookAuthor.getBookAuthorInfo();

         Session session = HibernateContext.getSession();
         Transaction tx = session.beginTransaction();
         {
            for (BookAuthorType bookAuth : bookAuthorList)
            {
               session.save(new BookAuthorJAXBUnMarshaller(bookAuth.getAuthorId(), bookAuth.getBookId()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("Book author table loaded.");
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }
}
