package ru.MrCrashLab.MoneyCheck.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.MrCrashLab.MoneyCheck.Client.GifClient;
import ru.MrCrashLab.MoneyCheck.JsonParsers.GifParser;

/*
 * Сервис для работы с гиф
 * */
@Service
public class GifService {
    @Autowired
    private GifClient gifClient;
    @Autowired
    private GifParser gifParser;
    @Value("${gyphi.api.key}")
    private String appId;
    private int countOffset;
    private final int limit = 1;
    private int totalCount = 0;
    private String gifUrl = null;

    /*
     * Метод получения гифки по поисковому имени
     * */
    public String getGif(String searchName) {
        JsonNode node = gifClient.getGifRequest(appId, searchName, countOffset, limit);
        totalCount = gifParser.getTotalCount(node);
        if (totalCount <= 0)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gif not found");
        countOffset = (int) (Math.random() * totalCount) % 4999;
        gifUrl = gifParser.getGifUrl(node);
        if (gifUrl == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gif API not found");
        return gifUrl;
    }
}
