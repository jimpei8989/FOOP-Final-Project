package model;

public interface Tickable {
    void onTurnBegin();

    void onTurnEnd();

    void onRoundBegin();

    void onRoundEnd();
}
