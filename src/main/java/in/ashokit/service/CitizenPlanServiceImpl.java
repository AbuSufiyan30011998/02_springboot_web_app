package in.ashokit.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.repository.CitizenPlanRepo;
import in.ashokit.utils.EmailUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired
	private EmailUtils emailUtils;

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
		/*
		 * List<CitizenPlan> citizenPlans = citizenPlanRepo.searchCitizens(criteria);
		 * return citizenPlans;
		 */

		// filter

		CitizenPlan entity = new CitizenPlan();
		if (StringUtils.isNoneBlank(criteria.getPlanName())) {
			entity.setPlanName(criteria.getPlanName());
		}

		if (StringUtils.isNoneBlank(criteria.getPlanStatus())) {
			entity.setPlanStatus(criteria.getPlanStatus());
		}

		if (StringUtils.isNoneBlank(criteria.getGeneder())) {
			entity.setGeneder(criteria.getGeneder());
		}
		if (null != criteria.getStartPlanDate()) {
			entity.setStartPlanDate(criteria.getStartPlanDate());
		}
		if (null != criteria.getEndPlanDate()) {
			entity.setEndPlanDate(criteria.getEndPlanDate());
		}

		Example<CitizenPlan> of = Example.of(entity);
		return citizenPlanRepo.findAll(of);
	}

	@Override
	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception {

		List<CitizenPlan> records = citizenPlanRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Data");
		HSSFRow headerRow = sheet.createRow(0);

		// set data
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Phone No");
		headerRow.createCell(4).setCellValue("SSN");
		headerRow.createCell(5).setCellValue("Plan Name");
		headerRow.createCell(6).setCellValue("Plan Status");

		int rowIndex = 1;

		for (CitizenPlan record : records) {
			HSSFRow dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(record.getName());
			dataRow.createCell(1).setCellValue(record.getEmail());
			dataRow.createCell(2).setCellValue(record.getGeneder());
			dataRow.createCell(3).setCellValue(record.getPhnNo());
			dataRow.createCell(4).setCellValue(record.getSsn());
			dataRow.createCell(5).setCellValue(record.getPlanName());
			dataRow.createCell(6).setCellValue(record.getPlanStatus());
			rowIndex++;
		}

		// To send file in email attachment

		File file = new File("data.xls");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
		emailUtils.sendEmail(file);

		// To download file in browser
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Override
	public void generatepdf(HttpServletResponse response) throws Exception {

		Document pdfDocs1 = new Document(PageSize.A4);
		ServletOutputStream outputStream = response.getOutputStream();
		PdfWriter.getInstance(pdfDocs1, outputStream);
		pdfDocs1.open();

		Document pdfDocs2 = new Document(PageSize.A4);
		File file = new File("data.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		PdfWriter.getInstance(pdfDocs2, fileOutputStream);
		pdfDocs2.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);

		Paragraph paragraph = new Paragraph("citizen plan info", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		pdfDocs1.add(paragraph);

		pdfDocs2.add(paragraph);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 3, 3, 3, 3, 3, 3, 3 });
		table.setSpacingBefore(5);

		PdfPCell pdfCell = new PdfPCell();
		pdfCell.setBackgroundColor(CMYKColor.BLUE);
		pdfCell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		pdfCell.setPhrase(new Phrase("Name", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("Email", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("Gender", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("Phone No", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("SSN", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(pdfCell);
		pdfCell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(pdfCell);

		// iterate
		List<CitizenPlan> records = citizenPlanRepo.findAll();

		for (CitizenPlan record : records) {
			table.addCell(record.getName());
			table.addCell(record.getEmail());
			table.addCell(record.getGeneder());
			table.addCell(String.valueOf(record.getPhnNo()));
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());

		}

		pdfDocs1.add(table);
		pdfDocs2.add(table);

		pdfDocs1.close();
		pdfDocs2.close();
		outputStream.close();
		fileOutputStream.close();
		emailUtils.sendEmail(file);
	}

}
