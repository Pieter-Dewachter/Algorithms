package algorithms.PermutationCombination;

import java.util.*;

public class PermutationCombination<E> {
    private ArrayList<Tree> deepestChildren;
    private int amount;

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
        amount = arrayList.size();
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
    public String permutations() { return combinations(amount); }

    /**
     * This method gives all combinations for a given length of the given elements
     * If the given length equals the amount of elements, this will give the permutations
     * @param length The length each combination must have, shorter than or equal to the amount of elements
     * @return String with all combinations for the given length, each on a separate line
     */
    public String combinations(int length) {
        // Check for invalid length
        if(length >= amount)
            return "Invalid length specified!";

        // Making a StringBuilder with the combinations in it
        StringBuilder builder = new StringBuilder();

        // To prevent duplicates, make an ArrayList with already done combinations
        ArrayList<String> done = new ArrayList<String>();

        // Starting from the bottom of the tree and going up until the root is reached
        for(Tree element : deepestChildren) {
            Tree cursor = element;

            // Starting from the bottom of the tree, so i must start from the maximum
            int i = amount;

            // Temporary StringBuilder, won't be added if it's a duplicate
            StringBuilder temp = new StringBuilder();

            while(cursor.getData() != null) {
                // Making sure the length doesn't exceed the given value by the user
                if(i <= length) {
                    temp.append(cursor.getData());
                    temp.append(" ");
                }
                cursor = cursor.getParent();
                i--;
            }

            // Check if it's a duplicate, add only if it's not
            if(!done.contains(temp.toString())) {
                done.add(temp.toString());
                builder.append(temp.toString());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Main method which allows user input for calculating permutations and combinations
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PermutationCombination instance = null;
        boolean combination = false;
        ArrayList<String> input = new ArrayList<String>();

        System.out.println("-- Permutation and Combination calculator --");
        System.out.println("  Enter as many elements as you wish, enter 'next' or 'n' when you are done");

        while(true) {
            String next = scanner.next();
            if((next.equals("next") || next.equals("n")) && instance == null) {
                instance = new PermutationCombination(input);
                System.out.println("  Enter 'permutation' or 'p' to calculate the permutations");
                System.out.println("  Enter 'combination' or 'c' to calculate the combinations");
                System.out.println("  Enter 'quit' or 'q' when you are done");
            }
            else if((next.equals("permutation") || next.equals("p")) && instance != null) {
                System.out.println(instance.permutations());
            }
            else if((next.equals("combination") || next.equals("c")) && instance != null) {
                System.out.println("  Enter the length of the combinations you want");
                combination = true;
            }
            else if(combination) {
                System.out.println(instance.combinations(Integer.parseInt(next)));
                combination = false;
            }
            else if((next.equals("quit") || next.equals("q")) && instance != null) {
                System.exit(0);
            }
            else if(instance == null) {
                input.add(next);
            }
        }
    }
}
