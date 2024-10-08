import java.util.Iterator;
import java.util.Random;

public class FirstTo100 {
    private final CircularLinkedList<Player> players;
    private final Random random;

    public static void main(String[] args) {
        FirstTo100 game= new FirstTo100("Maddie", "Rico", "John", "Jeff");
        game.play();
    }

    public FirstTo100( String...values){
        players= new CircularLinkedList<>();
        for(String name : values){
            players.add(new Player(name));
        }
        random = new Random();
    }

    public void play() {
        boolean PLAY_ROUND = true;
        if (players.size() == 0) {
            return;
        }
        Iterator<Player> itr = players.iterator();
        Player person = itr.next();
        int looper = random.nextInt(players.size());
        for (int i = 0; i <= looper; i++) {
            person = itr.next();
        }
        int count=0;
        int saver=0;
        System.out.println("LET THE GAME BEGIN!");
        System.out.println();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("New Round Starting");
        while (PLAY_ROUND) {

            if(count==players.size()){
                System.out.println();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("New Round Starting");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                count=0;
            }
            int firstScore = rollDice();
            int secondScore = rollDice();
            person.addScore(firstScore + secondScore);
            if(person.getScore()>saver){
                System.out.println(person.getName() + " rolls a " + firstScore + " and a " +
                        secondScore +  " now totaling " + person.getScore() +
                        "...new high player!");
                saver=person.getScore();
            }else{
                System.out.println(person.getName() + " rolls a " + firstScore + " and a " +
                        secondScore + " now totaling " + person.getScore());
            }
            if (person.getScore() >= 100) {
                System.out.println();
                System.out.println("The winner is " + person.getName() + " with a score of " +
                        person.getScore() + "!");
                PLAY_ROUND = false;
            }
            if (!itr.hasNext()) {
                itr = players.iterator();
            }
            count++;
            person = itr.next();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        }
    }

    private int rollDice(){
        return random.nextInt(6)+1;
    }
}
