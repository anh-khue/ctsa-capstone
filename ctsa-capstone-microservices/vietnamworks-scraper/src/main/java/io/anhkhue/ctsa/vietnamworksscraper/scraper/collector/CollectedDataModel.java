package io.anhkhue.ctsa.vietnamworksscraper.scraper.collector;

import io.anhkhue.ctsa.vietnamworksscraper.model.Position;
import io.anhkhue.ctsa.vietnamworksscraper.model.Skill;
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
    private int duration;
}
