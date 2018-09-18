package pawelkucharski.repository;

import pawelkucharski.domain.Player;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnFilePlayerRepository implements IPlayerRepository {

    @Override
    public Player addPlayer(String name, String surname, int points, int id) {
        Map<Integer,Player> tempList = retrievePlayers();
        Player player = new Player(name,surname,points,id);
        if(!tempList.containsKey(id)) {
            try (BufferedWriter playersFile = new BufferedWriter(new FileWriter("players.txt",true))) {
                playersFile.write(name + "," + surname + "," + points + "," + id + "\r\n");
                return player;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            System.out.println("Gracz o takim ID juz istnieje - gracz nie zostal dodany do pliku");
            return null;
        }
    }

    @Override
    public boolean editPlayer(int id, String name) {
        Map<Integer,Player> tempList = retrievePlayers();
        if(tempList.containsKey(id)){
            Player playerToEdit = tempList.get(id);
            playerToEdit.setName(name);
            tempList.put(id,playerToEdit);
            writePlayers(tempList);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean editPlayer(int id, String name, String surname) {
        Map<Integer,Player> tempList = retrievePlayers();
        if(tempList.containsKey(id)){
            Player playerToEdit = tempList.get(id);
            playerToEdit.setName(name);
            playerToEdit.setSurname(surname);
            tempList.put(id,playerToEdit);
            writePlayers(tempList);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean editPlayer(int id, String name, String surname, int points) {
        Map<Integer,Player> tempList = retrievePlayers();
        if(tempList.containsKey(id)){
            Player playerToEdit = tempList.get(id);
            playerToEdit.setName(name);
            playerToEdit.setSurname(surname);
            playerToEdit.setPoints(points);
            tempList.put(id,playerToEdit);
            writePlayers(tempList);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean removePlayer(int id) {
        Map<Integer,Player> tempList = retrievePlayers();
        if(tempList.containsKey(id)){
            tempList.remove(id);
            writePlayers(tempList);
            return true;
        }else{
            System.out.println("Gracz o podanym ID nie istnieje");
            return false;
        }
    }

    @Override
    public void returnListOfPlayers() {
        try (BufferedReader playersFile = new BufferedReader(new FileReader("players.txt"))) {
            System.out.println("List of players:");
            String input;
            while ((input = playersFile.readLine()) != null) {
                String[] data = input.split(",");
                String imie = data[0];
                String nazwisko = data[1];
                int points = Integer.parseInt(data[2]);
                int id = Integer.parseInt(data[3]);
                Player player = new Player(imie,nazwisko,points,id);
                System.out.println(player.toString());
            }
        } catch (IOException e) {
            System.out.println("Zaden gracz nie znajduje się na liście");
        }
    }

    @Override
    public Player findPlayer(int id) {
        Player foundPlayer = null;
        try (BufferedReader playersFile = new BufferedReader(new FileReader("players.txt"))) {
            String input;
            while ((input = playersFile.readLine()) != null) {
                String[] data = input.split(",");
                String imie = data[0];
                String nazwisko = data[1];
                int points = Integer.parseInt(data[2]);
                if (id == Integer.parseInt(data[3])) {
                    foundPlayer = new Player(imie, nazwisko, points, id);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return foundPlayer;
    }


    private Map<Integer, Player> retrievePlayers() {
        Map<Integer, Player> listOfPlayers = new LinkedHashMap<>();
        try (BufferedReader playersFile = new BufferedReader(new FileReader("players.txt"))) {
            String input;
            while ((input = playersFile.readLine()) != null) {
                String[] data = input.split(",");
                String imie = data[0];
                String nazwisko = data[1];
                int points = Integer.parseInt(data[2]);
                int id = Integer.parseInt(data[3]);
                Player player = new Player(imie,nazwisko,points,id);
                listOfPlayers.put(id,player);
            }
        } catch (IOException e) { //
            //System.out.println("Lista z graczami nie istnieje - brak pliku");
            //e.printStackTrace();
            //System.out.println(e.getMessage());
        }

        return listOfPlayers;
    }

    private void writePlayers(Map<Integer,Player> tempList){
        try(BufferedWriter playersFile = new BufferedWriter(new FileWriter("players.txt"))){
            for(Player player : tempList.values()){
                playersFile.write(player.getName() + "," + player.getSurname() + "," + player.getPoints() + "," + player.getId() + "\r\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
