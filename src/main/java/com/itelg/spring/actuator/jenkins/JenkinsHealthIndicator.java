package com.itelg.spring.actuator.jenkins;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;

import com.offbytwo.jenkins.JenkinsServer;

public class JenkinsHealthIndicator extends AbstractHealthIndicator
{
    private String url;

    private String username;

    private String password;

    public JenkinsHealthIndicator(String url)
    {
        this.url = url;
    }

    public JenkinsHealthIndicator(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception
    {
        JenkinsServer jenkins = createJenkinsServer();

        if (jenkins.isRunning())
        {
            String version = jenkins.getVersion().getLiteralVersion();
            builder.up().withDetail("version", version);
        }
        else
        {
            builder.down();
        }
    }

    private JenkinsServer createJenkinsServer() throws URISyntaxException
    {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password))
        {
            return new JenkinsServer(new URI(url), username, password);
        }

        return new JenkinsServer(new URI(url));
    }
}