package ru.MrCrashLab.MoneyCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"ru.MrCrashLab.MoneyCheck.Controllers", "ru.MrCrashLab.MoneyCheck.Client"})
@SpringBootApplication
public class MoneyCheckApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoneyCheckApplication.class, args);
	}

}
