package com.e_connect.part_service.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ServerAddress;
import com.mongodb.MongoClientSettings.Builder;
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  public String getDatabaseName() {
    return "E-Connect-Demo";
  }

  @Override
  protected void configureClientSettings(Builder builder) {

    builder
        .applyToClusterSettings(settings  -> {
          settings.hosts(List.of(new ServerAddress("127.0.0.1", 27017)));
        });
  }
}