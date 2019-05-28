package help.me.understand.jsf.ViewScopeDemo;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer<Object> {
    @Override
    public int getTypeId() {
        return 2;
    }

    @Override
    public void write(ObjectDataOutput objectDataOutput, Object object)
            throws IOException {
        ObjectOutputStream out = new ObjectOutputStream((OutputStream) objectDataOutput);
        out.writeObject(object);
        out.flush();
    }

    @Override
    public Object read(ObjectDataInput objectDataInput) throws IOException {
        ObjectInputStream in = new ObjectInputStream((InputStream) objectDataInput);
        try {
            return in.readObject();
        }
        catch (ClassNotFoundException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public void destroy() {
    }

}
