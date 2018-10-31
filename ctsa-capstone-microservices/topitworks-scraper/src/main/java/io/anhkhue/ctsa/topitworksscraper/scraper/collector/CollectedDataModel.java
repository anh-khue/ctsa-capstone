package io.anhkhue.ctsa.topitworksscraper.scraper.collector;

import io.anhkhue.ctsa.topitworksscraper.model.Position;
import io.anhkhue.ctsa.topitworksscraper.model.Skill;
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
