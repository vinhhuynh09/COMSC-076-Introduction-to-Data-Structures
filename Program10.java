/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #10 Binary Search Trees
 * Course: COMSC 076. Summer 2023
 * Date: July 19, 2023
 ***********************************************************************************************************************
 * Description:
 Fully implement the BST class in Listing 25.4 (on page 961 of the 11th Edition of the text).
 Design and write a (main) driver program to completely test every method in the BST class to
 ensure the class meets all its requirements. You should read the Listing 25.5: TestBST.java
 for an idea of what your program should look like.
 **********************************************************************************************************************

 Six methods implemented in Tree.class:
 1) public default boolean containsAll(Collection<?> c)
 2) public default boolean addAll(Collection<? extends E> c)
 3) public default boolean removeAll(Collection<?> c)
 4) public default boolean retainAll(Collection<?> c)
 5) public default Object[] toArray()
 6) public default <T> T[] toArray(T[] array)
 ********************************************************************************************************************/

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Program10 {
    public static void main(String[] args) {
        // Below codes are copy and paste from Listing 25.5. The output is the same the book.
        BST<String> tree = new BST<>(); // Create a BST

        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");

        // Traverse tree
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());

        // Search for an element
        System.out.print("\nIs Peter in the tree? " + tree.search("Peter"));

        // Get a path from the root to Peter
        System.out.print("\nA path from the root to Peter is: ");
        java.util.ArrayList<BST.TreeNode<String>> path = tree.path("Peter");
        for (int i = 0; path != null && i < path.size(); i++)
            System.out.print(path.get(i).element + " ");

        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> intTree = new BST<>(numbers);
        System.out.print("\nInorder (sorted): ");
        intTree.inorder();

        /**************** Add new code below to test the rest of methods */
        System.out.println("\n\n\nMy own code to test the rest of the methods\n");
        /** creating myTree binary tree **/
        String[] str = {"Vinh", "Aditya", "Maegan", "Pierre", "Huang", "Peter", "Derrick"};
        BST<String> myTree = new BST<>(str);

        /** testing insert(E e) **/
        myTree.insert("Heinrich");
        myTree.insert("Krishna");
        myTree.insert("Andrian");

        /** testing search(E e) **/
        System.out.println("Is Derrick in the tree?: " + myTree.search("Derrick"));
        System.out.println("Is Alex in the tree?: " + myTree.search("Alex"));

        /** testing inorder() **/
        System.out.print("myTree inorder traversal: ");
        myTree.inorder();

        /** testing postorder() **/
        System.out.print("\nmyTree postorder traversal: ");
        myTree.postorder();

        /** testing preorder() **/
        System.out.print("\nmyTree preorder traversal: ");
        myTree.preorder();

        /** testing hasNext() **/
        System.out.println("\nDoes myTree have next element?: " + myTree.iterator().hasNext());

        /** testing getSize() **/
        System.out.print("The number of nodes in myTree is: " + myTree.getSize());

        /** testing getRoot() **/
        BST.TreeNode<String> treeRoot2 = myTree.getRoot();
        System.out.println("\nThe root in myTree is: " + treeRoot2.element);

        /** testing path(E e) **/
        System.out.print("A path from the root to Huang in myTree is: ");
        path = myTree.path("Huang");
        for (int i = 0; path != null && i < path.size(); i++) {
            System.out.print(path.get(i).element + " ");
        }

        /** testing delete(E e) **/
        myTree.delete("Heinrich");
        System.out.print("\nmyTree inorder traversal after removing Heinrich: ");
        myTree.inorder();

        /** testing isEmpty() **/
        System.out.println("\nIs myTree empty?: " + myTree.isEmpty());

        /** testing contains(Object e) **/
        System.out.println("Does myTree contain Vinh?: " + myTree.contains("Vinh"));
        System.out.println("Does myTree contain Vincent?: " + myTree.contains("Vincent"));

        /** testing add(E e) **/
        myTree.add("Brian");
        System.out.print("myTree inorder traversal after adding Brian: ");
        myTree.inorder();

        /** testing remove(Object e) **/
        myTree.remove("Brian");
        System.out.print("\nmyTree inorder traversal after removing Brian: ");
        myTree.inorder();

        /** testing size() **/
        System.out.println("\nmyTree size: " + myTree.size());

        /** creating myTree2 binary tree **/
        BST<String> myTree2 = new BST<>();

        myTree2.insert("Paul");
        myTree2.insert("Bill");
        myTree2.insert("Josh");

        /** testing containsAll(Collection<?> c) **/
        System.out.println("Does myTree1 contain all of myTree2?: " + myTree.containsAll(myTree2));

        /** testing addAll(Collection<? extends E> c) **/
        myTree.addAll(myTree2);
        System.out.print("myTree inorder traversal after adding all of myTree2: ");
        myTree.inorder();

        /** testing retainAll(Collection<?> c) **/
        myTree.retainAll(myTree);
        System.out.print("\nmyTree inorder traversal after retaining all: ");
        myTree.inorder();

        /** testing toArray() **/
        Object[] arrayObj = new Object[3];
        arrayObj = myTree.toArray();
        System.out.print("\nmyTree toArray(): ");
        for (Object o : arrayObj) {
            System.out.print(o + " ");
        }

        /** testing toArray(T[] array) **/
        System.out.print("\nmyTree2 toArray(T[] array): ");
        String[] arrayStr = new String[3];
        arrayStr = myTree2.toArray(arrayStr);
        for (String s : arrayStr) {
            System.out.print(s + " ");
        }

        /** testing removeAll(Collection<?> c) **/
        myTree2.removeAll(myTree2);
        System.out.println("\nAfter removing all nodes from myTree2, the tree size of myTree2 is: " + myTree2.size());
        myTree2.inorder();

        /** testing clear() **/
        myTree.clear();
        System.out.println("After clearing all nodes from myTree, the tree size of myTree is: " + myTree.size());
        myTree.inorder();
    }   // end void main
}   // end class Main

