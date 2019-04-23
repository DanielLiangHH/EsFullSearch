package cn.daniel.service;

public interface LayerDataService {

    Integer pushDbData2ES(String owner, String tablename, String orderBy, Integer startIndex, Integer endIndex);
}
