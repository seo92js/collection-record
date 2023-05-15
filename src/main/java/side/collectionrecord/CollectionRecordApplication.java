package side.collectionrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CollectionRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionRecordApplication.class, args);
	}

}
