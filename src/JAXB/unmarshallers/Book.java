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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

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
//         session.close();

         session.update(this);
         //Initiate all the new books
        Book bagOfBones = find("Bag of Bones");
        Book lostHorizon = find("Lost Horizon");
        Book bambi = find("Bambi: A Life in the Woods");
        Book databaseSystems = find("Database Systems, the Complete Book");
        Book mathBook = find("Algebra: Tools for a Changing World");
        Book under = find("Under the Dome");
        Book murder = find("Murder at School");
        Book shining = find("The Shining");
        Book salem = find("Salem's Lot");

        //Find publishers
        Publisher pb = Publisher.find("Pocket Books");
        Publisher pearson = Publisher.find("Pearson");
        Publisher prentice = Publisher.find("Prentice Hall");
        Publisher gb = Publisher.find("Gallery Books");
        Publisher dales = Publisher.find("Dales Large Print Books");
        Publisher randomHouse = Publisher.find("Random House Digital, Inc");

        //set the publishers of a book.
        bagOfBones.setPublisher(pb);
        lostHorizon.setPublisher(pb);
        bambi.setPublisher(pb);
        databaseSystems.setPublisher(pearson);
        mathBook.setPublisher(prentice);
        under.setPublisher(gb);
        murder.setPublisher(dales);
        shining.setPublisher(gb);
        salem.setPublisher(randomHouse);

        //Find the authors.
        Author king = Author.find("Stephen", "King");
        Author james = Author.find("James", "Hilton");
        Author felix = Author.find("Felix", "Salten");
        Author hector = Author.find("Hector", "Garcia-Milina");
        Author jeff = Author.find("Jeffry", "Ullman");
        Author prent = Author.find("Prentice", "Hall");
        Author jenn = Author.find("Jennifer", "Widom");

        //add authors to book.
        bagOfBones.getAuthors().add(king);
        lostHorizon.getAuthors().add(james);
        bambi.getAuthors().add(felix);
        databaseSystems.getAuthors().add(jenn);
        databaseSystems.getAuthors().add(hector);
        databaseSystems.getAuthors().add(jeff);
        mathBook.getAuthors().add(prent);
        under.getAuthors().add(king);
        murder.getAuthors().add(james);
        shining.getAuthors().add(king);
        salem.getAuthors().add(king);

        //Find the genres
        Genre mystery = Genre.find("Mystery");
        Genre horror = Genre.find("Horror");
        Genre education = Genre.find("Education");
        Genre math = Genre.find("Math");
        Genre database = Genre.find("Database");
        Genre thriller = Genre.find("Thriller");
        Genre childBook = Genre.find("Child Book");
        Genre adventure = Genre.find("Adventure");

        //add genres to books.
        bagOfBones.getGenres().add(mystery);
        bagOfBones.getGenres().add(horror);
        bagOfBones.getGenres().add(thriller);
        lostHorizon.getGenres().add(adventure);
        bambi.getGenres().add(childBook);
        bambi.getGenres().add(adventure);
        databaseSystems.getGenres().add(database);
        mathBook.getGenres().add(math);
        mathBook.getGenres().add(education);
        under.getGenres().add(horror);
        under.getGenres().add(thriller);
        murder.getGenres().add(horror);
        murder.getGenres().add(mystery);
        shining.getGenres().add(horror);
        shining.getGenres().add(thriller);
        salem.getGenres().add(horror);

//        Session session2 = HibernateContext.getSession();
        // Load the Student table in a transaction.
        Transaction tx2 = session.beginTransaction();
        {
            session.save(bagOfBones);
            session.save(lostHorizon);
            session.save(bambi);
            session.save(databaseSystems);
            session.save(mathBook);
            session.save(under);
            session.save(murder);
            session.save(shining);
            session.save(salem);
        }
        tx2.commit();
        session.close();

         System.out.println("Book table loaded.");
      }
      catch (JAXBException ex)
      {
         ex.printStackTrace();
      }
   }

   /**
     * Fetch the book with a matching title.
     * @param title the title to match.
     * @return the book or null.
     */
    public static Book find(String title)
    {
       // Query by example.
        Book prototype = new Book();
        prototype.setTitle(title);
        Example example = Example.create(prototype);

        Session session = HibernateContext.getSession();
        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(example);

        Book book = (Book) criteria.uniqueResult();

        session.close();
        return book;
    }
}
