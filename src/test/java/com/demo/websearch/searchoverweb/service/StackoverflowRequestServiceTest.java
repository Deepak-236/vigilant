package com.demo.websearch.searchoverweb.service;

import com.demo.websearch.searchoverweb.dto.SearchResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StackoverflowRequestServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private StackoverflowRequestService requestService;

    private static final String response = "{\n" +
            "   \"items\": [\n" +
            "      {\n" +
            "         \"tags\": [\n" +
            "            \"javascript\",\n" +
            "            \"html\",\n" +
            "            \"jquery\",\n" +
            "            \"css\"\n" +
            "         ],\n" +
            "         \"owner\": {\n" +
            "            \"reputation\": 313,\n" +
            "            \"user_id\": 12708740,\n" +
            "            \"user_type\": \"registered\",\n" +
            "            \"profile_image\": \"https://www.gravatar.com/avatar/06dc5328919ec1c1cb8055b8025cb6d7?s=128&d=identicon&r=PG&f=1\",\n" +
            "            \"display_name\": \"psychcoder\",\n" +
            "            \"link\": \"https://stackoverflow.com/users/12708740/psychcoder\"\n" +
            "         },\n" +
            "         \"is_answered\": true,\n" +
            "         \"view_count\": 45,\n" +
            "         \"accepted_answer_id\": 62422099,\n" +
            "         \"answer_count\": 3,\n" +
            "         \"score\": 0,\n" +
            "         \"last_activity_date\": 1592377306,\n" +
            "         \"creation_date\": 1592371010,\n" +
            "         \"question_id\": 62421923,\n" +
            "         \"content_license\": \"CC BY-SA 4.0\",\n" +
            "         \"link\": \"https://stackoverflow.com/questions/62421923/add-element-to-list-using-map-function-javascript\",\n" +
            "         \"title\": \"Add element to list using map function (javascript)\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"tags\": [\n" +
            "            \"python\",\n" +
            "            \"database\",\n" +
            "            \"list\",\n" +
            "            \"dictionary\"\n" +
            "         ],\n" +
            "         \"owner\": {\n" +
            "            \"reputation\": 47,\n" +
            "            \"user_id\": 7544632,\n" +
            "            \"user_type\": \"registered\",\n" +
            "            \"accept_rate\": 0,\n" +
            "            \"profile_image\": \"https://www.gravatar.com/avatar/5d46b581b9639a61b18f7ef7f5d6647f?s=128&d=identicon&r=PG&f=1\",\n" +
            "            \"display_name\": \"Alan Fu\",\n" +
            "            \"link\": \"https://stackoverflow.com/users/7544632/alan-fu\"\n" +
            "         },\n" +
            "         \"is_answered\": true,\n" +
            "         \"view_count\": 48,\n" +
            "         \"answer_count\": 2,\n" +
            "         \"score\": 1,\n" +
            "         \"last_activity_date\": 1587988433,\n" +
            "         \"creation_date\": 1587986174,\n" +
            "         \"question_id\": 61457654,\n" +
            "         \"content_license\": \"CC BY-SA 4.0\",\n" +
            "         \"link\": \"https://stackoverflow.com/questions/61457654/what-is-the-best-way-to-add-element-to-list-of-dict-in-python\",\n" +
            "         \"title\": \"what is the best way to add element to list of dict in python?\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"has_more\": true,\n" +
            "   \"quota_max\": 10000,\n" +
            "   \"quota_remaining\": 9990\n" +
            "}";
    private static final String searchString = "add element to list";

    @Test
    public void testResponseStringParsedAndCorrectSearchResultsReturned() throws IOException {
        final String expectedTitle1 = "Add element to list using map function (javascript)";
        final String expectedURI1 =  "https://stackoverflow.com/questions/62421923/add-element-to-list-using-map-function-javascript";

        final String expectedTitle2 = "what is the best way to add element to list of dict in python?";

        when(httpClientService.callGetApi(any(String.class))).thenReturn(response);

        SearchResults searchResults = requestService.getStackoverflowSearchResults(searchString);

        assertNotNull("searchResults should not be null", searchResults);
        assertEquals("2 SearchResultDTOs expected", searchResults.getSearchResultDTOList().size(), 2);
        assertEquals("dto1 doesnot contain expected title", searchResults.getSearchResultDTOList().get(0).getTitle(), expectedTitle1);
        assertEquals("dto1 doesnot contain expected URI", searchResults.getSearchResultDTOList().get(0).getUrl().toString(), expectedURI1);

        assertEquals("dto2 doesnot contain expected title", searchResults.getSearchResultDTOList().get(1).getTitle(), expectedTitle2);
    }
}
