package ru.MrCrashLab.MoneyCheck.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.MrCrashLab.MoneyCheck.Services.GifService;
import ru.MrCrashLab.MoneyCheck.Services.MoneyService;

/*
 * Контроллер
 * */
@RestController
@RequestMapping("/money-check-service")
public class MoneyCheckController {
    @Autowired
    GifService gifService;
    @Autowired
    MoneyService moneyService;
    @Value("${gyphi.api.search.rich}")
    private String richKey;
    @Value("${gyphi.api.search.broke}")
    private String brokeKey;
    @Value("${gyphi.api.search.nothing}")
    private String nothingKey;
    double diffCurrency = 0;
    private String gifUrlTag = "<img src=%s/>";

    /*
     * Endpoint для отображения гифки согласно росту/падению валюты
     * */
    @GetMapping("/currency")
    public String getCurrency(@RequestParam(name = "desc") String desc) {
        diffCurrency = moneyService.getMoney(desc);
        if (diffCurrency > 0) {
            return String.format(gifUrlTag, gifService.getGif(richKey));
        } else if (diffCurrency < 0) {
            return String.format(gifUrlTag, gifService.getGif(brokeKey));
        } else {
            return String.format(gifUrlTag, gifService.getGif(nothingKey));
        }
    }
}
