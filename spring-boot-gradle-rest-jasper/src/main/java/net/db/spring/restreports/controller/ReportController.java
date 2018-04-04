package net.db.spring.restreports.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.db.spring.restreports.domain.report.UserReportData;
import net.db.spring.restreports.service.ReportService;
import net.sf.jasperreports.engine.JREmptyDataSource;

//@Api(value="report", produces = "application/pdf")
@Controller
@RequestMapping(value = "/report")
public class ReportController {
	
	private static final String FILE_FORMAT = "format";
	private static final String DATASOURCE = "datasource";
	
	@Autowired
	private ReportService reportService;
	
	

//    @ApiOperation(value = "Get Report", notes = "Generating report")
//    @ApiResponses(value = {
//    		@ApiResponse(code = 400, message = "Validation errors"),
//            @ApiResponse(code = 200, message = "Report flush"),
//            @ApiResponse(code = 500, message = "Error occurred")
//    })
    @RequestMapping(value = "{reportname}", method = RequestMethod.GET)
    public ModelAndView getReport(final ModelMap modelMap, ModelAndView modelAndView, 
    		@PathParam("reportname") final String reportname, 
    		@RequestParam(FILE_FORMAT) final String format,
    		@RequestParam(value = "param1", required = false) final String param1) {
    	    	
    	modelMap.put(DATASOURCE, new JREmptyDataSource());
        modelMap.put(FILE_FORMAT, format);
        
        UserReportData data = reportService.getReportData();
        
        modelMap.put("USER", data);
        
        modelAndView = new ModelAndView(reportname, modelMap);
        return modelAndView;
    }
	
}
