package springIntro.exporter.dest;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import springIntro.exporter.conf.KafkaConfigs;
import springIntro.exporter.stack.ICollection;

@Component("kafkaDest")
public class KafkaDestination implements IDestination {

    @Autowired
    private ICollection                    dataCollection;

    private KafkaConfigs                   kafkaConfigs;

    private KafkaProducer<Integer, String> producer;

    @Autowired
    public KafkaDestination(KafkaConfigs kafkaConfigs) {
        this.kafkaConfigs = kafkaConfigs;
        initProducer();
    }

    private void initProducer() {

        System.out.println(kafkaConfigs.toString());

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigs.getKafkaBrokers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG,kafkaConfigs.getClientId());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<Integer, String>(props);

    }

    @Override
    public void write() {

        int messageCount = 0;
        while (true) {

            String data = dataCollection.get();
            if (data == null) {
                break;
            }
            messageCount++;
            try {
                ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(kafkaConfigs.getTopicName(),
                                                                                             messageCount,
                                                                                             data);
                
                producer.send(record).get();
            } catch (Exception e) {
                System.out.println("" +e);
            }
        }
    }

    @Override
    public String toString() {
        return "KafkaDestination []";
    }

}
