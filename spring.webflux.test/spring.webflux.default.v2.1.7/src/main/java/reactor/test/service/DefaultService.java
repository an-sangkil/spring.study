package reactor.test.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultService {
	
	public String hello3(String name) {
		
		return "hello3 Service" + name;
	}

	
	public String hello4(String name) {
		
		return "hello4 Service" + name;
	}
}
