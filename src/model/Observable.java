package model;

import server.EventListener;

public interface Observable {
    void subscribe(EventListener eventListener);
    void unsubscribe(EventListener eventListener);
}

