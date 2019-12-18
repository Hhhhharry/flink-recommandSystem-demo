package com.demo.task;

import com.demo.map.GetLogFunction;
import com.demo.map.UserHistoryWithInterestMapFunction;
import com.demo.util.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

/**
* @program: UserInterestTask
* @description: 用户-兴趣 -> 实现基于上下文的推荐逻辑
 * 根据用户对同一个产品的操作计算兴趣度,计算规则通过操作间隔时间(如购物 - 浏览 < 100s)则判定为一次兴趣事件
 * 通过Flink的ValueState实现,如果用户的操作Action=3(收藏),则清除这个产品的state,
 * 如果超过100s没有出现Action=3的事件,也会清除这个state
 * 数据存储在Hbase的u_interest表
* @author: HarryCao
* @create: 2019/12/18-16:28
**/

import java.util.Properties;

public class UserInterestTask {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = Property.getKafkaProperties("interest");
        DataStreamSource<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new GetLogFunction()).keyBy("userId").map(new UserHistoryWithInterestMapFunction());

        env.execute("User Product History");
    }
}
