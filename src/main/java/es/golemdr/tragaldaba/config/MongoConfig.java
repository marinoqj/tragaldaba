package es.golemdr.tragaldaba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
@EnableMongoRepositories(basePackages = "es.golemdr.tragaldaba.repository")
@ComponentScan(basePackages = { "es.golemdr.tragaldaba.*" })
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	
	@Value("${spring.data.mongodb.uri}")
	private String databaseUri;

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}
	
	
    @Bean
    public MongoClient mongo() {
    	ConnectionString connectionString = null;
    	MongoClientSettings mongoClientSettings = null;
    	MongoClient mongoClient = null;
    	
    	if (databaseUri != null) {
    		connectionString = new ConnectionString(databaseUri);
            mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            mongoClient = MongoClients.create(mongoClientSettings);
    	}
        
        return mongoClient;
    }
    
    
    /**
     * 
     * Si se quieren utilizar credenciales habrá que crear un usuario y una password en MongoDB:
     * 
	      db.createUser(
		 	  {
		 	    user: "usuarioMongoDB",
		 	    pwd: "passwordMongoDB",
		 	    roles: [
		 	       { role: "readWrite", db: "nombreBaseDeDatosMongoDB" }
		 	    ]
		 	  }
		 	)
	 *
     */
//	@Bean
//	public MongoClient mongoClient() {
//		ServerAddress address = new ServerAddress("127.0.0.1", 27017);
//		MongoCredential credential = MongoCredential.createCredential("usuarioMongoDB", getDatabaseName(), "passwordMongoDB".toCharArray());
//		MongoClientOptions options = new MongoClientOptions.Builder().build();
//        
//		MongoClient client = new MongoClient(address, credential, options);
//		return client;
//	}    

}

// NOTA:
// No hace falta definir el @Bean MongoTemplate porque ya está definido en AbstractMongoClientConfiguration

