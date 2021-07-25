package tw.com.demo.generator.id;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
public class SimpleCaseIdGenerator implements CaseIdGenerator {
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public String genCaseId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
