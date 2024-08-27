package com.pranay.happ.serviceIMPL;

import com.lowagie.text.DocumentException;
import com.pranay.happ.serviceI.PdfGenerateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {
	private Logger logger = LoggerFactory.getLogger(PdfGenerateServiceImpl.class);

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${pdf.directory}")
	private String pdfDirectory;

	@Override
	public void generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
		Context context = new Context();
		context.setVariables(data);
	    try {
	        String htmlContent = templateEngine.process(templateName, context);
	        try (FileOutputStream fileOutputStream = new FileOutputStream(pdfDirectory + pdfFileName)) {
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(htmlContent);
	            renderer.layout();
	            renderer.createPDF(fileOutputStream, false);
	            renderer.finishPDF();
	        }
	    } catch (Exception e) {
	        logger.error("Error generating PDF file", e);
	    }
    }
	}