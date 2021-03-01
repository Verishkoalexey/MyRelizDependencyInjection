package com.dependencyInjection.framework.module;

/**
 * Module interface to configure dependency injection mapping.
 *
 */
public interface Injector {

    default void configure() {

    }

    <T> Class<? extends T> getProvider(Class<T> type);

    <T> void bind(Class<T> intf, Class<? extends T> impl);


}
