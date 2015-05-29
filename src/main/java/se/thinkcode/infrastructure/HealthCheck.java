package se.thinkcode.infrastructure;

public class HealthCheck extends com.codahale.metrics.health.HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
