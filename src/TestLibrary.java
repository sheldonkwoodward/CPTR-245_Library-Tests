import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestLibrary {
    private Library lib;
    @Before public void setUp() {
        lib = new Library();
    }

    @Test public void addBookTest() {
        String[] bookArr = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "1851", "1200"};
        lib.addBook(bookArr[2], bookArr[3], bookArr[4], bookArr[5], bookArr[6]);
        Assert.assertArrayEquals(bookArr, lib.books.get(0));
    }

    @Test public void addMultBookTest() {
        // books to add to database
        String[] bookArr0 = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "1851", "1200"};
        String[] bookArr1 = {"1", "In", "If You Give a Mouse a Cookie", "Laura Numeroff", "Children", "1985", "20"};
        String[] bookArr2 = {"2", "In", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", "Fantasy", "1998", "800"};

        // adding books
        lib.addBook(bookArr0[2], bookArr0[3], bookArr0[4], bookArr0[5], bookArr0[6]);
        lib.addBook(bookArr1[2], bookArr1[3], bookArr1[4], bookArr1[5], bookArr1[6]);
        lib.addBook(bookArr2[2], bookArr2[3], bookArr2[4], bookArr2[5], bookArr2[6]);

        // check proper adding to database
        Assert.assertArrayEquals(bookArr0, lib.books.get(0));
        Assert.assertArrayEquals(bookArr1, lib.books.get(1));
        Assert.assertArrayEquals(bookArr2, lib.books.get(2));
    }

    @Test (expected = StringNotNumberException.class)
    public void addBookBadYearTest() {
        String[] bookArr = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "Eighteen Fifty One", "1200"};
        lib.addBook(bookArr[2], bookArr[3], bookArr[4], bookArr[5], bookArr[6]);
    }

    @Test (expected = StringNotNumberException.class)
    public void addBookBadPageTest() {
        String[] bookArr = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "1851", "One Thousand Two Hundred"};
        lib.addBook(bookArr[2], bookArr[3], bookArr[4], bookArr[5], bookArr[6]);
    }

    @Test public void queryBookTest() {

    }

    @Test public void editBookTest() {

    }

    @Test public void removeBookTest() {

    }
}
