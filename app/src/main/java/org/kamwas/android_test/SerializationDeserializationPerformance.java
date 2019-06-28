package org.kamwas.android_test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationDeserializationPerformance {

    public byte[] serialize(User user) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(user);
        out.close();
        return bos.toByteArray();
    }

    public User deserialize(byte[] user) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(user);
        ObjectInputStream in = new ObjectInputStream(bis);
        return (User) in.readObject();
    }

}
