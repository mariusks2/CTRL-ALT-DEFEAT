package inf112.Model.Factory;

/**
 * Interface for implementing a generic factory to create different objects and screens
 * @param <T> The type of objects or screens to be created by the factory
 */
public interface IFactory<T> {
        
        /**
         * Adds a type to the factory with the specified key and class type
         * @param key The key associated with the type to be added
         * @param classType The class type of the object or screen to be associated with the key
         */
        void addType(String key, Class<? extends T> classType);

        /**
         * Creates an object or screen based on the provided key and added parameters
         * @param key The key associated with the type of object or screen we're trying to create
         * @param params Parameters used for creating the different objects and screens
         * @return The created object or screen
         */
        T create(String key, Object... params);

}
