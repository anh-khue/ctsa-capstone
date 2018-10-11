package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.Major;
import io.ctsa.resultssuggestionsservice.model.MajorResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorResultRepository extends JpaRepository<MajorResult, Integer> {

    List<MajorResult> findByMajorId(int majorId);

    @Query(value = "select major_id                 as id,\n" +
            "       null                     as person_id,\n" +
            "       avg(characteristic)      as characteristic,\n" +
            "       null                     as entrance_exam_result_id,\n" +
            "       avg(high_school_average) as high_school_average,\n" +
            "       major_id\n" +
            "from major_result\n" +
            "where major_id = :majorId\n" +
            "group by major_id", nativeQuery = true)
    MajorResult findCentroid(@Param("majorId") int majorId);
}
