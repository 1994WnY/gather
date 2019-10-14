/**
 * Copyright (C), 2018-2019, 肥宅股份有限公司
 * FileName: RibbonConfiguration
 * Author:   王呐宇
 * Date:     2019/7/20 19:22
 * Description: Ribbon的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 *  王呐宇          2018-12-31         1.0.0             写着玩
 */
package com.botech.demoalgorithm.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Ribbon的配置类〉
 *
 * @author 王呐宇
 * @date 2019/7/20
 * @since 1.0.0
 */
@Configuration
public class RibbonConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}