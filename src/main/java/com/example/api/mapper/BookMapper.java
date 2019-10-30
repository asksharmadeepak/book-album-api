package com.example.api.mapper;

import com.example.api.domains.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    public List<Book> mapBooks(String books) {
        JSONParser parse = new JSONParser();
        List<Book> bookList = new ArrayList<>();
        try {
            JSONObject jobj = (JSONObject) parse.parse(books);
            JSONArray results = (JSONArray) jobj.get("items");
            for (int i = 0; i < results.size(); i++) {
                Book bookObj = new Book();
                JSONObject salesInfos = (JSONObject) results.get(i);
                JSONObject volumeInfo = (JSONObject) salesInfos.get("volumeInfo");
                bookObj.setTitle(volumeInfo.get("title").toString());
                bookObj.setKind(salesInfos.get("kind").toString());
                bookObj.setAuthors((JSONArray) volumeInfo.get("authors"));
                bookList.add(bookObj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
