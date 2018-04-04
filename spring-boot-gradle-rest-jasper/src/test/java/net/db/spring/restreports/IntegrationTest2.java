package net.db.spring.restreports;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.Matchers.*;

import java.io.ByteArrayInputStream;

/**
 * Full application context is started without tomcat server
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest2 {
	
	private static final String REPORT_URL = "http://localhost:8080/report/rpt_A?format=pdf";
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void example() throws Exception {
          
        byte[] result = this.mockMvc.perform(get(REPORT_URL))
        		.andDo(print())
        		.andExpect(status().isOk())
                .andExpect(content().string(notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        
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
