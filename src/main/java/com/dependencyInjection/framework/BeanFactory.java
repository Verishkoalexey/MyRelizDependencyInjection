package com.dependencyInjection.framework;

import com.dependencyInjection.framework.annotation.Inject;
import com.dependencyInjection.framework.exeptions.ConstructorNotFoundException;
import com.dependencyInjection.framework.exeptions.TooManyConstructorsException;
import com.dependencyInjection.framework.module.Injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BeanFactory {
    private final Injector module;

    public BeanFactory(final Injector module) {
        this.module = module;
    }

    public Object inject(final Class<?> classToInject) throws Exception{
        if (classToInject == null) {
            return null;
        }
        return injectFieldsIntoClass(classToInject);
    }

    private Object injectFieldsIntoClass(final Class<?> classToInject)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        int countConstructor = 0;
        for (Constructor<?> constructor : classToInject.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                countConstructor++;
            }
        }
        if (countConstructor > 1){
            return new TooManyConstructorsException();
        }
        if (countConstructor == 1){
            for (Constructor<?> constructor : classToInject.getConstructors()) {
                if (constructor.isAnnotationPresent(Inject.class)){
                    return injectFieldsViaConstructor(classToInject,constructor);
                }
            }
        }
        return new ConstructorNotFoundException();
    }

    private Object injectFieldsViaConstructor(Class<?> classToInject, Constructor<?> constructor) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final Object[] objArr = new Object[parameterTypes.length];
        int i = 0;
        for (final Class<?> c : parameterTypes) {
            final Class<?> dependency = module.getProvider(c);
            if (c.isAssignableFrom(dependency)) {
                objArr[i++] = dependency.getConstructor().newInstance();
            }
        }
        return classToInject.getConstructor(parameterTypes).newInstance(objArr);
    }
}
