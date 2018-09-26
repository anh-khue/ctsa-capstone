package io.ctsa.basedatasetservice.client;

import io.ctsa.basedatasetservice.model.Keyword;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class ElasticsearchWebClient {

    @Value("${ctsa.elasticsearch.base-url}")
    private String baseUrl;

    @Value("${ctsa.elasticsearch.keyword-uri}")
    private String keywordUri;

    @Bean
    WebClient elasticsearchClient() {
        return WebClient.builder()
                        .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .baseUrl(baseUrl)
                        .build();
    }

    public void pushKeywords(List<Keyword> keywords) {
        keywords.forEach(keyword -> pushKeyword(keyword.getId(), keyword.getWord()));
    }

    private void pushKeyword(int id, String keyword) {
        String requestBody = createPushRequestBody(id, keyword);

        System.out.println(keywordUri + "/" + id);

        String response = elasticsearchClient().post()
                                               .uri(keywordUri + "/" + id)
                                               .body(BodyInserters.fromObject(requestBody))
                                               .accept(MediaType.APPLICATION_JSON)
                                               .retrieve()
                                               .bodyToMono(String.class)
                                               .block();
        System.out.println(response);
    }

    private String createPushRequestBody(int id, String keyword) {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("id", id);
            requestBody.put("keyword", keyword);

            return requestBody.toString();
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }
}
