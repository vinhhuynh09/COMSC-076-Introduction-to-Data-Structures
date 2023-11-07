/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #11 Hashing
 * Course: COMSC 076. Summer 2023
 * Date: July 24, 2023
 ***********************************************************************************************************************
 * Create a new concrete class that implements MyMap (Listing 27.1) using open addressing with quadratic probing.
 * For simplicity, use f(key) = key % size as the hash function, where size is the hash-table size.
 * Initially, the hash-table size is 4. The table size is doubled whenever the load factor exceeds the threshold (0.5).
 * Write a driver program to test your class.
 * This program is chapter 27 Exercise 2.
 **********************************************************************************************************************/

public class Program11 {
    public static void main(String[] args) {
        MyMap<String, Integer> map = new MyHashMap<String, Integer>();
        // NBA players - 7 of them
        map.put("LeBron", 38);  // "name", age
        map.put("Leonard", 31);
        map.put("Harden", 33);
        map.put("George", 32);
        map.put("Jordan", 60);
        map.put("Curry", 34);
        map.put("Paul", 37);

        System.out.println("Is map empty?: " + map.isEmpty());
        System.out.println("Map Size: " + map.size());
        System.out.println("Entries in map (name, age): " + map);

        System.out.println("Names in map: " + map.keySet());
        System.out.println("Values(age) in map: " + map.values());
        System.out.println("The age for " + "Curry is " + map.get("Curry"));

        System.out.println("Is LeBron in the map? " + map.containsKey("LeBron"));
        System.out.println("Is age 57 in the map? " + map.containsValue(57));

        System.out.println("Is age 60 in the map? " + map.containsValue(60));

        System.out.println("map.entrySet: " + map.entrySet());

        map.remove("George");
        System.out.println("Entries in map after removing George: " + map);

        map.put("Bryant", 41);  // put in Kobe Bryant
        System.out.println("Map Size: " + map.size());
        System.out.println("Entries in map (name, age): " + map); // check if George was removed and Bryant is in
        map.clear();
        System.out.println("Entries in map after map.clear(): " + map);
        System.out.println("Map Size: " + map.size());
    }   // end main

    /********************************************************************************
     * static class MyHashMap<K, V> implements MyMap<K, V>
     ********************************************************************************/
    static class MyHashMap<K, V> implements MyMap<K, V> {
        private static int HASH_TABLE_SIZE = 4; // Per requirement. Size must be power of 2

        private static int MAX_HASH_TABLE_SIZE = 1 << 30; // maximum hash table size. 1 << 30 is same as 2^30
        private int capacity;    // Current hash table capacity. Capacity is a power of 2
        private static float MAX_LOAD_FACTOR = 0.5f;    // Define default load factor
        private float loadFactorThreshold;  // load factor used in the hash table
        private int size = 0;

        // Hash table is an array with each cell that is a linked list
        MyMap.Entry<K, V>[] table;

        /** Construct a map with the default capacity and load factor */
        public MyHashMap() {
            this(HASH_TABLE_SIZE, MAX_LOAD_FACTOR);
        }

        /** Construct a map with the specified initial capacity and default load factor */
        public MyHashMap(int initialCapacity) {
            this(initialCapacity, MAX_LOAD_FACTOR);
        }

        /** Construct a map with the specified initial capacity and load factor */
        public MyHashMap(int initialCapacity, float loadFactorThreshold) {
            if (initialCapacity > MAX_HASH_TABLE_SIZE) {
                this.capacity = MAX_HASH_TABLE_SIZE;
            }
            else {
                this.capacity = trimToPowerOf2(initialCapacity);
            }
            this.loadFactorThreshold = loadFactorThreshold;
            table = new MyMap.Entry[capacity];
        }

        @Override
        /** Remove all of the entries from this map */
        public void clear() {
            size = 0;
            removeEntries();
        }

        @Override
        /** Return true if the specified key is in the map */
        public boolean containsKey(K key) {
            int i = hash(key.hashCode());
            int startI = i;
            int j = 1;
            while (table[i] != null) {
                if (table[i].getKey().equals(key))
                    return true;
                i = (startI + j * j) % capacity;
                j++;
            }
            return false;
        }

        @Override
        /** Return true if this map contains the value */
        public boolean containsValue(V value) {
            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    if (table[i].getValue().equals(value)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        /** Return a set of entries in the map */
        public java.util.Set<MyMap.Entry<K, V>> entrySet() {
            java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();

            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    set.add(table[i]);
                }
            }
            return set;
        }

