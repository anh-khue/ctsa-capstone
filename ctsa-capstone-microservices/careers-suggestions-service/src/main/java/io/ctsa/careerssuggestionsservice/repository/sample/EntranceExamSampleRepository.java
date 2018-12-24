package io.ctsa.careerssuggestionsservice.repository.sample;

import io.ctsa.careerssuggestionsservice.model.sample.EntranceExamSampleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntranceExamSampleRepository extends JpaRepository<EntranceExamSampleDetail, Integer> {

    @Query(value = "select avg(eesd.mark)\n" +
            "from entrance_exam_sample_detail eesd\n" +
            "       join major_sample mr on eesd.major_sample_id = mr.id\n" +
            "       join entrance_exam_subject ees on eesd.entrance_exam_subject_id = ees.id\n" +
            "where mr.major_id = :majorId\n" +
            "  and ees.id = :subjectId\n" +
            "group by eesd.entrance_exam_subject_id", nativeQuery = true)
    double findSubjectAverageByMajor(@Param("majorId") int majorId, @Param("subjectId") int subjectId);
}
