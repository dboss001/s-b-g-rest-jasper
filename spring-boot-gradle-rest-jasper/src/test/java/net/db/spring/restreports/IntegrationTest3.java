package net.db.spring.restreports;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import net.db.spring.restreports.domain.data.Address;
import net.db.spring.restreports.domain.data.User;
import net.db.spring.restreports.service.ReportService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.Matchers.*;

import java.io.ByteArrayInputStream;

/**
 * Full application context is started without tomcat server with mocked service
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest3 {
	
	private static final String REPORT_URL = "http://localhost:8080/report/rpt_A?format=pdf";
	private static final String DATA_URL = "https://jsonplaceholder.typicode.com/users";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private RestTemplate restTemplate;
	
	@InjectMocks
    private ReportService service;

    @Test
    public void example() throws Exception {
    	
    	
    	Address addr = new Address("Street1", "Suite1", "City1", "ZipCode1", null);
    	User usr = new User("Ime1", "Username1", addr);
    	
    	when(restTemplate.getForObject(DATA_URL, User[].class)).thenReturn(new User[] {usr});
        
        byte[] result = this.mockMvc.perform(get(REPORT_URL))
        		.andDo(print())
        		.andExpect(status().isOk())
                .andExpect(content().string(notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        
        PDDocument doc = PDDocument.load(new ByteArrayInputStream(result));
        assertEquals(1, doc.getNumberOfPages());
        
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(5);
        
        String text = pdfStripper.getText(doc);
        System.out.println("DOC TEXT: " + text);
        
        assertThat(text).contains("Username1");
        
        doc.close();
        
    }
    
}
