package com.github.elwinbran.gamebacklog;

public enum GameStatus
{
    WANT_TO_PLAY("Want to play"),
    PLAYING("Playing"),
    STALLED("Stalled"),
    DROPPED("Dropped");

    private final String text;

    GameStatus(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }
}
