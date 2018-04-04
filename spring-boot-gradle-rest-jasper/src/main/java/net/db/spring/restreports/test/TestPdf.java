package net.db.spring.restreports.test;

import java.io.IOException;

import com.testautomationguru.utility.PDFUtil;

public class TestPdf {

	public static void main(String[] args) throws IOException {
		
		String file1="C:\\WORK\\workspace-oxygen\\spring-boot-gradle-rest-jasper\\src\\main\\java\\net\\db\\spring\\restreports\\test\\rpt_A.pdf";
		String file2="C:\\WORK\\workspace-oxygen\\spring-boot-gradle-rest-jasper\\src\\main\\java\\net\\db\\spring\\restreports\\test\\rpt_B.pdf";
		
		PDFUtil pdfUtil = new PDFUtil();
		
		System.out.println("File1: " + pdfUtil.getText(file1));
		System.out.println("File2: " + pdfUtil.getText(file2));
		
		System.out.println("Compare result: " + pdfUtil.compare(file1, file2));
		
	}

}
