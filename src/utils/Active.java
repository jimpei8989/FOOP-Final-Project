package utils;

public interface Active {
    // an instance is inactive when it is semantically *freed*
    boolean isActive();
}
