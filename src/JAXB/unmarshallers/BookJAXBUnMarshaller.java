package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import jaxb.generated.book.BookRoot;
import jaxb.generated.book.BookType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "book")
@AssociationOverrides({
        @AssociationOverride(name = "BookAuthor.book_id",
            joinColumns = @JoinColumn(name = "id")),
})
public class BookJAXBUnMarshaller
{
   private long id;
   private String title;
   private String publishedDate;
   private long isbn_id;
   private long pub_id;

   public BookJAXBUnMarshaller(long id, String publishedDate, String title, long isbn_id, long pub_id)
   {
      this.id = id;
      this.title = title;
      this.publishedDate = publishedDate;
      this.isbn_id = isbn_id;
      this.pub_id = pub_id;
   }

   public BookJAXBUnMarshaller()
   {
   }

   @Id
   @Column(name="book_id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Column(name="title")
   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   @Column(name="pub_date")
   public String getPublishedDate()
   {
      return publishedDate;
   }

   public void setPublishedDate(String publishedDate)
   {
      this.publishedDate = publishedDate;
   }

   @Column(name="isbn_id")
   public long getIsbn_id()
   {
      return isbn_id;
   }

   public void setIsbn_id(long isbn_id)
   {
      this.isbn_id = isbn_id;
   }

   @Column(name="pub_id")
   public long getPub_id()
   {
      return pub_id;
   }

   public void setPub_id(long pub_id)
   {
      this.pub_id = pub_id;
   }

   public void unMarshall(File xmlDocument)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<BookRoot> bookElement =
                 (JAXBElement<BookRoot>) unMarshaller.unmarshal(xmlDocument);
         BookRoot book = bookElement.getValue();
         List<BookType> bookList = book.getBookInfo();

         Session session = HibernateContext.getSession();

         Transaction tx = session.beginTransaction();
         {
            for (BookType books : bookList)
            {
               session.save(new BookJAXBUnMarshaller(books.getBookId(),
                       books.getPubDate(), books.getTitle(), books.getIsbnId(),
                       books.getPubId()));
            }
         }
         tx.commit();
         session.close();
         System.out.println("Book table loaded.");

      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }
}
