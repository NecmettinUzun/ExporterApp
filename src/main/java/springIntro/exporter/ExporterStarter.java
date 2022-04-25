package springIntro.exporter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "springIntro.exporter")
public class ExporterStarter {

    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExporterStarter.class);
        
        
        ExporterManager exportManager = context.getBean("exportManager", ExporterManager.class);

        System.out.println(exportManager.toString());
        
        exportManager.init();
        
        context.close();
    }
}
