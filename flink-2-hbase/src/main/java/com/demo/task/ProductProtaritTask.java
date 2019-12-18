package com.demo.task;

import com.demo.map.ProductPortraitMapFunction;
import com.demo.util.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
* @program: ProductProtaritTask
* @description: 产品画像记录 -> 实现基于标签的推荐逻辑
 * 用两个维度记录产品画像,一个是喜爱该产品的年龄段,另一个是性别
 * 数据存储在Hbase的prod表
* @author: HarryCao
* @create: 2019/12/18-17:06
**/

/**
 * 产品画像 -> Hbase
 *
 * @author XINZE
 */
public class ProductProtaritTask {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = Property.getKafkaProperties("ProductPortrait");
        DataStreamSource<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new ProductPortraitMapFunction());
        env.execute("Product Portrait");

    }
}
