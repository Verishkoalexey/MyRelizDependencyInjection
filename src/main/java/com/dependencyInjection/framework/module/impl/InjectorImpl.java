package com.dependencyInjection.framework.module.impl;


import com.dependencyInjection.framework.module.Injector;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract module to configure and retrieve proper dependency injection mapping.
 *
 *
 */
public abstract class InjectorImpl implements Injector {

    private final Map<Class<?>, Class<?>> classMap = new HashMap<>();

    @Override
    public <T> Class<? extends T> getProvider(Class<T> type) {
        final Class<?> implementation = classMap.get(type);
        if (implementation == null) {
            return null;
        }
        return implementation.asSubclass(type);
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {
        classMap.put(intf, impl.asSubclass(intf));
    }

}
