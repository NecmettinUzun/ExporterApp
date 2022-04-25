package springIntro.exporter.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springIntro.exporter.dest.IDestination;
import springIntro.exporter.source.ISource;

@Configuration
public class PropertiesFromConfig {

    @Autowired
    Environment        env;

    @Autowired
    ApplicationContext context;

    @Bean
    public String inputFilePath() {
        String filePath = env.getProperty(ConfigNames.INPUT_FILE_PATH);
        return filePath;
    }

    @Bean
    public String outputFilePath() {
        String filePath = env.getProperty(ConfigNames.OUTPUT_FILE_PATH);
        return filePath;
    }
    
    @Bean
    public ISource source() {
        String dataSource = env.getProperty(ConfigNames.DATA_SOURCE);
        return context.getBean(dataSource, ISource.class);
    }

    @Bean
    public IDestination dest() {
        String dataSource = env.getProperty(ConfigNames.DATA_DEST);
        return context.getBean(dataSource, IDestination.class);
    }
    
    @Bean
    public String kafkaBrokers() {
        return env.getProperty(ConfigNames.KAFKA_BROKERS);
    }
    
    @Bean
    public String clientId() {
        return env.getProperty(ConfigNames.CLIENT_ID);
    }
    
    @Bean
    public String topicName() {
        return env.getProperty(ConfigNames.TOPIC_NAME);
    }
    
    @Bean
    public String groupName() {
        return env.getProperty(ConfigNames.GROUP_ID_CONFIG);
    }
}
