/*******************************************************************************
 * Copyright (c) 2017 by JoyLau. All rights reserved
 ******************************************************************************/

package com.gta.train.platform.persis.mybatis.plugin;

public class MapperException extends RuntimeException {
    public MapperException() {
        super();
    }

    public MapperException(String message) {
        super(message);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperException(Throwable cause) {
        super(cause);
    }

}
