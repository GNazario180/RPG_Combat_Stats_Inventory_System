package game.item;

import game.player.Player;

public interface ItemType {
    void use(Player player);
    void unuse(Player player);
}