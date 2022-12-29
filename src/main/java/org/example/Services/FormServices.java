package org.example.Services;

import java.util.ArrayList;
import java.util.List;

public class FormServices {

    public static List<Long> parseIdsFromForm(String s){
        String[] strings = s.split(" ");
        List<Long> ids = new ArrayList<>();
        if(strings.length <=1){
            return ids;
        }
        for(String id: strings){
            ids.add(Long.parseLong(id));
        }
        return ids;
    }
}
