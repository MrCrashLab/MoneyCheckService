package ru.MrCrashLab.MoneyCheck.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MoneyCheckControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void pageNotFounds() throws Exception {
		this.mockMvc.perform(get("/money-check-service"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	void noParamsPassed() throws Exception{
		this.mockMvc.perform(get("/money-check-service/currency"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	void contextLoads() throws Exception{
		this.mockMvc.perform(get("/money-check-service/currency").param("desc", "RUB"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<img src=")));
	}

	@Test
	void wrongDesc() throws Exception{
		this.mockMvc.perform(get("/money-check-service/currency").param("desc", "RUBdsdsdsd"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

}
