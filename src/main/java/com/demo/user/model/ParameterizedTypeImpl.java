package com.demo.user.model;

import java.lang.reflect.Type;

public class ParameterizedTypeImpl implements java.lang.reflect.ParameterizedType {

    private final Class raw;
    private final Type[] args;


    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