        @Override
        /** Return the value that matches the specified key */
        public V get(K key) {
            int i = hash(key.hashCode());
            int startI = i;
            int j = 1;
            while (table[i] != null) {
                if (table[i].getKey().equals(key)) {
                    return table[i].getValue();
                }
                i = (startI + j * j) % capacity;
                j++;
            }
            return null;
        }

        @Override
        /** Return true if this map contains no entries */
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        /** Return a set consisting of the keys in this map */
        public java.util.Set<K> keySet() {
            java.util.Set<K> set = new java.util.HashSet<K>();

            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    set.add(table[i].getKey());
                }
            }
            return set;
        }

        @Override
        /** Add an entry (key, value) into the map */
        public V put(K key, V value) {
            if (get(key) != null) { // The key is already in the map
                int i = hash(key.hashCode());
                int startI = i;
                int j = 1;
                while (table[i] != null) {
                    if (table[i].getKey().equals(key)) {
                        V oldValue = table[i].getValue();
                        table[i].value = value;
                        return oldValue;
                    }
                    i = (startI + j * j) % capacity;
                    j++;
                }
            }

            // Check load factor
            if (size >= capacity * loadFactorThreshold) {
                if (capacity == MAX_HASH_TABLE_SIZE)
                    throw new RuntimeException("Exceeding maximum capacity");

                rehash();
            }

            int i = hash(key.hashCode());
            int startI = i;
            int j = 1;

            while (table[i] != null) {
                i = (startI + j * j) % capacity;
                j++;
            }
            table[i] = new MyMap.Entry<K, V>(key, value);
            size++;
            return value;
        }

        @Override
        /** Remove the entries for the specified key */
        public void remove(K key) {
            int i = hash(key.hashCode());
            int startI = i;
            int j = 1;
            while (table[i] != null) {
                if (table[i].getKey().equals(key)) {
                    size--;
                    table[i] = null;
                }
                i = (startI + j * j) % capacity;
                j++;
            }
        }

        @Override
        /** Return the number of entries in this map */
        public int size() {
            return size;
        }

        @Override
        /** Return a set consisting of the values in this map */
        public java.util.Set<V> values() {
            java.util.Set<V> set = new java.util.HashSet<V>();

            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    set.add(table[i].getValue());
                }
            }
            return set;
        }

        /** Hash function */
        private int hash(int hashCode) {
            return supplementalHash(hashCode) & (capacity - 1);
        }

        /** Ensure the hashing is evenly distributed */
        private static int supplementalHash(int h) {
            h ^= (h >>> 20) ^ (h >>> 12);
            return h ^ (h >>> 7) ^ (h >>> 4);
        }

        /** Return a power of 2 for initialCapacity */
        private int trimToPowerOf2(int initialCapacity) {
            int capacity = 1;
            while (capacity < initialCapacity) {
                capacity <<= 1;
            }

            return capacity;
        }

        /** Remove all entries from each bucket */
        private void removeEntries() {
            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    table[i] = null;
                }
            }
        }

        /** Rehash the map */
        private void rehash() {
            java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
            capacity <<= 1;     // Double capacity
            table = new MyMap.Entry[capacity];
            size = 0;           // Reset size to 0

            for (Entry<K, V> entry : set) {
                put(entry.getKey(), entry.getValue()); // Store to new table
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("[");

            for (int i = 0; i < capacity; i++) {
                if (table[i] != null)
                    builder.append(table[i]);
            }

            builder.append("]");
            return builder.toString();
        }
    }

    /********************************************************************************
     * interface MyMap<K, V>
     ********************************************************************************/
    interface MyMap<K, V> {
        /** Remove all of the entries from this map */
        public void clear();

        /** Return true if the specified key is in the map */
        public boolean containsKey(K key);

        /** Return true if this map contains the specified value */
        public boolean containsValue(V value);

        /** Return a set of entries in the map */
        public java.util.Set<Entry<K, V>> entrySet();

        /** Return the first value that matches the specified key */
        public V get(K key);

        /** Return true if this map contains no entries */
        public boolean isEmpty();

        /** Return a set consisting of the keys in this map */
        public java.util.Set<K> keySet();

        /** Add an entry (key, value) into the map */
        public V put(K key, V value);

        /** Remove the entries for the specified key */
        public void remove(K key);

        /** Return the number of mappings in this map */
        public int size();

        /** Return a set consisting of the values in this map */
        public java.util.Set<V> values();

        /** Define inner class for Entry */
        public static class Entry<K, V> {
            K key;
            V value;

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }

            @Override
            public String toString() {
                return "[" + key + ", " + value + "]";
            }
        }
    }
}
