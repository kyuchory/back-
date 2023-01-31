package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RestJsonController;
import spring.PodDao;

/* 설정클래스 의존 자동주입은 사용하지 않았음 */
@Configuration
public class AppCtx {

	@Bean
	public PodDao podDao() {
		return new PodDao();
	}
	
	@Bean
	public RestJsonController restJsonController() {
		RestJsonController restJsonController = new RestJsonController();
		restJsonController.setPodDao(podDao());
		//의존 주입후 return
		return restJsonController;
	}
}
