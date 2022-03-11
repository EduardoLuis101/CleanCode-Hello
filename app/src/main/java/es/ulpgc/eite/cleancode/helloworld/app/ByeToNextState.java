package es.ulpgc.eite.cleancode.helloworld.app;

import java.util.Objects;

public class ByeToNextState {

    public String message;

    public ByeToNextState(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ByeToNextState that = (ByeToNextState) obj;
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
