package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.service.CitizenPlanService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CitizenPlanWebController {

    @Autowired
    private CitizenPlanService citizenPlanService;

    @GetMapping("/")
    public String searchCitizensForm(Model model) {

        formInit(model);

        model.addAttribute("search", new SerachCriteria());
        return "index";
    }

    private void formInit(Model model) {
        List<String> planNames = citizenPlanService.getPlanNames();
        List<String> planStatus = citizenPlanService.getPlanStatus();

        model.addAttribute("plans", planNames);
        model.addAttribute("status", planStatus);
    }

    @PostMapping("/filter-data")
    public String formHandlebtn(@ModelAttribute("search") SerachCriteria criteria, Model model)
            throws JsonProcessingException {
        log.info("{}", criteria);

        List<CitizenPlan> citizensInfo = citizenPlanService.serachCitizens(criteria);
        model.addAttribute("citizens", citizensInfo);
        formInit(model);
        return "index";
    }

    // excel download

    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=data.xls";
        response.addHeader(headerKey, headerValue);
        citizenPlanService.generateExcel(response);
    }


    //pdf download
    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=data.pdf";
        response.addHeader(headerKey, headerValue);
        citizenPlanService.generatepdf(response);
    }

}
