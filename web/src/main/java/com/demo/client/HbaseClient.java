package com.demo.client;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.mortbay.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: web
 * @Description:
 * @author: shijiucao
 * @CreateTime: 2019-12-22 14:59
 */
@Component
public class HbaseClient {
    @Autowired
    private HbaseTemplate hbaseTemplate;

    public void createTable(String test, String test01) {
    }

    /*
    获取rowKey一列的数据
     */
    public List<Map.Entry> getRow(String tableName, String rowKey) {
        if(StringUtils.isBlank(tableName) || StringUtils.isBlank(rowKey))
            return null;
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<List<Map.Entry>>() {
            @Override
            public List<Map.Entry> mapRow(Result result, int i) throws Exception {
                List<Cell> listcell = result.listCells();
                Map<String, Object> map = new HashMap<>();
                if(listcell != null || listcell.size() > 0)
                    for(Cell cell : listcell)
                    {
                        map.put(Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength())
                                ,Bytes.toString(cell.getValueArray(),cell.getValueOffset(), cell.getValueLength()));
                    }
                return new ArrayList<>(map.entrySet());
            }
        });
    }

    /*
    Hbase查找单个数据
     */
    public String getData(String tableName, String rowKey, String family, String column) {
        if(StringUtils.isBlank(tableName) || StringUtils.isBlank(rowKey)
                || StringUtils.isBlank(family) || StringUtils.isBlank(column))
            return null;
        return hbaseTemplate.get(tableName, rowKey, family, column, new RowMapper<String>() {
            @Override
            public String mapRow(Result result, int i) throws Exception {
                List<Cell> listCell = result.listCells();
                String res = "";
                if(listCell != null && listCell.size() > 0)
                    for(Cell cell : listCell)
                    {
                        res = Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getFamilyLength());
                    }
                return res;
            }
        });
    }




}
