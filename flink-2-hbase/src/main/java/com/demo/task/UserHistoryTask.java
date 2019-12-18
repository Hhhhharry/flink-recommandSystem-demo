package com.demo.task;


/**
* @program: UserHistoryTask
* @description: 用户-产品浏览历史 -> 实现基于协同过滤的推荐逻辑
* @author: HarryCao
* @create: 2019/12/18-16:20
**/


import com.demo.map.UserHistoryMapFunction;
import com.demo.util.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class UserHistoryTask {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = Property.getKafkaProperties("history");
        DataStreamSource<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new UserHistoryMapFunction());

        env.execute("User Product History");
    }
}
