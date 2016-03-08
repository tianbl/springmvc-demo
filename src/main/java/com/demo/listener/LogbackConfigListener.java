package com.demo.listener;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LogbackConfigListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);

    private static final String CONFIG_LOCATION = "logbackConfigLocation";

    public void contextInitialized(ServletContextEvent event) {
        String logbackConfigLocation = event.getServletContext().getInitParameter(CONFIG_LOCATION);
        String fn = event.getServletContext().getRealPath(logbackConfigLocation);
        System.out.println("---------------->logbackConfigLocation=="+logbackConfigLocation);
        System.out.println("---------------->fn==" + fn);
        if(fn==null){
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>找不到log配置文件");
        }else if(fn.endsWith(".properties")){
            PropertyConfigurator.configure(fn);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>使用.properties配置文件");
        }else if(fn.endsWith(".xml")){
            try {
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                loggerContext.reset();
                JoranConfigurator joranConfigurator = new JoranConfigurator();
                joranConfigurator.setContext(loggerContext);
                joranConfigurator.doConfigure(fn);
                logger.debug("loaded slf4j configure file from {}", fn);
            } catch (JoranException e) {
                logger.error("can loading slf4j configure file from " + fn, e);
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>使用xml文集配置项目日志");
        }else {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>找不到合适的配置文件");
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}