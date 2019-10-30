package com.example.api.utils;

import com.example.api.domains.Album;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlbumTitleSorter {

    public static void sortByTitle(List<Album> albums, String sortOption){
        Collections.sort(albums, new Comparator<Album>() {
            @Override
            public int compare(Album o1, Album o2) {
                return selectSortOrder(o1, o2, sortOption);
            }
        });
    }

    private static int selectSortOrder(Album o1, Album o2, String sortOption) {
        if(sortOption.equals("artist")){
            return o1.getArtistName().compareTo(o2.getArtistName());
        }else if(sortOption.equals("kind")){
            return o1.getKind().compareTo(o2.getKind());
        }
        return o1.getCollectionName().compareTo(o2.getCollectionName());
    }

}
