package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/api/.")
	public String handleObject(@RequestParam(value="넘어온 요청파라미터 값(name)", defaultValue="넘어온거 없으면 디폴트값") Boolean agree) {
		//만약 넘어온거 없으면 디폴트값을 쓰지 않고 에러를 띄오고싶으면 required=true를 주면된다.
		//그리고 그 name의 값을 변수로 agree에 넣어준다 (기본 api동작구조)
		if(!agree) {
			return "register/step1";
		}
		
		return null;
	}
	
	
	/* --- */
}
