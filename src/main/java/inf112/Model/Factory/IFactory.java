package inf112.Model.Factory;

/**
 * Interface for implementing a generic factory to create different objects and screen
 */
public interface IFactory<T> {

        void addType(String key, Class<? extends T> classType);

        T create(String key, Object... params);

}
