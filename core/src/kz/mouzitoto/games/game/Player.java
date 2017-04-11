package kz.mouzitoto.games.game;

/**
 * Created by Mouzitoto on 11.04.2017.
 */
public class Player {
    private String id;
    private String name;
    private int cardsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardsCount() {
        return cardsCount;
    }

    public void setCardsCount(int cardsCount) {
        this.cardsCount = cardsCount;
    }
}
