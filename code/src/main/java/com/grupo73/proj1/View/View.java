package com.grupo73.proj1.View;

import java.io.IOException;

public interface View<T>  {

     void draw(T model) throws IOException;
}
