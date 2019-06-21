package com.grupo73.proj1.Controller;

import com.grupo73.proj1.View.Event;

import java.io.IOException;

public interface Controller<T> {

    void consumeEvent(Event event, T model) throws IOException;
}
