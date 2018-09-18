package pawelkucharski.repository;

import pawelkucharski.domain.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryPlayerRepository implements IPlayerRepository {

    private Map<Integer,Player> listOfPlayers;

    public InMemoryPlayerRepository(){
        listOfPlayers = new LinkedHashMap<>();
    }

    @Override
    public Player addPlayer(String name, String surname, int points, int id){
        Player player = new Player(name,surname,points,id);

        if(!listOfPlayers.containsKey(id)){
            listOfPlayers.put(id,player);
            return player;
        }
        return null;
    }

    @Override
    public boolean editPlayer(int id, String name){
        if(listOfPlayers.containsKey(id)) {
            Player playerToEdit = findPlayer(id);
            playerToEdit.setName(name);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean editPlayer(int id, String name, String surname){
        if(listOfPlayers.containsKey(id)) {
            Player playerToEdit = findPlayer(id);
            playerToEdit.setName(name);
            playerToEdit.setSurname(surname);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean editPlayer(int id, String name, String surname, int points){
        if(listOfPlayers.containsKey(id)) {
            Player playerToEdit = findPlayer(id);
            playerToEdit.setName(name);
            playerToEdit.setSurname(surname);
            playerToEdit.setPoints(points);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean removePlayer(int id){
        if(listOfPlayers.containsKey(id)){
            listOfPlayers.remove(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void returnListOfPlayers() {
        if(!listOfPlayers.isEmpty()) {
            System.out.println("List of players:");
            for (Integer key : listOfPlayers.keySet()) {
                //for(Map.Entry<Integer,Player> entry : listOfPlayers.entrySet()){
                System.out.println("ID = " + key + " " + listOfPlayers.get(key));
            }
        }else{
            System.out.println("Nie ma zadnych graczy w liscie");
        }
    }

    @Override
    public Player findPlayer(int id){
        if(listOfPlayers.containsKey(id)) {
            Player foundPlayer = listOfPlayers.get(id);
            return foundPlayer;
        }
        return null;
    }
}
