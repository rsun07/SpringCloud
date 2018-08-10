package pers.xiaoming.springcloud.hystrix.http;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import pers.xiaoming.springcloud.entity.Student;

// A traditional Hystrix Command impl without using spring cloud,
// just extend com.netflix.hystrix.HystrixCommand
@Slf4j
public class GetStudentById extends HystrixCommand<Student> {

    private static final String REST_URL_HOST = "http://localhost:8001";
    private static final String STUDENT_RESOURCE = "/student";

    // Hystrix using Command Group to define thread-pool by default
    private static final String HYSTRIX_COMMAND_GROUP_KEY = "READ_GROUP";
    private static HystrixCommand.Setter hystrixSetter;

    // Hystrix Setter for config
    static {
        hystrixSetter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(HYSTRIX_COMMAND_GROUP_KEY))
                .andCommandKey(HystrixCommandKey.Factory.asKey(HYSTRIX_COMMAND_GROUP_KEY))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(false)
                        .withFallbackEnabled(true)
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerRequestVolumeThreshold(20)
                        .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)
                        .withCircuitBreakerErrorThresholdPercentage(30))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(16)
                        .withQueueSizeRejectionThreshold(16));

                // use the following config is use Semaphore rather than thread pool
                /*
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(
                            HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionTimeoutEnabled(false)
                        .withFallbackEnabled(true)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(16)
                 */
    }

    private RestTemplate restTemplate;
    private int id;

    public GetStudentById(RestTemplate restTemplate, int id) {
        super(hystrixSetter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Student run() throws Exception {
        return restTemplate.getForObject(REST_URL_HOST + STUDENT_RESOURCE + "/id/" + id, Student.class);
    }

    /**
     *
     * @return Student (id=0, name="", score=0.0)
     */
    @Override
    protected Student getFallback() {
        log.error("Fall back and return blank student");
        return new Student("", 0.0);
    }
}
