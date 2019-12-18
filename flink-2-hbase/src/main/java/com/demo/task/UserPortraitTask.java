package com.demo.task;
/**
* @program: UserPortraitTask
* @description: 用户画像计算 -> 实现基于标签的推荐逻辑
 * v1.0按照三个维度去计算用户画像,分别是用户的颜色兴趣,用户的产地兴趣,和用户的风格兴趣.
 * 根据日志不断的修改用户画像的数据,记录在Hbase中.数据存储在Hbase的user表
* @author: HarryCao
* @create: 2019/12/18-17:01
**/
import com.demo.map.UserPortraitMapFunction;
import com.demo.util.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * 用户画像 -> Hbase
 *
 * @author XINZE
 */
public class UserPortraitTask {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = Property.getKafkaProperties("userPortrait");
        DataStreamSource<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new UserPortraitMapFunction());
        env.execute("User Portrait");
    }
}
