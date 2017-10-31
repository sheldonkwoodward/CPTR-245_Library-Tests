import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestLibrary {
    private Library lib;
    @Before public void setUp() {
        lib = new Library();

    }


    @Test public void addBookTest() {
        String[] bookArr = {"0", "In", "Moby Dick", "That Man", "Adventure", "1969", "200"};
        lib.addBook(bookArr[2], bookArr[3], bookArr[4], bookArr[5], bookArr[6]);
        Assert.assertArrayEquals(bookArr, lib.books.get(0));
    }

    @Test public void queryBookTest() {

    }

    @Test public void editBookTest() {

    }

    @Test public void removeBookTest() {

    }
}
