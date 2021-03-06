import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


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
        // books to add to database
        String[] bookArr0 = {"0", "In", "Moby-Dick", "Melville, Herman", "Adventure", "1851", "1200"};
        String[] bookArr1 = {"1", "In", "If You Give a Mouse a Cookie", "Numeroff, Laura", "Children", "1985", "20"};
        String[] bookArr2 = {"2", "In", "Harry Potter and the Chamber of Secrets", "ARowling, J. K.", "Fantasy", "1998", "800"};

        // adding books
        lib.addBook(bookArr0[2], bookArr0[3], bookArr0[4], bookArr0[5], bookArr0[6]);
        lib.addBook(bookArr1[2], bookArr1[3], bookArr1[4], bookArr1[5], bookArr1[6]);
        lib.addBook(bookArr2[2], bookArr2[3], bookArr2[4], bookArr2[5], bookArr2[6]);

        // build expected query results
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        ArrayList<String> queryBook0 = new ArrayList<>();
        ArrayList<String> queryBook1 = new ArrayList<>();
        ArrayList<String> queryBook2 = new ArrayList<>();
        queryBook0.add(bookArr2[2]);
        queryBook0.add(bookArr2[3]);
        queryBook1.add(bookArr1[2]);
        queryBook1.add(bookArr1[3]);
        queryBook2.add(bookArr0[2]);
        queryBook2.add(bookArr0[3]);
        expected.add(queryBook0);
        expected.add(queryBook1);
        expected.add(queryBook2);

        // query
        ArrayList<ArrayList<String>> actual = lib.queryBook("SELECT title, author ORDER title");

        // assert equals
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test (expected = BadQueryException.class)
    public void queryOutOfOrderTest() {
        lib.queryBook("ORDER title SELECT title, author");
    }

    @Test (expected = BadQueryException.class)
    public void queryBadSelectorTest() {
        lib.queryBook("SELECT title, author ORDERIFY title");
    }

    @Test (expected = BadQueryException.class)
    public void queryBadParameterTest() {
        lib.queryBook("SELECT thetitle, author ORDER title");
    }

    @Test (expected = BadQueryException.class)
    public void queryMissingParameterTest() {
        lib.queryBook("SELECT title, author ORDER");
    }

    @Test public void removeBookTest() {

        String[] bookArr0 = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "1851", "1200"};
        String[] bookArr1 = {"1", "In", "If You Give a Mouse a Cookie", "Laura Numeroff", "Children", "1985", "20"};

        // adding books in order to test removeBook() method
        lib.addBook(bookArr0[2], bookArr0[3], bookArr0[4], bookArr0[5], bookArr0[6]);
        lib.addBook(bookArr1[2], bookArr1[3], bookArr1[4], bookArr1[5], bookArr1[6]);

        lib.removeBook(bookArr0[0]);
        assert(lib.books.size() == 1);
    }

    @Test public void IdAssignmentAfterRemoveTest() {
        String[] bookArr0 = {"0", "In", "Moby-Dick", "Herman Melville", "Adventure", "1851", "1200"};
        String[] bookArr1 = {"1", "In", "If You Give a Mouse a Cookie", "Laura Numeroff", "Children", "1985", "20"};

        // adding books in order to test removeBook() method
        lib.addBook(bookArr0[2], bookArr0[3], bookArr0[4], bookArr0[5], bookArr0[6]);
        lib.addBook(bookArr1[2], bookArr1[3], bookArr1[4], bookArr1[5], bookArr1[6]);

        bookArr1[0] = "0";
        lib.removeBook(bookArr0[0]);

        assert(lib.books.get(0)[0].equals("0"));
    }
}
