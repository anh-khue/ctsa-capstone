package io.ctsa.careerssuggestionsservice.repository.sample;

import io.ctsa.careerssuggestionsservice.model.sample.MajorSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorSampleRepository extends JpaRepository<MajorSample, Integer> {

    List<MajorSample> findByMajorId(int majorId);

    @Query(value = "select major_id                 as id,\n" +
            "       null                     as person_id,\n" +
            "       avg(characteristic)      as characteristic,\n" +
            "       null                     as entrance_exam_sample_id,\n" +
            "       avg(high_school_average) as high_school_average,\n" +
            "       major_id\n" +
            "from major_sample\n" +
            "where major_id = :majorId\n" +
            "group by major_id", nativeQuery = true)
    MajorSample findCentroid(@Param("majorId") int majorId);
}
