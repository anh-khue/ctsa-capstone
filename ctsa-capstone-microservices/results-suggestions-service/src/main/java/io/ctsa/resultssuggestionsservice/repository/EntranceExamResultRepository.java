package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.EntranceExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntranceExamResultRepository extends JpaRepository<EntranceExamResult, Integer> {

    @Query(value = "select major_id as id, avg(eer.math) as math, avg(eer.literature) as literature, avg(eer.english) as english,avg(eer.physics) as physics, avg(eer.chemistry) as chemistry, avg(eer.biology) as biology, avg(eer.history) as history, avg(eer.geography) as geography from major_result mr join entrance_exam_result eer on mr.entrance_exam_result_id = eer.id where mr.major_id = :majorId group by mr.major_id", nativeQuery = true)
    EntranceExamResult findCentroid(@Param("majorId") int majorId);
}
