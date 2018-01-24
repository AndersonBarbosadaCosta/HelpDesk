package com.schoolofnet.HelpDesk.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schoolofnet.HelpDesk.model.Ticket;
import com.schoolofnet.HelpDesk.services.TicketService;
import com.schoolofnet.HelpDesk.util.TicketReportPDF;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private TicketService ticket;

	public ReportsController(TicketService ticket) {
		this.ticket = ticket;
	}

	@GetMapping("/tickets")
	public String ticketReport(@RequestParam(required = false, value = "day") Integer day, Model model) {
		model.addAttribute("list", this.ticket.reportsTicketsByDays(day));
		return "reports/ticket";
	}

	@GetMapping("/tickets/pdfgen")
	public String ticketReportPdfForm(Model model) {

		return "reports/ticket_pdf";
	}

	@GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfTicketReport(
			@RequestParam(required = false, value = "day") Integer day, Model model) {
		List<Ticket> tickets = this.ticket.reportsTicketsByDays(day);
		model.addAttribute("list", tickets);
		ByteArrayInputStream pdf = TicketReportPDF.generate(tickets);

		return ResponseEntity
				.ok()
				.header("Content-Disposition", "inline; filname = report.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));

	}

}
