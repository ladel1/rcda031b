package blogdev.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

@Component
public class JavaFakerHelper {

	@Bean
	public Faker faker() {
		return new Faker();
	} 
	
}
