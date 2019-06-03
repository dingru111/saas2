package com.gta.train.platform.config.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:cache.xml"})
public class CacheConfiguration {

}
