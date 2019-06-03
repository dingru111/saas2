/******************************************************************************
 * Copyright (c) 2017.3.28 by JoyLau.                                         *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by LiuFa on 2016/10/31.
 * com.jltfisp.util
 * ROOT
 */
public final class SerializeUtils {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    /**
     * @author LiuFa
     * deserialize
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {

        Object result = null;

        if (isEmpty(bytes)) {
            return null;
        }

        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
                try {
                    result = objectInputStream.readObject();
                } catch (ClassNotFoundException ex) {
                    throw new Exception("Failed to deserialize object type", ex);
                }
            } catch (Throwable ex) {
                throw new Exception("Failed to deserialize", ex);
            }
        } catch (Exception e) {
            logger.error("Failed to deserialize", e);
        }
        return result;
    }

    public static boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

    /**
     * @author LiuFa
     * serialize
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        byte[] result = null;
        if (object == null) {
            return new byte[0];
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
            try {
                if (!(object instanceof Serializable)) {
                    throw new IllegalArgumentException(
                            SerializeUtils.class.getSimpleName() + " requires a Serializable payload "
                                    + "but received an object of type [" + object.getClass().getName() + "]");
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();
                result = byteStream.toByteArray();
            } catch (Throwable ex) {
                throw new Exception("Failed to serialize", ex);
            }
        } catch (Exception ex) {
            logger.error("Failed to serialize", ex);
        }
        return result;
    }
}
