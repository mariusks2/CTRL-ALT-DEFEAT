package inf112.Model.Factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map; // Make sure this is java.util.Map

/**
 * Abstract class for generic factory.
 */
public abstract class GenericFactory<T> implements IFactory<T> {

    // Variables
    protected Map<String, Class<? extends T>> registeredType = new HashMap<>();

    @Override
    public void addType(String type, Class<? extends T> class1) {
        if (registeredType.containsKey(type)) {
            throw new IllegalArgumentException("Type " + type + " is already registered.");
        }
        registeredType.put(type, class1);
    }

    @Override
    public T create(String type, Object... params) {
        Class<? extends T> class1 = registeredType.get(type);
        if (class1 == null) {
            throw new IllegalArgumentException("No type registered for " + type);
        }
        try {
            Class<?>[] paramTypes = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass();
            }
            Constructor<? extends T> constructor = class1.getConstructor(paramTypes);

            // Pass the actual parameters to newInstance
            return constructor.newInstance(params); // Here was the missing part
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate type: " + type, e);
        }
    }
}
