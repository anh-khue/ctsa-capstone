package io.anhkhue.ctsa.topitworksscraper.scraper.collector;

import java.util.List;

public interface Collector {

    List<CollectedDataModel> collectData();
}
