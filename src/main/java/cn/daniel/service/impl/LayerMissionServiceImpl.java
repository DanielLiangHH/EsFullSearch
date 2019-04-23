package cn.daniel.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.daniel.service.LayerDataService;
import cn.daniel.service.LayerMissionService;

@Service("layerMissionService")
public class LayerMissionServiceImpl implements LayerMissionService {

    @Resource
    private LayerDataService layerDataService;

    @PostConstruct
    public void start() {
        System.out.println("启动抽取");
        layerDataService.pushDbData2ES("LUCENEDATA", "T_DATA", "UPDATETIME", 0, 20);
    }
}
