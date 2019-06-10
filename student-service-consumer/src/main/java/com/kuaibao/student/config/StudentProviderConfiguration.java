package com.kuaibao.student.config;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class StudentProviderConfiguration {

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        /*RoundRobinRule rule = new RoundRobinRule();
        rule.initWithNiwsConfig(config);
        return rule;*/
        return new RandomRule();
    }
}
