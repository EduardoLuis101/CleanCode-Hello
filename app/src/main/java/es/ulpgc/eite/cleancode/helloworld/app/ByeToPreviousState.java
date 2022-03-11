package es.ulpgc.eite.cleancode.helloworld.app;

import java.util.Objects;

public class ByeToPreviousState {

    public String message;

    public ByeToPreviousState(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ByeToPreviousState that = (ByeToPreviousState) obj;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "message: " + message;
    }
}
