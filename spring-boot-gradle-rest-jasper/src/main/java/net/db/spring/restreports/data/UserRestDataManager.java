package net.db.spring.restreports.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.db.spring.restreports.domain.data.User;

@Component
public class UserRestDataManager {
	
	private static final String URL = "https://jsonplaceholder.typicode.com/users";
	
	@Autowired
	private RestTemplate restTemplate;

	public List<User> getData()  {
		
		List<User> result = new ArrayList<>();
		
        User[] users = this.restTemplate.getForObject(URL, User[].class);
        
        result = new ArrayList<User>(Arrays.asList(users));
        
		return result;
	}
	
}
