package ru.MrCrashLab.MoneyCheck.Client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Интерфейс для обращения к стороннему API, а именно Gyphi API
 *  */
@FeignClient(url = "${gyphi.api.path}", name = "${gyphi.feign.client.name}")
public interface GifClient {

    /*
     * Метод запроса получения гифки по поисковому запросу
     * */
    @GetMapping("${gyphi.api.path.search}")
    public JsonNode getGifRequest(@RequestParam(name = "api_key") String appId,
                                  @RequestParam(name = "q") String searchName,
                                  @RequestParam(name = "offset") int offset,
                                  @RequestParam(name = "limit") int limit);

}
