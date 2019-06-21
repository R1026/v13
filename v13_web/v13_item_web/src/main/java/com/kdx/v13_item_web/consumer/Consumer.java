package com.kdx.v13_item_web.consumer;

import com.kdx.common.constant.RabbitMQConstant;
import com.kdx.common.util.HttpClientUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConstant.ITEM_QUEUE)
    @RabbitHandler
    public void processItem(String str) throws IOException {

        String result = HttpClientUtil.doGet(str);
        System.out.println(result);
    }

}
