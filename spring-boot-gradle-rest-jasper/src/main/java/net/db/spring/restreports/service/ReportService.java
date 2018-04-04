package net.db.spring.restreports.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.db.spring.restreports.data.UserRestDataManager;
import net.db.spring.restreports.domain.data.User;
import net.db.spring.restreports.domain.report.UserReportData;

@Component
public class ReportService {
	
	@Autowired
	private UserRestDataManager userRestDataManager;
	
	
	public UserReportData getReportData () {
		
		UserReportData report = new UserReportData();
		
		List<User> users = userRestDataManager.getData();
		
		if(!users.isEmpty()) {
			
			report.setName(users.get(0).getName());
			report.setUsername(users.get(0).getUsername());
			report.setAddresses(new ArrayList<>());
			
			for (User usr : users) {
				report.getAddresses().add(usr.getAddress());   
			}
			
		}
		
		return report;
	}
}
