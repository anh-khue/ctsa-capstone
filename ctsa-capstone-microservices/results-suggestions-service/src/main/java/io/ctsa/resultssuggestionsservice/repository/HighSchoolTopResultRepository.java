package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface HighSchoolTopResultRepository extends JpaRepository<HighSchoolTopResult, Integer> {

    List<HighSchoolTopResult> findByMajorResultId(int majorId);

    @Query(value = "select hstr.subject_id  as id,\n" +
            "       hstr.subject_id  as major_result_id,\n" +
            "       hstr.subject_id  as subject_id,\n" +
            "       avg(hstr.mark)   as mark,\n" +
            "       avg(hstr.weight) as weight\n" +
            "from high_school_top_result hstr\n" +
            "       join major_result mr on hstr.major_result_id = mr.id\n" +
            "where mr.major_id = :majorId\n" +
            "group by hstr.subject_id", nativeQuery = true)
    List<HighSchoolTopResult> findCentroid(@Param("majorId") int majorId);
}
