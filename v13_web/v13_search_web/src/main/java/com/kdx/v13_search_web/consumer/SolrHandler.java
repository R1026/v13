package com.kdx.v13_search_web.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kdx.api.ISearchService;
import com.kdx.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Component
public class SolrHandler {

    @Reference
    private ISearchService searchService;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.SOLR_ADD_MESSAGE_QUEUE)
    public void processProduct(int id){
        System.out.println(id);
        searchService.updateById(id);
    }

}
