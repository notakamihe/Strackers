package sample;
import sample.models.Sticker;

public class CurrentSticker {
    static Sticker sticker;

    public static void setSticker(Sticker otherSticker) {
        sticker = otherSticker;
    }

    public static Sticker getSticker()
    {
        return sticker;
    }

    public static void clearSticker () {
        sticker = null;
    }
}
