package pawelkucharski.repository;


import pawelkucharski.domain.Player;

public interface IPlayerRepository {
    Player addPlayer(String name, String surname, int points, int id);

    boolean editPlayer(int id, String name);

    boolean editPlayer(int id, String name, String surname);

    boolean editPlayer(int id, String name, String surname, int points);

    boolean removePlayer(int id);

    void returnListOfPlayers();

    Player findPlayer(int id);
}
