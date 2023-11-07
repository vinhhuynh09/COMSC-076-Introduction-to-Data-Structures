import java.util.*;

/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #9 Implementing Lists
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: July 15, 2023
 ***********************************************************************************************************************
 * Description:
 Start by carefully reading Listing 24.5: MyLinkedList.java (on page 938 of the 11th Edition of the text).
 Note that the listing is incomplete. Your assignment is to implement a revised MyLinkedList class after
 you have included all the code needed to fill in and complete all the methods that were omitted.
 Next, write a (main) driver program that initializes a linked list with 10 names (your choice),
 and then completely tests every one of its methods of ensure that the class meets all its requirements.
 **********************************************************************************************************************/

public class Program9 {

    /***************************************************************************
     *  public static void main(String[] args)
     *  ************************************************************************/
    public static void main(String[] args) {
        /********************************* testing MyLinkedList.java ********************************/
        // Create a list for strings
        String[] str = {"Justin", "Sato", "Maegan", "Vinush", "Derrick", "Andrian", "Michael", "Jeremy", "Daniel", "Bill"};
        MyLinkedList<String> list = new MyLinkedList<>(str);
        // Add elements to the list
        list.add("John"); // Add elements to the list
        System.out.println(list);

        list.add(0, "Jim"); // Add elements to the beginning of the list
        System.out.println(list);

        /** testing addLast(E e) **/
        list.addLast("George"); // Add elements to the end of the list
        System.out.println(list);

        /** testing addFirst(E e) **/
        list.addFirst("Nick");
        System.out.println(list);

        /** testing add(int index, E e) **/
        list.add(2, "Phillip"); // Add it to the list at index 2
        System.out.println(list);

        /** testing contains() **/
        System.out.println("\nList contains Stephen: " + list.contains("Maegan"));
        System.out.println("List contains Vinh: " + list.contains("Vinh"));

        /** testing get() **/
        System.out.println("\nZero index element: " + list.get(0));
        System.out.println("Sixth index element: " + list.get(6));
        System.out.println("Third index element: " + list.get(3));

        /** testing indexOf() **/
        System.out.println("\nIndex of John: " + list.indexOf("John"));
        System.out.println("Index of Anthony: " + list.indexOf("Anthony"));

        /** testing lastIndexOf() **/
        System.out.println("\nLast index of Jim: " + list.lastIndexOf("Jim"));
        System.out.println("Last index of Vinh: " + list.lastIndexOf("Vinh"));

        /** testing set() **/
        System.out.println("\nOld element at index 5: " + list.set(5, "Tim"));
        System.out.println(list);

        /** testing getFirst() **/
        System.out.println("\nFirst element in list: " + list.getFirst());

        /** testing getLast() **/
        System.out.println("\nLast element in list: " + list.getLast());

        /** testing addFirst() **/
        list.addFirst("Patrick");
        System.out.println("\nAfter adding Patrick: " + list);

        /**testing addLast() **/
        list.addLast("Kevin");
        System.out.println("\nAfter adding Kevin: " + list);

        /** testing removeFirst() **/
        list.removeFirst();
        System.out.println("\nAfter removing first element: " + list);

        /** testing removeLast() **/
        list.removeLast();
        System.out.println("\nAfter removing last element: " + list);

        /** testing size() **/
        System.out.println("\nSize of list: " + list.size());

        /** testing remove() **/
        list.remove(0);
        System.out.println("\n" + list);

        list.remove(2); // Remove the element at index 2
        System.out.println(list);

        list.remove(list.size() - 1);
        System.out.print(list + "\n");

        /** testing isEmpty() **/
        System.out.println("\nIs list empty?: " + list.isEmpty());

        /** testing remove(Object e) **/
        list.remove("Derrick");
        System.out.println("\n" + list);

        String[] str2 = {"Bob", "Ricky", "John"};
        MyLinkedList<String> list2 = new MyLinkedList<>(str2);

        /** testing containsAll(Collection<?> c) **/
        System.out.println("\nDoes list1 contain all of list2?: " + list.containsAll(list2));

        /** testing addAll(Collection<? extends E> c) **/
        list.addAll(list2);
        System.out.println("\n" + list);

        /** testing retainAll(Collection<? extends E> c) **/
        list.retainAll(list);
        System.out.println("\n" + list);

        /** testing removeAll(Collection<? extends E> c) **/
        list.removeAll(list);
        System.out.println();

        /** testing toArray() **/
        Object[] arrayObj = new Object[3];
        arrayObj = list2.toArray();
        for (Object o : arrayObj) {
            System.out.print(o + " ");
        }

        System.out.println("\n");

        String[] arrayStr = new String[3];
        arrayStr = list2.toArray(arrayStr);
        for (String s : arrayStr) {
            System.out.print(s + " ");
        }

        /** testing clear() **/
        list2.clear();
        System.out.println("\n\nAfter clearing the list2, the list2 size is " + list2.size());
    }
}   // end main class

