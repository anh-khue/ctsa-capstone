package io.anhkhue.ctsa.myworkscraper.scraper.collector;

import java.util.List;

public interface Collector {

    List<CollectedDataModel> collectData();
}
