package net.engining.sccc.batch.slave;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.engining.gm.facility.SystemStatusFacility;
import net.engining.pcx.cc.process.service.support.BatchProvider;
import net.engining.pcx.cc.process.service.support.Provider7x24;
import net.engining.sccc.batch.config.BatchContextConfig;
import net.engining.sccc.batch.config.BatchIntegrationContextConfig;
import net.engining.sccc.batch.config.ResourceContextConfig;
import net.engining.sccc.batch.config.TaskExecutorContextConfig;
import net.engining.sccc.config.GeneralContextConfig;
import net.engining.sccc.config.JPAContextConfig;
import net.engining.sccc.config.ValidatorContextConfig;
import net.engining.sccc.config.props.BatchTaskProperties;
import net.engining.sccc.config.props.CommonProperties;

/**
 * 这个类用来组装需要的配置，根据不同的项目组装需要的配置项 <br>
 * 使用@Import用来导入@Configuration注解的config类(也可以通过@SpringBootApplication指定scanBasePackages来扫描@Configuration) <br>
 * 使用@ImportResource用来加载传统的xml配置
 * 
 * @author Eric Lu
 *
 */
@Configuration
//显式的指定具体的Properties类，不通过扫描的方式，更清晰；
//另也不需要在@ConfigurationProperties注解的自定义Properties类上加@Component
@EnableConfigurationProperties(value = { 
		CommonProperties.class,
		BatchTaskProperties.class
		})
//显式的指定具体的@Configuration类，不通过扫描的方式，更清晰；
@Import(value = {
		GeneralContextConfig.class,
		JPAContextConfig.class,
		ValidatorContextConfig.class,
		TaskExecutorContextConfig.class,
		BatchIntegrationContextConfig.class,
		BatchContextConfig.class,
		ResourceContextConfig.class
		})
@ComponentScan(
		basePackages = {
				"net.engining.pcx.cc.process.service",
				"net.engining.sccc.biz.service",
				"net.engining.sccc.batch"
		})
@EntityScan(basePackages = {
		"net.engining.pg.parameter.entity",
		"net.engining.pg.batch.entity",
		"net.engining.gm.entity",
		"net.engining.pcx.cc.infrastructure",
		"net.engining.profile.entity"
	})
public class CombineConfiguration {

	@Bean
	public SystemStatusFacility systemStatusFacility(){
		SystemStatusFacility systemStatusFacility = new SystemStatusFacility();
		return systemStatusFacility;
		
	}
	
	@Bean
	public Provider7x24 provider7x24(){
		BatchProvider provider7x24 = new BatchProvider();
		return provider7x24;
	}
	
	@Bean
	@ConditionalOnClass(value={RemoteIpFilter.class})
	public RemoteIpFilter remoteIpFilter(){
		return new RemoteIpFilter();
	}
}
