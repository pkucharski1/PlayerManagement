package pawelkucharski;


import pawelkucharski.domain.Player;
import pawelkucharski.repository.IPlayerRepository;
import pawelkucharski.repository.InMemoryPlayerRepository;
import pawelkucharski.repository.OnFilePlayerRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static IPlayerRepository iPlayerRepository;

    public static void main(String[] args) {

        iPlayerRepository = new InMemoryPlayerRepository();
        boolean quit = false;

        while (!quit) {
            printMenu();
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    System.out.println("Wyjscie z programu");
                    quit = true;
                    break;
                case 2:
                    addPlayer();
                    break;
                case 3:
                    printMenuForPlayerUpdate();
                    editPlayer();
                    break;
                case 4:
                    removePlayer();
                    break;
                case 5:
                    queryPlayer();
                    break;
                case 6:
                    returnListOfPlayers();
                    break;
                case 7:
                    printMenuForRepositoryChoice();
                    repositoryChoice();
            }
        }
    }


    private static void addPlayer(){
        System.out.println("Wpisz imie gracza:");
        String name = scanner.nextLine();
        System.out.println("Wpisz nazwisko gracza:");
        String surname = scanner.nextLine();
        System.out.println("Wpisz liczbę punktów gracza");
        int points = getInt();
        System.out.println("Wpisz ID gracza");
        int id = getInt();

        Player newPlayer = iPlayerRepository.addPlayer(name,surname,points,id);
        if(newPlayer != null){
            System.out.println("Nowy gracz dodany: ID = " + id + " " + newPlayer.toString());
        }else{
            System.out.println("Nie udalo sie dodac nowego gracza - juz taki istnieje");
        }
        scanner.nextLine();
    }

    private static void returnListOfPlayers(){
        iPlayerRepository.returnListOfPlayers();
    }

    private static void removePlayer(){
        System.out.println("Wpisz ID gracza do usuniecia");
        int id = getInt();

        if(iPlayerRepository.removePlayer(id)){
            System.out.println("Gracz zostal pomyslnie usuniety");
        }else{
            System.out.println("Brak gracza o takim ID");
        }
        scanner.nextLine();
    }

    private static void queryPlayer(){
        System.out.println("Wpisz ID gracza do zwrócenia");
        int id = getInt();

        Player foundPlayer = iPlayerRepository.findPlayer(id);
        if(foundPlayer != null){
            System.out.println("Znaleziony gracz: ID = " + id + " imie = " + foundPlayer.getName() +
            " nazwisko = " + foundPlayer.getSurname() + " data utworzenia = " + foundPlayer.getDate() + " points = " + foundPlayer.getPoints());
        }else{
            System.out.println("Brak gracza o takim ID");
        }
        scanner.nextLine();
    }

    private static void editPlayer() {
        int choice = getInt();

        String updatedName;
        String updatedSurname;

        switch (choice) {
            case 1:
                System.out.println("Wpisz ID gracza do edycji");
                int id = getInt();
                scanner.nextLine();
                System.out.println("Wpisz nowe imie gracza");
                updatedName = scanner.nextLine();
                if (iPlayerRepository.editPlayer(id, updatedName)) {
                    System.out.println("Dane gracza zostaly zmienione");
                } else {
                    System.out.println("Gracz o takim ID nie istnieje");
                }
                break;

            case 2:
                System.out.println("Wpisz ID gracza do edycji");
                id = getInt();
                scanner.nextLine();
                System.out.println("Wpisz nowe imie gracza");
                updatedName = scanner.nextLine();
                System.out.println("Wpisz nowe nazwisko gracza");
                updatedSurname = scanner.nextLine();
                if (iPlayerRepository.editPlayer(id, updatedName, updatedSurname)) {
                    System.out.println("Dane gracza zostaly zmienione");
                } else {
                    System.out.println("Gracz o takim ID nie istnieje");
                }
                break;

            case 3:
                System.out.println("Wpisz ID gracza do edycji");
                id = getInt();
                scanner.nextLine();
                System.out.println("Wpisz nowe imie gracza");
                updatedName = scanner.nextLine();
                System.out.println("Wpisz nowe nazwisko gracza");
                updatedSurname = scanner.nextLine();
                System.out.println("Wpisz liczbę punktów gracza");
                int points = getInt();
                if (iPlayerRepository.editPlayer(id, updatedName, updatedSurname, points)) {
                    System.out.println("Dane gracza zostaly zmienione");
                } else {
                    System.out.println("Gracz o takim ID nie istnieje");
                }
                break;
            case 4:
                break;
        }
    }

    private static void printMenu(){
        System.out.println("\nDostepne opcje");
        System.out.println("1 - Koniec programu\n" +
                "2 - Dodaj nowego gracza\n" +
                "3 - Edytuj gracza\n" +
                "4 - Usun gracza\n" +
                "5 - Pobierz gracza\n" +
                "6 - Wyswietl listę graczy\n" +
                "7 - Zmiana repozytorium\n");
        System.out.println("Wpisz jaką akcję chcesz podjąć");
    }

    private static void printMenuForPlayerUpdate(){
        System.out.println("\nMenu edycji gracza");
        System.out.println("1 - Zmien imię\n" +
                "2 - Zmień imię i nazwisko\n" +
                "3 - Zmień imię, nazwisko i liczbę punktów\n"+
                "4 - Wyjscie z opcji edytowania gracza - powrot do menu\n");
        System.out.println("Wpisz jaką akcję chcesz podjąć");
    }

    private static void printMenuForRepositoryChoice(){
        System.out.println("\nMenu wyboru repozytorium");
        System.out.println("1 - Wybor repozytorium w pamieci\n" +
                "2 - Wybor repozytorium w pliku\n");
        System.out.println("Wpisz jaką akcję chcesz podjąć");
    }

    private static int getInt() {
        while(true) {
            try {
                return scanner.nextInt();
            } catch(InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Wpisz poprawną liczbe");
            }
        }
    }

    private static void repositoryChoice(){
        int choice = getInt();
        switch(choice){
            case 1:
                iPlayerRepository = new InMemoryPlayerRepository();
                break;
            case 2:
                iPlayerRepository = new OnFilePlayerRepository();
                break;
        }
    }
}