class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    // Create an empty binary tree
    public BST() {
    }

    // Create a binary search tree from an array of objects
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    // Return true if the element is in the tree
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;     // Go left
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;    // Go right
            } else              // Element matches current.element
                return true;    // Element is found
        }

        return false; // Not in the tree
    }   // end public boolean search(E e)

    @Override /** Insert element e into the binary search tree.
     *	Return true if the element is inserted successfully. */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }   // end public boolean insert(E e)

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override // Inorder traversal from the root
    public void inorder() {
        inorder(root);
    }

    //  Inorder traversal from a subtree
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override // Postorder traversal from the root
    public void postorder() {
        postorder(root);
    }

    // Postorder traversal from a subtree
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override   // Preorder traversal from the root
    public void preorder() {
        preorder(root);
    }

    // Preorder traversal from a subtree
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }   // end protected void preorder(TreeNode<E> root)

    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }   // end public static class TreeNode<E>

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Return a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }   // end public java.util.ArrayList<TreeNode<E>> path(E e)

    @Override     /** Delete an element from the binary search tree.
     * Return true if the element is deleted successfully.
     * Return false if the element is not in the tree.
     */
    public boolean delete(E e) {
        //Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child.
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent.
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root */
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override   /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        @Override   /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override    /** Remove the current element */
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear();   // Clear the list
            inorder();      // Rebuild the list
        }
    }

    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}

interface Tree<E> extends Collection<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    /** Insert element e into the binary tree Return true if the element is inserted successfully */
    public boolean insert(E e);

    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    public boolean delete(E e);

    /** Get the number of elements in the tree */
    public int getSize();

    /** Inorder traversal from the root*/
    public default void inorder() {
    }

    /** Postorder traversal from the root */
    public default void postorder() {
    }

    /** Preorder traversal from the root */
    public default void preorder() {
    }

    @Override /** Return true if the tree is empty */
    public default boolean isEmpty() { return size() == 0;
    }

    @Override
    public default boolean contains(Object e) { return search((E)e);
    }

    @Override
    public default boolean add(E e) { return insert(e);
    }

    @Override
    public default boolean remove(Object e) { return delete((E)e);
    }

    @Override
    public default int size() { return getSize();
    }

    /********************************************************************
     * Codes below are new code.
     ********************************************************************/
    @Override
    public default boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
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
        Object[] array = new Object[size()];
        int index = 0;
        for (E element : this) {
            array[index++] = element;
        }
        return array;
    }       // end public default Object[] toArray()

    @Override
    public default <T> T[] toArray(T[] array) {
        int size = size();
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        else if (array.length > size) {
            array[size] = null;
        }

        int index = 0;
        for (E element : this) {
            array[index++] = (T) element;
        }

        return array;
    }
}       // end public interface Tree<E> extends Collection<E>
