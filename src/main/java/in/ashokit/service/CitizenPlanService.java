package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;

public interface CitizenPlanService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> serachCitizens(SerachCriteria criteria);

	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception;

	public void generatepdf(HttpServletResponse httpServletResponse) throws Exception;

}
