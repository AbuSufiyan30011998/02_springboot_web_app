package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.repository.CitizenPlanRepo;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

    @Autowired
    private CitizenPlanRepo citizenPlanRepo;

    @Override
    public List<String> getPlanNames() {
        List<String> uniquePlanNames = citizenPlanRepo.getUniquePlanNames();
        return uniquePlanNames;
    }

    @Override
    public List<String> getPlanStatus() {
        List<String> uniquePlanStatus = citizenPlanRepo.getUniquePlanStatus();
        return uniquePlanStatus;
    }

    @Override
    public List<CitizenPlan> serachCitizens(SerachCriteria criteria) {
        List<CitizenPlan> citizenPlans = citizenPlanRepo.searchCitizens(criteria);
        return citizenPlans;
    }

    @Override
    public void generateExcel(HttpServletResponse httpServletResponse) {

    }

    @Override
    public void generatepdf(HttpServletResponse httpServletResponse) {

    }

}
