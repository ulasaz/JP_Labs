import java.util.Objects;

public class Book {
     private final Integer n_ISBN;
     private String title;
     private String author;

     public Book(Integer n_ISBN, String title, String author){
         this.n_ISBN = n_ISBN;
         this.title = title;
         this.author = author;
     }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var book = (Book) obj;
        return Objects.equals(book.n_ISBN, this.n_ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(n_ISBN);
    }

    @Override
    public String toString() {
        return "Name of book: " + title + " by: " + author + " ISBN: " + n_ISBN;
    }
}
