package ru.MrCrashLab.MoneyCheck.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.MrCrashLab.MoneyCheck.Client.MoneyClient;
import ru.MrCrashLab.MoneyCheck.JsonParsers.MoneyParser;

import java.time.LocalDate;
import java.util.List;

/*
 * Сервис для работы с крсами обмены валют
 * */
@Service
public class MoneyService {
    @Autowired
    private MoneyClient moneyClient;
    @Autowired
    private MoneyParser moneyParser;
    @Value("${exchange.api.id}")
    private String appId;
    @Value("${exchange.base}")
    private String base;
    private double todayCurrency = 0;
    private double yesterdayCurrency = 0;
    private List<String> listCurrency = null;
    private JsonNode todayMoney;
    private JsonNode yesterdayMoney;

    /*
     * Получение разницы между сегодняшним и вчерашним курсом
     * */
    public double getMoney(String desCurrency) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        todayMoney = moneyClient.getTodayMoneyRequest(appId, base);
        yesterdayMoney = moneyClient.getYesterdayMoneyRequests(appId, base, yesterday.toString());

        if (todayMoney == null || yesterdayMoney == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This currency API not found");

        if (listCurrency == null)
            listCurrency = moneyParser.getListCurrency(todayMoney);

        if (listCurrency.indexOf(desCurrency) == -1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This currency does not exist");

        todayCurrency = moneyParser.getCurrency(todayMoney, desCurrency);
        yesterdayCurrency = moneyParser.getCurrency(yesterdayMoney, desCurrency);
        moneyParser.getListCurrency(todayMoney);
        return todayCurrency - yesterdayCurrency;
    }
}
