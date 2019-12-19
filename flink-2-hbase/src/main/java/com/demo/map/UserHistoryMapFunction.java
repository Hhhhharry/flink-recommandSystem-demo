package com.demo.map;
/**
* @program: UserHistoryMapFunction
* @description: 通过Flink去记录用户浏览过这个类目下的哪些产品, 为后面的基于Item的协同过滤做准备
 * 实时的记录用户的评分到Hbase中,为后续离线处理做准备.
 * 数据存储在Hbase的tory表
* @author: HarryCao
* @create: 2019/12/18-16:23
**/
import com.demo.client.HbaseClient;
import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class UserHistoryMapFunction implements MapFunction<String, String> {

    /**
     * 将 用户-产品  和 产品-用户 分别存储Hbase表
     * @params
     * @return
     * @throws Exception
     */
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        if (null != log){
            HbaseClient.increamColumn("u_history",String.valueOf(log.getUserId()),"p",String.valueOf(log.getProductId()));
            HbaseClient.increamColumn("p_history",String.valueOf(log.getProductId()),"p",String.valueOf(log.getUserId()));
        }
        return "";
    }
}
