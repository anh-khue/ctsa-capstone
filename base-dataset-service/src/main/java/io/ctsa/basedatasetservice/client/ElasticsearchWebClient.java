package io.ctsa.basedatasetservice.client;

import io.ctsa.basedatasetservice.model.Keyword;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
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
        keywords.forEach(keyword -> {
            pushKeyword(keyword.getId(), keyword.getWord());
        });
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

    public List<String> extractKeywords(String content) {
        String requestBody = createSearchRequestBody(content);

        String response = elasticsearchClient().post()
                                               .uri(keywordUri + "/_search?size=50")
                                               .body(BodyInserters.fromObject(requestBody))
                                               .accept(MediaType.APPLICATION_JSON)
                                               .retrieve()
                                               .bodyToMono(String.class)
                                               .block();

        return retrieveHitKeywords(response);
    }

    private String createSearchRequestBody(String message) {
        try {
            JSONObject messageJsonObject = new JSONObject();
            messageJsonObject.put("keyword", message);

            JSONObject matchJsonObject = new JSONObject();
            matchJsonObject.put("match", messageJsonObject);

            JSONObject requestBody = new JSONObject();
            requestBody.put("query", matchJsonObject);

            return requestBody.toString();
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    private List<String> retrieveHitKeywords(String response) {
        try {
            JSONArray hitsArray = new JSONObject(response)
                    .getJSONObject("hits")
                    .getJSONArray("hits");

            List<String> hitKeywords = new ArrayList<>();

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject hit = hitsArray.getJSONObject(i);
                hitKeywords.add(hit.getJSONObject("_source")
                                   .getString("keyword"));
            }

            return hitKeywords;
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }
}
