package algorithms.PermutationCombination;

import java.util.ArrayList;

public class Tree<E> {
    private E data;
    private Tree parent;
    private ArrayList<Tree> children;
    
    /**
     * Tree constructor without parent, this will be the root node
     * @param data The data you wish to put in this node
     */
    public Tree(E data) {
        this.data = data;
    }

    /**
     * Tree constructor with parent, this will be a child node
     * @param data The data you wish to put in this node
     * @param parent The parent tree / node of this tree / node
     */
    public Tree(E data, Tree parent) {

    }

    /**
     * Getter for the parent of this tree / node
     * @return The parent element or null if this is the root
     */
    public Tree getParent() {
        return this.parent;
    }

    /**
     * Getter for the data inside of this tree / node
     * @return The data this tree / node contains
     */
    public E getData() {
        return this.data;
    }

    /**
     * Getter for all children of this tree / node
     * @return An ArrayList with the direct children
     */
    public ArrayList<Tree> getChildren() {
        return this.children;
    }

    /**
     * Setter for the data inside of this tree  /node
     * @param data The new data you want
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Adds a new direct child to this tree / node
     * @param child The child you want to add
     */
    public void addChild(Tree child) {
        children.add(child);
    }
}
