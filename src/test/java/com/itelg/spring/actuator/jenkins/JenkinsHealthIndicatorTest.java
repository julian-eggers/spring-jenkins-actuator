package com.itelg.spring.actuator.jenkins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

public class JenkinsHealthIndicatorTest
{
    @Ignore
    @Test
    public void testWithUpAndWithAuthentication()
    {
        HealthIndicator healthIndicator = new JenkinsHealthIndicator("http://192.168.2.110:8080/", "admin", "d4920417fc6842039222df91b8c68be5");
        Health health = healthIndicator.health();
        assertEquals(Status.UP, health.getStatus());
        assertNotNull(health.getDetails().get("version"));
    }

    @Test
    public void testWithUpAndWithoutAuthentication()
    {
        HealthIndicator healthIndicator = new JenkinsHealthIndicator("https://jenkins.mono-project.com/");
        Health health = healthIndicator.health();
        assertEquals(Status.UP, health.getStatus());
        assertNotNull(health.getDetails().get("version"));
    }

    @Test
    public void testWithDown()
    {
        HealthIndicator healthIndicator = new JenkinsHealthIndicator("http://jenkins.com/");
        Health health = healthIndicator.health();
        assertEquals(Status.DOWN, health.getStatus());
        assertNull(health.getDetails().get("version"));
    }

    @Test
    public void testGetters()
    {
        JenkinsHealthIndicator healthIndicator = new JenkinsHealthIndicator("http://192.168.2.110:8080/", "admin", "d4920417fc6842039222df91b8c68be5");
        assertEquals("http://192.168.2.110:8080/", healthIndicator.getUrl());
        assertEquals("admin", healthIndicator.getUsername());
        assertEquals("d4920417fc6842039222df91b8c68be5", healthIndicator.getPassword());
    }
}