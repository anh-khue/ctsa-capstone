package io.ctsa.resultssuggestionsservice.repository.sample;

import io.ctsa.resultssuggestionsservice.model.sample.HighSchoolTopSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HighSchoolTopSampleRepository extends JpaRepository<HighSchoolTopSample, Integer> {

    List<HighSchoolTopSample> findByMajorSampleId(int majorId);

    @Query(value = "select hsts.subject_id  as id,\n" +
            "       hsts.subject_id  as major_sample_id,\n" +
            "       hsts.subject_id  as subject_id,\n" +
            "       avg(hsts.mark)   as mark,\n" +
            "       avg(hsts.weight) as weight\n" +
            "from high_school_top_sample hsts\n" +
            "       join major_sample ms on hsts.major_sample_id = ms.id\n" +
            "where ms.major_id = :majorId\n" +
            "group by hsts.subject_id", nativeQuery = true)
    List<HighSchoolTopSample> findCentroid(@Param("majorId") int majorId);
}
