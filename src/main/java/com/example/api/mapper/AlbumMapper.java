package com.example.api.mapper;

import com.example.api.domains.Album;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumMapper {

    public List<Album> mapAlbums(String albums) {
        JSONParser parse = new JSONParser();
        List<Album> albumList = new ArrayList<>();
        try {
            JSONObject jobj = (JSONObject) parse.parse(albums);
            JSONArray results = (JSONArray) jobj.get("results");
            for (int i = 0; i < results.size(); i++) {
                Album albumObj = new Album();
                JSONObject location = (JSONObject) results.get(i);
                albumObj.setCollectionName(location.get("collectionName").toString());
                albumObj.setArtistName(location.get("artistName").toString());
                albumObj.setKind(location.get("kind").toString());
                albumList.add(albumObj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return albumList;
    }
}
