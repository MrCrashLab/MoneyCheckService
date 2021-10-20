package ru.MrCrashLab.MoneyCheck.JsonParsers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/*
 * Json Parser для обработки json, полученных с openexchangerates API
 * */
@Component
public class MoneyParser {
    /*
    * Получение курса обмена валюты desCurrency
    * */
    public double getCurrency(JsonNode node, String desCurrency) {
        if (node.has("rates")) {
            if (node.get("rates").has(desCurrency)) {
                return Double.parseDouble(node.get("rates").get(desCurrency).toString());
            }
        }
        return 0;
    }

    /*
    * Метод для получения всех валют
    * */
    public List<String> getListCurrency(JsonNode node){
        List<String> listCurrency = new ArrayList<>();
        Iterator<String> iteratorFieldName = node.get("rates").fieldNames();
        while(iteratorFieldName.hasNext()) {
            listCurrency.add(iteratorFieldName.next());
        }
        return listCurrency;
    }
}
