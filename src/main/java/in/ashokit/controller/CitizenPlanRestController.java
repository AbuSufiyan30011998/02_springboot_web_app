package in.ashokit.controller;

import java.time.LocalDate;
import java.util.List;

import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.CitizenPlanService;

@RestController
@RequestMapping("/citizen")
public class CitizenPlanRestController {

    private final String geneder = "geneder";
    @Autowired
    private CitizenPlanService citizenPlanService;

    @GetMapping("/names")
    public ResponseEntity<?> getPlaneNames() {
        List<String> planNames = citizenPlanService.getPlanNames();
        return new ResponseEntity<>(planNames, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getPlaneStatus() {
        List<String> planStatus = citizenPlanService.getPlanStatus();
        return new ResponseEntity<>(planStatus, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<CitizenPlan> searchCitizens(
            @RequestParam(name = "planName", required = false) String planName,
            @RequestParam(name = "planStatus", required = false) String planStatus,
            @RequestParam(name = geneder, required = false) String gender,
            @RequestParam(name = "startPlanDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startPlanDate,

            @RequestParam(name = "endPlanDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endPlanDate
    ) {
        SerachCriteria criteria = new SerachCriteria();
        criteria.setPlanName(planName);
        criteria.setPlanStatus(planStatus);
        criteria.setGeneder(gender);
        criteria.setStartPlanDate(startPlanDate);
        criteria.setEndPlanDate(endPlanDate);

        return citizenPlanService.serachCitizens(criteria);
    }


}
