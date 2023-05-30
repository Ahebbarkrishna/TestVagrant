import java.util.HashMap;
	class Node1 {
	    String song;
	    String user;
	    Node1 prev;
	    Node1 next;

	    public Node1(String song, String user) {
	        this.song = song;
	        this.user = user;
	        this.prev = null;
	        this.next = null;
	    }
	}

	class playedList {
	    private int capacity;
	    private int size;
	    private HashMap<String, Node1> map;
	    private Node1 head;
	    private Node1 tail;

	    public playedList(int capacity) {
	        this.capacity = capacity;
	        this.size = 0;
	        this.map = new HashMap<>();
	        this.head = null;
	        this.tail = null;
	    }

	    public void addSong(String song, String user) {
	        if (map.containsKey(user)) {
	            Node1 node = map.get(user);
	            node.song = song;
	            moveToHead(node);
	        } else {
	            Node1 newNode = new Node1(song, user);
	            map.put(user, newNode);

	            if (size == capacity) {
	                removeTail();
	            } else {
	                size++;
	            }

	            if (head == null) {
	                head = newNode;
	                tail = newNode;
	            } else {
	                newNode.next = head;
	                head.prev = newNode;
	                head = newNode;
	            }
	        }
	    }

	    public String[] getRecentlyPlayedSongs(String user) {
	        if (map.containsKey(user)) {
	            Node1 node = map.get(user);
	            moveToHead(node);
	            String[] songs = new String[size];
	            Node1 curr = head;
	            int i = 0;
	            while (curr != null) {
	                songs[i++] = curr.song;
	                curr = curr.next;
	            }
	            return songs;
	        }
	        return new String[0];
	    }

	    private void moveToHead(Node1 node) {
	        if (node == head) {
	            return;
	        }

	        if (node == tail) {
	            tail = node.prev;
	            tail.next = null;
	        } else {
	            node.prev.next = node.next;
	            node.next.prev = node.prev;
	        }

	        node.prev = null;
	        node.next = head;
	        head.prev = node;
	        head = node;
	    }

	    private void removeTail() {
	        map.remove(tail.user);
	        tail = tail.prev;
	        if (tail != null) {
	            tail.next = null;
	        } else {
	            head = null;
	        }
	    }
	}

	public class Test {
	    public static void main(String[] args) {
	    	playedList  store = new playedList (3);

	        store.addSong("S1", "User1");
	        store.addSong("S2", "User2");
	        store.addSong("S3", "User3");
	        store.addSong("S4", "User4");
	        store.addSong("S2", "User2");
	        store.addSong("S1", "User1");

	        String[] songs = store.getRecentlyPlayedSongs("User1");
	        System.out.println("User1's : ");
	        for (String song : songs) {
	            System.out.println(song);
	            
	        }
	    }
	}

