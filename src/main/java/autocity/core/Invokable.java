package autocity.core;

/**
 * Created by Whiplash on 1/19/2015.
 */
public interface Invokable {

    final String delimiter = "\\.";

    void Execute(String command);
}
