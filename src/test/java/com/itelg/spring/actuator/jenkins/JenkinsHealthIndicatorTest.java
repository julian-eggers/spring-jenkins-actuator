package com.itelg.spring.actuator.jenkins;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.actuate.health.HealthIndicator;

public class JenkinsHealthIndicatorTest
{
    @Test
    @Ignore
    public void testIT()
    {
        HealthIndicator healthIndicator = new JenkinsHealthIndicator("http://server01:8080/");
        System.out.println(healthIndicator.health());
    }
    
    @Test
    public void testGetJenkinsUrl()
    {
        JenkinsHealthIndicator healthIndicator = new JenkinsHealthIndicator("http://jenkins.com");
        Assert.assertEquals("http://jenkins.com", healthIndicator.getJenkinsUrl());
    }
}