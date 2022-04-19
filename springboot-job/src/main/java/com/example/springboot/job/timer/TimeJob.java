package com.example.springboot.job.timer;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class TimeJob implements SimpleJob {

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()){
            case 1:
                log.info("时间是-分片1：{}",sdf.format(new Date()));
                break;
            case 2:
                log.info("时间是-分片2：{}",sdf.format(new Date()));
                break;
            case 0:
                log.info("时间是-分片3：{}",sdf.format(new Date()));
                break;
        }

    }
}
