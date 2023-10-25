package io.github.hrashk;

import org.junit.jupiter.api.AfterEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

abstract class ContextAware {
    protected AnnotationConfigApplicationContext ctx;

    @AfterEach
    public void closeContext() {
        if (ctx != null)
            ctx.close();
    }
}
