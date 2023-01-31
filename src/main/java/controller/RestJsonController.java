package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.*;

@RestController
public class RestJsonController {

	private PodDao podDao;
	
	public void setPodDao(PodDao podDao) {
		this.podDao = podDao;
	}

	/* -RestApi- */
	//RestApi 다룰 예정
	@GetMapping("/api/.")
	public List<Pod> pods(){
		//
		return null;
	}
	
	
	/* --- */
}
