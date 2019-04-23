package cn.daniel.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.daniel.interfaze.MultiDataSource;
import cn.daniel.mapper.LayerDataMapper;
import cn.daniel.service.LayerDataService;
import oracle.sql.CLOB;

@Service("layerDataService")
public class LayerDataServiceImpl implements LayerDataService {

    @Autowired
    private LayerDataMapper layerDataMapper;

    @Autowired
    private TransportClient transportClient;

    @PostConstruct
    public void init() {
    }

    @Override
    @MultiDataSource("data")
    @Transactional(readOnly = true)
    public Integer pushDbData2ES(String owner, String tablename, String orderBy, Integer startIndex, Integer endIndex) {
        List<Map<String, Object>> lmData = this.search(owner, tablename, orderBy, startIndex, endIndex);
        if (lmData == null) {
            return 0;
        }
        System.out.println("查询数据量：" + lmData.size());
        int i = 1;
        for (Map<String, Object> mData : lmData) {
            this.formatData(mData);
            transportClient.prepareIndex("myindex", "user", "" + i++).setSource(mData).execute().actionGet();
        }
        return lmData.size();
    }

    private void formatData(Map<String, Object> mData) {
        Iterator<Entry<String, Object>> iterator = mData.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> item = iterator.next();
            if (item.getValue() instanceof java.sql.Date || item.getValue() instanceof java.sql.Timestamp) {
                item.setValue(((Date) item.getValue()).getTime());
            } else if (item.getValue() instanceof CLOB) {
                try {
                    item.setValue(clobToString((CLOB) item.getValue()));
                } catch (Exception e) {
                    item.setValue("");
                }
            } else if (item.getValue() instanceof BigDecimal) {
                item.setValue(((BigDecimal) item.getValue()).floatValue());
            }
        }
    }

    private String clobToString(Clob clob) throws SQLException, IOException {
        String reString = "";
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }

    private List<Map<String, Object>> search(String owner, String tablename, String orderBy, Integer startIndex,
            Integer endIndex) {
        if (StringUtils.isEmpty(owner) || StringUtils.isEmpty(tablename)) {
            return null;
        }
        if (startIndex == null) {
            startIndex = 0;
        }
        if (endIndex == null) {
            endIndex = 10;
        }
        if (StringUtils.isEmpty(orderBy)) {
            return layerDataMapper.selectByTableNameWithPage(owner, tablename, startIndex, endIndex);
        } else {
            return layerDataMapper.selectByTableNameWithPageOrderAsc(owner, tablename, orderBy, startIndex, endIndex);
        }
    }
}
