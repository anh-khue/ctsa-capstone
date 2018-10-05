package io.anhkhue.ctsa.myworkscraper.scraper.collector;

import io.anhkhue.ctsa.myworkscraper.model.Position;
import io.anhkhue.ctsa.myworkscraper.model.Skill;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class CollectedDataModel {

    private String link;
    private LocalDate postedDate;
    private Position position;
    private Set<Skill> skills;
//    private int duration;
    private LocalDate approvalDate;
    private int number;
}
