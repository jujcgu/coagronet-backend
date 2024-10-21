package com.coagronet.reports.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.reports.services.ReportService;

@RestController
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/api/report")
    public ResponseEntity<byte[]> generateReport(@RequestParam int category) {

        System.out.println("category=" + category);

        try {
            // Generate the report as a byte array
            byte[] report = reportService.generateReport(category); // (message);

            // Set headers for PDF response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // headers.setContentDispositionFormData("attachment", "report.pdf");
            headers.setContentDispositionFormData("inline", "productoReport.pdf");
            // headers.setContentDispositionFormData("inline", "report.pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error LIA:" + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
