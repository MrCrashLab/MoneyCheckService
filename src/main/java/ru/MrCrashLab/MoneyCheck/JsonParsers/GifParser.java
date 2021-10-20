package ru.MrCrashLab.MoneyCheck.JsonParsers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;


/*
 * Json Parser для обработки json, полученных с Gyphi API
 * */
@Component
public class GifParser {

    /*
     * Получение количества гифок, найденных по поисковому запросу
     * */
    public int getTotalCount(JsonNode node) {
        if (node.has("pagination")) {
            if (node.get("pagination").has("total_count")) {
                return Integer.parseInt(node.get("pagination").get("total_count").toString());
            }
        }
        return 0;
    }


    /*
     * Получение url гифки
     * */
    public String getGifUrl(JsonNode node) {
        if (node.has("data")) {
            if (node.get("data").has(0))
                if (node.get("data").get(0).has("images"))
                    if (node.get("data").get(0).get("images").has("original"))
                        if (node.get("data").get(0).get("images").get("original").has("url"))
                            return node.get("data").get(0).get("images").get("original").get("url").toString();
        }
        return null;
    }


}
