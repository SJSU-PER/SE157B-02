package JAXB.unmarshallers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import jaxb.generated.book.BookRoot;
import jaxb.generated.book.BookType;
import jaxb.generated.isbn.IsbnRoot;
import jaxb.generated.isbn.IsbnType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "book")
public class Book
{
   private long id;
   private String title;
   private List<Author> authors = new ArrayList<>();
   private List<Genre> genres = new ArrayList<>();
   private ISBN isbn;
   private Publisher publisher;
   private String publishedDate;

   public Book()
   {
   }

   public Book(String title, String publishedDate, ISBN isbn)
   {
      this.title = title;
      this.isbn = isbn;
      this.publishedDate = publishedDate;
   }

   @ManyToMany
   @JoinTable(name="Book_Author",
               joinColumns={@JoinColumn(name="book_id")},
               inverseJoinColumns={@JoinColumn(name="author_id")})
   public List<Author> getAuthors()
   {
      return authors;
   }
   public void setAuthors(List<Author> authors)
   {
      this.authors = authors;
   }

   @ManyToMany
   @JoinTable(name="Book_Genre",
               joinColumns={@JoinColumn(name="book_id")},
               inverseJoinColumns={@JoinColumn(name="genre_id")})
   public List<Genre> getGenres()
   {
      return genres;
   }

   public void setGenres(List<Genre> genres)
   {
      this.genres = genres;
   }

   @Id
   @GeneratedValue
   @Column(name="book_id")
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @OneToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
   @JoinColumn(name="isbn_id")
   public ISBN getIsbn()
   {
      return isbn;
   }
   public void setIsbn(ISBN isbn)
   {
      this.isbn = isbn;
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



   @Column(name="title")
   public String getTitle()
   {
      return title;
   }
   public void setTitle(String title)
   {
      this.title = title;
   }


   @ManyToOne
   @JoinColumn(name="pub_id")
   public Publisher getPublisher()
   {
      return publisher;
   }
   public void setPublisher(Publisher publisher)
   {
      this.publisher = publisher;
   }

   public void unMarshall(File bookXML, File isbnXML)
   {

      try
      {
         JAXBContext jaxbContext =
                 JAXBContext.newInstance("jaxb.generated.book");
         Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();

         JAXBElement<BookRoot> bookElement =
                 (JAXBElement<BookRoot>) unMarshaller.unmarshal(bookXML);
         BookRoot book = bookElement.getValue();
         List<BookType> bookList = book.getBookInfo();

         //for ISBN
         JAXBContext jaxbContext2 =
                 JAXBContext.newInstance("jaxb.generated.isbn");
         Unmarshaller unMarshaller2 = jaxbContext2.createUnmarshaller();

         JAXBElement<IsbnRoot> isbnElement =
                 (JAXBElement<IsbnRoot>) unMarshaller2.unmarshal(isbnXML);
         IsbnRoot isbn = isbnElement.getValue();
         List<IsbnType> isbnList = isbn.getIsbnInfo();

         Session session = HibernateContext.getSession();

         Transaction tx = session.beginTransaction();
         {
            for (BookType books : bookList)
            {
               for(IsbnType isbnNum : isbnList)
               {
                  if(books.getIsbnId() == isbnNum.getId())
                  {
                     session.save(new Book(books.getTitle(), books.getPubDate(),
                             new ISBN(isbnNum.getIsbnNumber())));
                     break;
                  }
               }
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
