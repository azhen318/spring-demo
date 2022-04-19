package com.example.springboot.job.timer;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class singleJob implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("单例定时任务...");
    }
}
