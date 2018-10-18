package io.ctsa.elasticsearchservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword {

    private int id;
    @NonNull
    private String word;
    private boolean isSynonym;
    private Integer mainKeywordId;
    private Integer positionId;
    private Integer skillId;
    private boolean pushedToElasticsearch;
}