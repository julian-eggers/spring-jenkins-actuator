package com.itelg.spring.actuator.jenkins;

import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;

public class JenkinsHealthIndicator extends AbstractHealthIndicator
{
    private static final Logger log = LoggerFactory.getLogger(JenkinsHealthIndicator.class);
    private String jenkinsUrl;

    public JenkinsHealthIndicator(String jenkinsUrl)
    {
        this.jenkinsUrl = jenkinsUrl;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception
    {
        HttpURLConnection connection = null;
        builder.up();
        
        try
        {
            URL url = new URL(jenkinsUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int statusCode = connection.getResponseCode();
            builder.withDetail("statusCode", Integer.valueOf(statusCode));
            
            if (statusCode == 200)
            {
                builder.withDetail("version", connection.getHeaderField("X-Jenkins"));
            }
        }
        catch (Exception e)
        {
            builder.down();
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }
    
    public String getJenkinsUrl()
    {
        return jenkinsUrl;
    }
}