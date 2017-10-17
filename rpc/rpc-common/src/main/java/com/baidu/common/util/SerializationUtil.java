package com.baidu.common.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by tingyun on 2017/10/17.
 * 序列化工具类（基于 Protostuff 实现）
 */
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    public SerializationUtil() {
    }

    /**
     * 反序列化（字节数组 -> 对象）
     *
     * @param data
     * @param genericClass
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> genericClass) {
        //return data;
        try {
            T message = objenesis.newInstance(genericClass);
            Schema<T> schema = getSchema(genericClass);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

    }

    /**
     * 序列化（对象 -> 字节数组）
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, linkedBuffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            linkedBuffer.clear();
        }
    }

    private static <T> Schema<T> getSchema(Class<T> genericClass) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(genericClass);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(genericClass);
            cachedSchema.put(genericClass, schema);
        }
        return schema;
    }


}
