package com.msa_ecommerce.CustomerService.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;

@Configuration
public class ServerConfiguration {

    @Bean
    public GrpcServerConfigurer serverConfigurer() {
        return serverBuilder -> serverBuilder.executor(Executors.newVirtualThreadPerTaskExecutor());
    }
}
