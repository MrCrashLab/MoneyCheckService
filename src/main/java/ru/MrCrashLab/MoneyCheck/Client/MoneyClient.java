package ru.MrCrashLab.MoneyCheck.Client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Интерфейс для обращения к стороннему API, а именно openexchangerates API
 *  */

@FeignClient(url = "${exchange.api.path}", name = "${exchange.feign.client.name}")
public interface MoneyClient {

    /*
     * Метод запроса для получения сегодняшнего курса обмена валют
     * */
    @GetMapping("${exchange.api.path.latest}")
    public JsonNode getTodayMoneyRequest(@RequestParam(name = "app_id") String appId,
                                         @RequestParam String base);

    /*
     * Метод запроса для получения вчерашнего курса обмена валют
     * */
    @GetMapping("${exchange.api.path.historical}/{yesterdayDate}.json")
    public JsonNode getYesterdayMoneyRequests(@RequestParam(name = "app_id") String appId,
                                              @RequestParam String base,
                                              @PathVariable("yesterdayDate") String Date);
}
