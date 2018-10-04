package io.ctsa.elasticsearchservice.communicator.elasticsearch;

import io.ctsa.elasticsearchservice.communicator.Communicator;
import io.ctsa.elasticsearchservice.model.Keyword;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticsearchWebClient {

    @Value("${ctsa.elasticsearch.keyword-uri}")
    private String keywordUri;

    private final WebClient webClient;

    public ElasticsearchWebClient(Communicator elasticsearchCommunicator) {
        webClient = elasticsearchCommunicator.client();
    }

    public Mono<String> extractKeywords(String content) {
        String requestBody = createRequestBody(cleanContent(content));

        return webClient.post()
                        .uri(keywordUri + "/_search?size=50")
                        .body(BodyInserters.fromObject(requestBody))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class);
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

    public Flux<String> retrieveHitKeywords(String response) {
        try {
            String content = new StringBuilder(response).deleteCharAt(0)
                                                        .deleteCharAt(response.length() - 2)
                                                        .toString()
                                                        .replace("\\", "")
                                                        .trim();
            JSONArray hitsArray = new JSONObject(content)
                    .getJSONObject("hits")
                    .getJSONArray("hits");

            List<String> hitKeywords = new ArrayList<>();

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject hit = hitsArray.getJSONObject(i);
                hitKeywords.add(hit.getJSONObject("_source")
                                   .getString("keyword"));
            }

            return Flux.fromStream(hitKeywords.stream());
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

//    public List<String> retrieveHitKeywords(String response) {
//        try {
//            JSONArray hitsArray = new JSONObject(response)
//                    .getJSONObject("hits")
//                    .getJSONArray("hits");
//
//            List<String> hitKeywords = new ArrayList<>();
//
//            for (int i = 0; i < hitsArray.length(); i++) {
//                JSONObject hit = hitsArray.getJSONObject(i);
//                hitKeywords.add(hit.getJSONObject("_source")
//                                   .getString("keyword"));
//            }
//
//            return hitKeywords;
//        } catch (JSONException e) {
//            throw new RuntimeException();
//        }
//    }

    private String cleanContent(String content) {
        return content.replaceAll("\\s", " ")
                      .replaceAll(",", " ")
                      .replaceAll("…", " ")
                      .replaceAll("\\.\\.\\.", " ")
                      .replaceAll("\\(", " ")
                      .replaceAll("\\)", " ")
                      .toLowerCase();
    }

    public void pushKeywords(List<Keyword> keywords) {
        keywords.forEach(keyword -> pushKeyword(keyword.getId(), keyword.getWord()));
    }

    private void pushKeyword(int id, String keyword) {
        String requestBody = createPushRequestBody(id, keyword);

        String response = webClient.post()
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
