/*
 * package in.ashokit.runner;
 * 
 * import java.time.LocalDate; import java.util.Arrays; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.ApplicationArguments; import
 * org.springframework.boot.ApplicationRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import in.ashokit.entity.CitizenPlan; import
 * in.ashokit.repository.CitizenPlanRepo;
 * 
 * @Component public class DataLoader implements ApplicationRunner {
 * 
 * @Autowired private CitizenPlanRepo citizePlanRepo;
 * 
 * @Override public void run(ApplicationArguments args) throws Exception {
 * 
 * CitizenPlan citizenPlan1 = new CitizenPlan("John", "john@gmail.com",
 * 9984897087l, 12121l, "Male", "Food", "Approved", LocalDate.now(),
 * LocalDate.now().plusMonths(6));
 * 
 * CitizenPlan citizenPlan2 = new CitizenPlan("smith", "smith@gmail.com",
 * 9984897089l, 122121l, "Male", "Cash", "Denied", null, null);
 * 
 * CitizenPlan citizenPlan3 = new CitizenPlan("kate", "kate@gmail.com",
 * 784897087l, 112123l, "Female", "Food", "Approved", LocalDate.now(),
 * LocalDate.now().plusMonths(6));
 * 
 * CitizenPlan citizenPlan4 = new CitizenPlan("diana", "diane@gmail.com",
 * 9984897082l, 42121l, "Female", "Medical", "Denied", null, null);
 * 
 * CitizenPlan citizenPlan5 = new CitizenPlan("Cathy", "cathy@gmail.com",
 * 998489987l, 12151l, "Female", "Food", "Approved", LocalDate.now(),
 * LocalDate.now().plusMonths(6)); CitizenPlan citizenPlan6 = new
 * CitizenPlan("Mark", "mark@gmail.com", 9984891087l, 1212321l, "Male",
 * "Medical", "Approved", LocalDate.now(), LocalDate.now().plusMonths(3));
 * 
 * List<CitizenPlan> citzenPlanList = Arrays.asList(citizenPlan1, citizenPlan2,
 * citizenPlan3, citizenPlan4, citizenPlan5, citizenPlan6);
 * 
 * citizePlanRepo.saveAll(citzenPlanList);
 * 
 * }
 * 
 * }
 */