/********************************************************************************
 * interface MyList<E> extends Collection<E>
 *******************************************************************************/
interface MyList<E> extends Collection<E> {
    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object e);

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public E set(int index, E e);

    @Override /** Add a new element at the end of this list */
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override /** Return true if this list contains no elements */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override /** Remove the first occurrence of the element e
     *  from this list. Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        else
            return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        // Left as an exercise
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        boolean modified = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public default Object[] toArray() {
        // Left as an exercise
        Object[] array = new Object[size()];
        int index = 0;
        for (E element : this) {
            array[index++] = element;
        }
        return array;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        // Left as an exercise
        int size = size();
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        } else if (array.length > size) {
            array[size] = null;
        }

        int index = 0;
        for (E element : this) {
            array[index++] = (T) element;
        }

        return array;
    }
}   // end interface MyList<E> extends Collection<E>

/************************************************************************************
 * class MyLinkedList<E> implements MyList<E>
 * *********************************************************************************/
class MyLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst() {
        return (size == 0) ? null : head.element;
    }

    /** Return the last element in the list */
    public E getLast() {
        return (size == 0) ? null : tail.element;
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);    // Create a new node
        newNode.next = head;                // link the new node with the head
        head = newNode;                     // head points to the new node
        size++;                             // Increase list size

        if (tail == null) //The new node is the only node in list
            tail = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);    // Create a new node for e

        if (tail == null) {
            head = tail = newNode;          // The only node in list
        }
        else {
            tail.next = newNode;            // Link the new node with the last node
            tail = newNode;                 // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override /** Add a new element at the specified index in this list. The index of the head element is 0 */
    public void add(int index, E e) {
        if (index == 0) addFirst(e);        // Insert first
        else if (index >= size) addLast(e); // Insert last
        else { // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /** Remove the head node and return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) return null;     // Nothing to delete
        else {
            Node<E> temp = head;        // Keep the first node temporarily
            head = head.next;           // Move head to point to next node
            size--;                     // Reduce size by 1
            if (head == null) tail = null; // List becomes empty
            return temp.element;        // Return the deleted element
        }
    }

    /** Remove the last node and return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) return null; // Nothing to remove
        else if (size == 1) {       // Only one element in the list
            Node<E> temp = head;
            head = tail = null;     // list becomes empty
            size = 0;
            return temp.element;
        }
        else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++)
                current = current.next;

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override /** Remove the element at the specified position in this list. Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) return null;    // Out of range
        else if (index == 0) return removeFirst();      // Remove first
        else if (index == size - 1) return removeLast(); // Remove last
        else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            }
            else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        // Left as an exercise
        return indexOf(e) !=-1;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        // Left as an exercise
        Node<E> current = head;
        int i = 0;
        while (current!=null && i !=index) {
            current = current.next;
            i++;
        }
        if (current!= null) {
            return current.element;
        }
        return null;
    }

    @Override /** Return the index of the head matching element in this list. Return −1 if no match. */
    public int indexOf(Object e) {
        // Left as an exercise
        Node<E> current = head;
        int index = 0;
        while(current!= null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override /** Return the index of the last matching element in this list. Return −1 if no match. */
    public int lastIndexOf(E e) {
        // Left as an exercise
        Node<E> current = head;
        int answer = -1;
        int index = 0;
        while(current != null) {
            if (current.element.equals(e)) {
                answer = index;
            }
            current = current.next;
            index++;
        }
        return answer;
    }

    @Override /** Replace the element at the specified position in this list with the specified element. */
    public E set(int index, E e) {
        // Left as an exercise
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        while (index != 0) {
            current = current.next;
            index--;
        }
        E r = current.element;
        current.element = e;
        return r;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /*****************************************************************************
     * private class LinkedListIterator implements java.util.Iterator<E>
     *****************************************************************************/
    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = head; // Current index

        @Override public boolean hasNext() {
            return (current != null);
        }

        @Override public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override public void remove() {
            // Left as an exercise
            if (current == head) {
                removeFirst();
                current = head;
            }
            else {
                Node<E> prev = head;
                while(prev.next != current) {
                    prev = prev.next;
                }
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                current = prev.next;
            }

        }
    }   // end private class LinkedListIterator implements java.util.Iterator<E>

    /***********************************************************************
     * private static class Node<E>
     ***********************************************************************/
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }   // end private static class Node<E>

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }
}   // end class MyLinkedList<E> implements MyList<E>