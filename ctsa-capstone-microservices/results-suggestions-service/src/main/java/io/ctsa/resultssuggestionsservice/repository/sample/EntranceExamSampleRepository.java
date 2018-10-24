package io.ctsa.resultssuggestionsservice.repository.sample;

import io.ctsa.resultssuggestionsservice.model.sample.EntranceExamSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntranceExamSampleRepository extends JpaRepository<EntranceExamSample, Integer> {

    @Query(value = "select avg(eerd.mark)\n" +
            "from entrance_exam_sample eer\n" +
            "       join major_sample mr on mr.entrance_exam_sample_id = eer.id\n" +
            "       join entrance_exam_sample_detail eerd on eer.id = eerd.entrance_exam_sample_id\n" +
            "       join entrance_exam_subject ees on eerd.entrance_exam_subject_id = ees.id\n" +
            "where mr.major_id = :majorId\n" +
            "  and ees.id = :subjectId\n" +
            "group by eerd.entrance_exam_subject_id", nativeQuery = true)
    double findSubjectAverageByMajor(@Param("majorId") int majorId, @Param("subjectId") int subjectId);
}
