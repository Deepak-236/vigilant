package com.demo.websearch.searchoverweb;

import com.demo.websearch.searchoverweb.dto.SearchResults;
import com.demo.websearch.searchoverweb.service.StackoverflowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

@SpringBootApplication
public class SearchOverWebApplication implements CommandLineRunner {

	@Autowired
	private StackoverflowRequestService requestService;

	public static void main(String[] args) {
		SpringApplication.run(SearchOverWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		readInputFromConsole();
	}

	private void readInputFromConsole() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter your search query:");

		String searchString = reader.readLine();
		if(StringUtils.isEmpty(searchString.trim())) {
			System.out.println("Please enter a valid input");
		} else {
			SearchResults searchResults;
			try {
				searchResults = requestService.getStackoverflowSearchResults(searchString);
			} catch (UnknownHostException e) {
				searchResults = null;
				System.out.println("Unable to access host. Please check your internet connection");
			}

			if(searchResults != null && searchResults.getSearchResultDTOList() != null) {
				if (searchResults.getSearchResultDTOList().size() == 0) {
					System.out.println("No results found for your search query");
				} else {
					searchResults.getSearchResultDTOList().forEach(resultDTO -> {
						System.out.println("Title: "+resultDTO.getTitle());
						System.out.println("URL: "+resultDTO.getUrl());
						System.out.println("Author Dispay Name: "+resultDTO.getOwner().get("display_name"));
					});
				}
			}
		}

		readInputFromConsole();
	}
}
