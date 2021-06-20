package model;

import java.util.List;

public class World {
    private List<Tickable> tickableObjects;

    public void update() {
        for (Tickable tickable : tickableObjects)
            tickable.onRoundBegin();
        for (Tickable tickable : tickableObjects)
            tickable.onRoundEnd();
    }

    public void addTickable(Tickable tickable) {
        tickableObjects.add(tickable);
    }
}
