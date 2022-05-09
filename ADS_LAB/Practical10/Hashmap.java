public class Hashmap {

    private final static int TABLE_SIZE = 10;

    HashLinkNode[] table;

    public Hashmap() {
        table = new HashLinkNode[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public int get(int key) {
        int hash = (key % TABLE_SIZE);
        if (table[hash] == null)
            return -1;
        else {

            HashLinkNode entry = table[hash];
            while (entry != null && entry.getKey() != key)
                entry = entry.getNext();
            if (entry == null)
                return -1;
            else
                return entry.getValue();
        }
    }

    public void put(int key, int value) {

        int hash = (key % TABLE_SIZE);

        if (table[hash] == null)

            table[hash] = new HashLinkNode(key, value);

        else {
            HashLinkNode entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key)
                entry = entry.getNext();

            if (entry.getKey() == key)
                entry.setValue(value);
            else
                entry.setNext(new HashLinkNode(key, value));
        }
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            HashLinkNode temp = table[i];
            System.out.print(i + " : ");
            while (temp != null) {
                System.out.print(temp.getKey() + " -> " + temp.getValue() + " , ");
                temp = temp.getNext();
            }
            System.out.println("");
        }
    }

    public void remove(int key) {

        int hash = (key % TABLE_SIZE);

        if (table[hash] != null) {

            HashLinkNode prevEntry = null;

            HashLinkNode entry = table[hash];

            while (entry.getNext() != null && entry.getKey() != key) {
                prevEntry = entry;
                entry = entry.getNext();
            }

            if (entry.getKey() == key) {
                if (prevEntry == null)
                    table[hash] = entry.getNext();
                else
                    prevEntry.setNext(entry.getNext());

            }
        }
    }
}