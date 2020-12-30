package com.cy.asset.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author wc
 * @date 2020/12/30
 * @describe WebSocket配置项
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter () {
        return new ServerEndpointExporter();
    }

}
