package woopaca.toss.nextchallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Second {

    private int newFriendNumber;
    private int compensation;
    private Person targetPerson;

    public int solution(int[][] relationships, int target, int limit) {
        newFriendNumber = 0;
        compensation = 0;

        Person[] people = new Person[101];
        initializeRelationships(relationships, people);

        targetPerson = people[target];
        targetPerson.updateLevel(0);
        initializeTargetFriendsLevel();

        Queue<Person> queue = new LinkedList<>(targetPerson.getFriends());
        while (!queue.isEmpty()) {
            Person person = queue.poll();
            if (person.getLevel() >= limit) {
                continue;
            }

            insertNewFriendsIntoQueue(queue, person);
        }

        return newFriendNumber + compensation;
    }

    private void insertNewFriendsIntoQueue(Queue<Person> queue, Person person) {
        int level = person.getLevel();
        List<Person> friends = person.getFriends();
        for (Person friend : friends) {
            if (friend.getLevel() != -1) {
                continue;
            }

            friend.updateLevel(level + 1);
            queue.add(friend);
            newFriendNumber++;
            compensation += 10;
        }
    }

    private void initializeTargetFriendsLevel() {
        List<Person> targetFriends = targetPerson.getFriends();
        for (Person friend : targetFriends) {
            friend.updateLevel(1);
            compensation += 5;
        }
    }

    private void initializeRelationships(int[][] relationships, Person[] people) {
        for (int[] relationship : relationships) {
            int a = relationship[0];
            int b = relationship[1];
            if (people[a] == null) {
                people[a] = new Person();
            }
            if (people[b] == null) {
                people[b] = new Person();
            }

            Person personA = people[a];
            Person personB = people[b];
            personA.addFriend(personB);
            personB.addFriend(personA);
        }
    }

    static class Person {

        private final List<Person> friends = new ArrayList<>();
        private int level = -1;

        public Person() {
        }

        public List<Person> getFriends() {
            return friends;
        }

        public int getLevel() {
            return level;
        }

        public void addFriend(Person person) {
            if (!this.friends.contains(person)) {
                this.friends.add(person);
            }
        }

        public void updateLevel(int level) {
            this.level = level;
        }
    }
}
