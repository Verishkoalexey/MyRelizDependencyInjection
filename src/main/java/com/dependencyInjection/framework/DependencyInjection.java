package com.dependencyInjection.framework;

import com.dependencyInjection.framework.module.Injector;

public final class DependencyInjection {

    public DependencyInjection() {

    }

    public static BeanFactory getBeanFactory(final Injector module) {
        module.configure();
        return new BeanFactory(module);
    }
}
