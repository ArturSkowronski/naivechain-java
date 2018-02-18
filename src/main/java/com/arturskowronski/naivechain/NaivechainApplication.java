package com.arturskowronski.naivechain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@Controller
public class NaivechainApplication {

	@RequestMapping("/")
	@ResponseBody
	List home() {
		return Collections.emptyList();
	}

	public static void main(String[] args) {
		SpringApplication.run(NaivechainApplication.class, args);
	}
}
