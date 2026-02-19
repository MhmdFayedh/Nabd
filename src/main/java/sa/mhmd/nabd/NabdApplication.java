package sa.mhmd.nabd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "sa.mhmd.nabd.modules.discovery")
@EnableCaching
public class NabdApplication {

	public static void main(String[] args) {
		SpringApplication.run(NabdApplication.class, args);
	}

}
