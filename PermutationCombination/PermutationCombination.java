package algorithms.PermutationCombination;

import java.util.*;

public class PermutationCombination<E> {
    private ArrayList<Tree> deepestChildren;

    /**
     * Constructor which takes the elements in a normal array
     * @param array The array containing the elements
     */
    public PermutationCombination(E[] array) {
        this(new ArrayList<E>(Arrays.asList(array)));
    }

    /**
     * Constructor which takes the element in an ArrayList form
     * @param arrayList The ArrayList containing the elements
     */
    public PermutationCombination(ArrayList<E> arrayList) {
        deepestChildren = new ArrayList<Tree>();
        Tree root = new Tree(null);
        this.assembleTree(arrayList, root);
    }

    /**
     * This method constructs the correct permutation tree for the given elements
     * The method is private because it gets triggered by the constructors
     * @param arrayList An ArrayList containing the elements
     * @param root The root tree / node for the tree
     */
    private void assembleTree(ArrayList<E> arrayList, Tree root) {
        for(E element : arrayList) {
            Tree cursor = new Tree(element, root);

            // Making a deep copy of the array list to make sure the original doesn't get lost
            ArrayList<E> deepCopy = new ArrayList<E>();
            deepCopy.addAll(arrayList);

            deepCopy.remove(element);

            // Recursion to fill in all levels of the tree, also finds the deepest children
            if(deepCopy.size() > 0)
                assembleTree(deepCopy, cursor);
            else
                deepestChildren.add(cursor);
        }
    }

    /**
     * This method gives all permutations of the given elements in a String format
     * @return String with all permutations, each on a separate line
     */
    public String permutations() {
        StringBuilder builder = new StringBuilder();

        // Starting from the bottom of the tree and going up until the root is reached
        for(Tree element : deepestChildren) {
            Tree cursor = element;

            builder.append(element.getData());
            builder.append(" ");

            while(cursor.getParent().getData() != null) {
                cursor = cursor.getParent();
                builder.append(cursor.getData());
                builder.append(" ");
            }

            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * This method gives all combinations for a given length of the given elements
     * @param length The length each combination must have, shorter than the amount of elements
     * @return String with all combinations for the given length, each on a separate line
     */
    public String combinations(int length) {
        // Not implemented yet
        return "";
    }

    /**
     * Main method which outputs all permutations of ABCD, this is only a temporary implementation
     */
    public static void main(String[] args) {
        String[] test = { "A", "B", "C", "D" };
        PermutationCombination inst = new PermutationCombination<String>(test);
        System.out.println(inst.permutations());
        // System.out.println(inst.combinations(2));
    }
}
