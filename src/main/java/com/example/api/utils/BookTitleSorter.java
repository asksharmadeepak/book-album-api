package com.example.api.utils;

import com.example.api.domains.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookTitleSorter {

    public static void sortByTitle(List<Book> books, String sortOption) {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return selectSortOrder(o1, o2, sortOption);
            }
        });
    }

    private static int selectSortOrder(Book o1, Book o2, String sortOption) {
        if (sortOption.equals("kind")) {
            return o1.getKind().compareTo(o2.getKind());
        } else {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

}
