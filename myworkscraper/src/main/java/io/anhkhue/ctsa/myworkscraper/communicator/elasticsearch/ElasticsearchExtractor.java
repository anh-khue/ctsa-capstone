package io.anhkhue.ctsa.myworkscraper.communicator.elasticsearch;

import io.anhkhue.ctsa.myworkscraper.communicator.Communicator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticsearchExtractor {

    @Value("/elastic_keywords/ctsa_keywords/_search")
    private String keywordUri;

    private final WebClient elasticsearchWebClient;

    public ElasticsearchExtractor(Communicator elasticsearchCommunicator) {
        elasticsearchWebClient = elasticsearchCommunicator.client();
    }

    public List<String> extractKeywords(String content) {
        String requestBody = createRequestBody(cleanContent(content));

        String response = elasticsearchWebClient.post()
                .uri(keywordUri)
                .body(BodyInserters.fromObject(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return retrieveHitKeywords(response);
    }

    private String createRequestBody(String message) {
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

    private String cleanContent(String content) {
        return content.replaceAll("\\s", " ")
                .replaceAll(",", " ")
                .replaceAll("…", " ")
                .replaceAll("\\.\\.\\.", " ")
                .replaceAll("\\(", " ")
                .replaceAll("\\)", " ")
                .toLowerCase();
    }
}
