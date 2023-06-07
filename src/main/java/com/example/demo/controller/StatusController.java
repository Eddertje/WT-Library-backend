package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
public class StatusController {
	
		/**
		 * Beschrijving van wat de functie doet
		 * 
		 * @param inputType - input string door gebruiker meegegeven
		 * @return ReturnType - 
		 * 
		 */
		@RequestMapping("status")
		public String status(String s) {
			return "U bent ingelogd";
		}
}
