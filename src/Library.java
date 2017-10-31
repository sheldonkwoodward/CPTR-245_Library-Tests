import java.util.ArrayList;

public class Library {
    private final int A_NUM = 7; // number of attributes for each books
    ArrayList<String[]> books; // books list

    Library() {
        books = new ArrayList<>();
    }

    void addBook(String title, String author, String genre, String year, String pageNum) {
        // test year and page number are numeric
        try {
            Double.parseDouble(year);
            Double.parseDouble(pageNum);
        }
        catch(NumberFormatException e) {
            throw new StringNotNumberException("Year or page number is not numeric");
        }

        // add a new book to the array
        books.add(new String[A_NUM]);

        // set book properties
        String[] book = books.get(books.size() - 1);  // reference to sepecific book
        book[0] = String.valueOf(books.size() - 1);  // book ID is the index num
        book[1] = "In";  // books are checked in by default
        book[2] = title;
        book[3] = author;
        book[4] = genre;
        book[5] = year;
        book[6] = pageNum;
    }
}
