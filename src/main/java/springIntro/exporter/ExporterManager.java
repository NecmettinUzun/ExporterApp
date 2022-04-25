package springIntro.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import springIntro.exporter.dest.IDestination;
import springIntro.exporter.source.ISource;

public class ExporterManager {

    public ExporterManager() {
    }

    @Autowired
    @Qualifier("source")
    private ISource    source;
    
    @Autowired
    @Qualifier("dest")
    private IDestination dest;
    
    
    public void init() {
        source.read();
        dest.write();
    }
    
    
    @Override
    public String toString() {
        return "ExporterManager [source=" + source + ", dest=" + dest + "]";
    }

    
}
