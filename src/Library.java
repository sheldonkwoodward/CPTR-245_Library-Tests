import java.util.ArrayList;

public class Library {
    private final int A_NUM = 7; // number of attributes for each books
    public ArrayList<String[]> books; // books list

    Library() {
        books = new ArrayList<>();
    }
    void addBook(String T, String A, String G, String Y, String P){
        books.add(new String[A_NUM]);
        String[] book = books.get(books.size() - 1);

        book[0] = String.valueOf(books.size() - 1);
        book[1] = "In";
        book[2] = T;
        book[3] = A;
        book[4] = G;
        book[5] = Y;
        book[6] = P;
    }

}
