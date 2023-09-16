package in.ashokit.repository;

import java.util.List;

import in.ashokit.binding.SerachCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

    @Query("select distinct(planName) from CitizenPlan")
    public List<String> getUniquePlanNames();

    @Query("select distinct(planStatus) from CitizenPlan")
    public List<String> getUniquePlanStatus();

    @Query("SELECT c FROM CitizenPlan c WHERE " +
            "(:#{#criteria.planName} IS NULL OR c.planName = :#{#criteria.planName}) AND " +
            "(:#{#criteria.planStatus} IS NULL OR c.planStatus = :#{#criteria.planStatus}) AND " +
            "(:#{#criteria.geneder} IS NULL OR c.geneder = :#{#criteria.geneder}) AND " +
            "(:#{#criteria.startPlanDate} IS NULL OR c.startPlanDate >= :#{#criteria.startPlanDate}) AND " +
            "(:#{#criteria.endPlanDate} IS NULL OR c.endPlanDate <= :#{#criteria.endPlanDate})")
    public List<CitizenPlan> searchCitizens(SerachCriteria criteria);
}
