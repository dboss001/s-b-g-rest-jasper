package net.db.spring.restreports;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import net.db.spring.restreports.controller.ReportController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
    private ReportController controller;

    @Test
    public void contexLoads() throws Exception {
        
    	assertThat(controller).isNotNull();
    	
    }
    
}
