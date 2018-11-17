package io.ctsa.warehouseservice.repository;

import io.ctsa.warehouseservice.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {

    @Query(value = "select count(*)\n" +
            "from recruitment\n" +
            "where position_id = :positionId\n" +
            "   and (:inputMonth between month(start_date) and month(end_date))" +
            "   and (:inputYear between year(start_date) and year(end_date))",
           nativeQuery = true)
    Integer countRecruitmentByPositionIdAndMonth(@Param("positionId") Integer positionId,
                                                 @Param("inputMonth") Integer inputMonth,
                                                 @Param("inputYear") Integer inputYear);
}
