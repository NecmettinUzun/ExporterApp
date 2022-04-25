package springIntro.exporter.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfigs {

    @Autowired
    private String kafkaBrokers;

    @Autowired
    private String clientId;

    @Autowired
    private String topicName;

    @Autowired
    private String groupName;

    public String getKafkaBrokers() {
        return kafkaBrokers;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "KafkaConfigs [kafkaBrokers=" + kafkaBrokers + ", clientId=" + clientId
                + ", topicName=" + topicName + ", groupName=" + groupName + "]";
    }

}
