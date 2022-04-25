package springIntro.exporter.source;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springIntro.exporter.conf.KafkaConfigs;
import springIntro.exporter.stack.ICollection;

@Component("kafkaSource")
public class KafkaSource implements ISource{

    @Autowired
    private ICollection                    dataCollection;

    private KafkaConfigs                   kafkaConfigs;

    private KafkaConsumer<Integer, String> consumer;
    
    @Autowired
    public KafkaSource(KafkaConfigs kafkaConfigs) {
        this.kafkaConfigs = kafkaConfigs;
    }
    
    @Override
    public boolean read() {
        System.out.println(kafkaConfigs.toString());
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigs.getKafkaBrokers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG,kafkaConfigs.getClientId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigs.getGroupName());
        consumer = new KafkaConsumer<Integer, String>(props);
        consumer.subscribe(Arrays.asList(kafkaConfigs.getTopicName()));
        
        while(true) {
            
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));
            if(records == null ) {
                break;
            }
            for (ConsumerRecord<Integer, String> consumerRecord : records) {
                this.dataCollection.put(consumerRecord.toString());
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "KafkaSource []";
    }

}
