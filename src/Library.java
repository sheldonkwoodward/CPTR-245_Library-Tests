import java.io.IOException;
import java.util.*;

public class Library {
    private final int A_NUM = 7; // number of attributes for each books
    private Map<String, Integer> selects;
    private Map<String, Integer> params;
    ArrayList<String[]> books; // books list

    Library() {
        books = new ArrayList<>();

        // build selects
        selects = new HashMap<String, Integer>();
        selects.put("SELECT", 0);
        selects.put("ORDER", 1);
        selects.put("END", 2);

        // build parameters
        params = new HashMap<String, Integer>();
        params.put("ID", 0);
        params.put("checked", 1);
        params.put("title", 2);
        params.put("author", 3);
        params.put("genre", 4);
        params.put("year", 5);
        params.put("page", 6);
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

    ArrayList<ArrayList<String>> queryBook(String query) {
        // break query into parts
        List<String> parts = new ArrayList<>(Arrays.asList(query.split("\\s+")));
        for(ListIterator<String> iter = parts.listIterator(); iter.hasNext(); ) {
            String element = iter.next();
            if(element.endsWith(",")) {
                parts.set(parts.indexOf(element), element.substring(0, element.length() - 1));

            }
        }
        if(parts.get(parts.size() - 1) != "END") {
            parts.add("END"); // add end to parts parse
        }

        // exception detection
        boolean foundSELECT = false;
        String lastPart = "";
        for(String el : parts) {
            // out of order selectors
            if(el.equals("SELECT")) {
                foundSELECT = true;
            }
            if(el.equals("ORDER") && !foundSELECT) {
                throw new BadQueryException("ORDER before SELECT");
            }
            // bad selectors/parameters
            if(!el.equals(",") && selects.get(el) == null && params.get(el) == null) {
                throw new BadQueryException("Bad selector/parameter");
            }
            // missing parameters
            if(selects.get(lastPart) != null && selects.get(el) != null) {
                throw new BadQueryException("Missing parameter");
            }
            lastPart = el;
        }

        // output of parse
        ArrayList<ArrayList<String>> output = new ArrayList<>();

        // current operation state
        String state = "";

        // main parse loop
        for(ListIterator<String> iter = parts.listIterator(); iter.hasNext(); ) {
            // current element from query string
            String el = iter.next();

            // selectors
            if(el.equals("SELECT")) {
                state = "SELECT";

                // add blank books
                for(int i = 0; i < books.size(); i ++) {
                    output.add(new ArrayList<>());
                }
                continue;
            }
            if(el.equals("CONTAINS")) {
                state = "CONTAINS";
                continue;
            }
            if(el.equals("ORDER")) {
                state = "ORDER";
                continue;
            }
            if(el.equals("ASC")) {
                state += " ASC";
                continue;
            }
            if(el.equals("END")) {
                state = "END";
                continue;
            }

            // params based on selectors
            if(state.equals("SELECT") || state.equals("ORDER")) {
                for(int i = 0; i < output.size(); i++) {
                    output.get(i).add(books.get(i)[params.get(el)]);
                }
            }
            if(state.equals("ORDER")) {
                // sort based on sorting info
                Collections.sort(output, new Comparator<ArrayList<String>>() {
                    @Override
                    public int compare(ArrayList<String> one, ArrayList<String> two) {
                        return one.get(one.size() - 1).compareTo(two.get(two.size() - 1));
                    }
                });

                // remove sorting info added SELECT
                for(int i = 0; i < output.size(); i++) {
                    output.get(i).remove(output.get(i).size() - 1);
                }
            }
            if(state.contains("ASC")) {
                Collections.reverse(output);
            }
        }
        return output;
    }

    void removeBook(String book_Id){
        for (String[] book : books){
            if (book[0].equals(book_Id)){
                books.remove(book);
                break;
            }
        }
        for (String[] book : books){
            if (Integer.parseInt(book[0]) > Integer.parseInt(book_Id)){
                int newID = Integer.parseInt(book[0])-1;
                book[0] = String.valueOf(newID);
            }
            System.out.println(book[0]);
        }
    }
}
