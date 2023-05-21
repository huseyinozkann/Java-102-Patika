import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Book> books = new TreeSet<>();

        books.add(new Book("Vadideki Zambak",366,"Balzak","1835"));
        books.add(new Book("Suç ve Ceza",384,"Dostoyevski","1866"));
        books.add(new Book("Simyacı",166,"Paulo Coelho","1988"));
        books.add(new Book("Kürk Mantolu Madonna", 177, "Sabahattin Ali","1943"));
        books.add(new Book("Toprak Ana",136,"Cengiz Aytmatov","1963"));


        System.out.println("###################### Kitapların ismine göre sıralı liste ######################");

        for (Book b: books){
            System.out.println(b);
        }

        Set<Book> sortedByPageCount = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return  o1.getPageCount() - o2.getPageCount();
            }
        });

        sortedByPageCount.addAll(books);

        System.out.println("\n###################### Kitapların sayfa sayısına göre sıralı liste ######################");

        for (Book b: sortedByPageCount){
            System.out.println(b);
        }


    }
}