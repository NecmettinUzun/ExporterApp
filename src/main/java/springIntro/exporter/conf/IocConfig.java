package springIntro.exporter.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import springIntro.exporter.ExporterManager;
import springIntro.exporter.stack.ICollection;
import springIntro.exporter.stack.QueueCollection;
import springIntro.exporter.utils.FileUtils;

@Configuration
@PropertySource("classpath:/resources/conf.properties")
public class IocConfig {


    @Bean
    @Scope("singleton")
    public ExporterManager exportManager() {
        return new ExporterManager();
    }
    
    @Bean
    @Scope("singleton")
    public FileUtils fileUtils() {
        return new FileUtils();
    }
    
    @Bean
    @Scope("singleton")
    public ICollection dataCollection() {
        return new QueueCollection();
    }
    
    @Bean
    @Scope("singleton")
    public KafkaConfigs kafkaConfigs() {
        return new KafkaConfigs();
    }
    
}
