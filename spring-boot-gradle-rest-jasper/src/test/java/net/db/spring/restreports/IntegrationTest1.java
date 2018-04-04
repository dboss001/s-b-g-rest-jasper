package net.db.spring.restreports;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;

/**
 * Full application context is started with tomcat server (random port is used)
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest1 {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void example() throws Exception {
        
        String url = "http://localhost:" + port + "/report/rpt_A?format=pdf";
        byte[] result = this.testRestTemplate.getForObject(url, byte[].class);
        
        PDDocument doc = PDDocument.load(new ByteArrayInputStream(result));
        assertEquals(2, doc.getNumberOfPages());
        
        
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(5);
        
        String text = pdfStripper.getText(doc);
        System.out.println("DOC TEXT: " + text);
        
        assertThat(text).contains("Leanne Graham");
        
        doc.close();
        
    }
    
}
