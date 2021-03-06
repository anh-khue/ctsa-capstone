package io.ctsa.warehouseservice.repository;

import io.ctsa.warehouseservice.model.RequiredSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {

    @Query(value = "select rs.skill_id\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId\n" +
            "  and rs.skill_type_id = :skillTypeId\n" +
            "group by rs.skill_id\n" +
            "order by count(rs.skill_id) desc\n" +
            "limit :itemPerPage\n" +
            "offset :skippedItemNumber",
           nativeQuery = true)
    List<Integer> findTopSkillsByPositionAndSkillType(@Param("positionId") Integer positionId,
                                                      @Param("skillTypeId") Integer skillTypeId,
                                                      @Param("itemPerPage") Integer itemPerPage,
                                                      @Param("skippedItemNumber") Integer skippedItemNumber);

    @Query(value = "select rs.skill_id\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId\n" +
            "  and rs.skill_type_id = :skillTypeId\n" +
            "group by rs.skill_id\n" +
            "order by count(rs.skill_id) desc",
           nativeQuery = true)
    List<Integer> findAllTopSkillsByPositionAndSkillType(@Param("positionId") Integer positionId,
                                                         @Param("skillTypeId") Integer skillTypeId);

    @Query(value = "select rs.skill_type_id\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId\n" +
            "group by rs.skill_type_id\n" +
            "order by count(rs.skill_type_id) desc",
           nativeQuery = true)
    List<Integer> findTopSkillTypesByPosition(@Param("positionId") Integer positionId);

    @Query(value = "select rs.skill_type_id\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId and rs.skill_type_id <> :skillTypeId\n" +
            "group by rs.skill_type_id\n" +
            "order by count(rs.skill_type_id) desc",
           nativeQuery = true)
    List<Integer> findTopSkillTypesByPositionEscapeSkillType(@Param("positionId") Integer positionId,
                                                             @Param("skillTypeId") Integer skillTypeId);

    @Query(value = "select count(rs.skill_id)\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId\n" +
            "   and rs.skill_id = :skillId\n" +
            "   and rs.skill_type_id != 11" +
            "   and (:inputMonth between month(start_date) and month(end_date))\n" +
            "   and (:inputYear between year(start_date) and year(end_date))",
           nativeQuery = true)
    Integer findTotalRecruitmentByPositionIdAndSkillIdAndMonthYear(@Param("positionId") Integer positionId,
                                                                   @Param("skillId") Integer skillId,
                                                                   @Param("inputMonth") Integer month,
                                                                   @Param("inputYear") Integer year);

    @Query(value = "select rs.skill_id\n" +
            "from recruitment r\n" +
            "       join required_skill rs on r.id = rs.recruitment_id\n" +
            "where r.position_id = :positionId" +
            "   and rs.skill_type_id != 11\n" +
            "group by rs.skill_id\n" +
            "order by count(rs.skill_id) desc\n" +
            "limit :top",
           nativeQuery = true)
    List<Integer> findTopSkillsByPositionId(@Param("positionId") Integer positionId,
                                            @Param("top") Integer top);
}
