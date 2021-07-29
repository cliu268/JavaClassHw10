import java.util.Arrays;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args)
    {
        int[] A = {1,2,3,4,5,2,6};
        System.out.println(linearSearch(A, 0));
        System.out.println(removeX("abcdexxxabc"));
        System.out.println(countStrictlyIncreasingPairs(A));
        System.out.println(moveAtobeginningmoveZtoEnd("kuddaazzzaaabuudb"));
        System.out.println(numOpenLeftParentheses("((dd()))abc)))((lll))df))dd)(("));
        System.out.println(equation(8)); // answer is 12^5 == 248832
    }
    
    // linearSearch
    // Inputs: int[] A, int target
    // Output: boolean
    // Returns true if target is inside of A and false otherwise. 
    // Hint! Use a recursive helper function to recurse over the indices. 
    public static boolean linearSearch(int[] A, int target) {
        if (A.length < 1) {
            return false;
        } else if (A.length == 1) {
            return A[0] == target;
        } else {
            if (A[0] == target) {
                return true;
            } else {
                return linearSearch(Arrays.copyOfRange(A, 1, A.length), target);
            }
        }
    }
    
    // removeX
    // Inputs: String s
    // Output: String
    // Takes in a String and recursively removes all occurrences of the letter ‘x’ in the String. 
    // Ex. “abcdexxxabc” ⇒ “abcdeabc”
    public static String removeX(String s) {
        if (s.length() == 0) {
            return "";
        } else if (s.charAt(0) == 'x') {
            return removeX(s.substring(1));
        } else {
            return s.charAt(0) + removeX(s.substring(1));
        }
    }
    
    // countStrictlyIncreasingPairs
    // Inputs: int[] A
    // Output: int
    // Takes in an integer array and counts the number of strictly increasing, consecutive pairs of integers
    // Ex. [1,2,3,4,5,2,6] ⇒ There are 4 strictly increasing, consecutive pairs.
    // [1,2], [2,3], [3,4], [4,5]
    public static int countStrictlyIncreasingPairs(int[] A) {
        if (A.length < 2) {
            return 0;
        } else if (A.length == 2) {
            return (A[0] < A[1]) ? 1 : 0;
        } else {
            if (A[0] < A[1]) {
                return 1 + countStrictlyIncreasingPairs(Arrays.copyOfRange(A, 1, A.length));
            } else {
                return countStrictlyIncreasingPairs(Arrays.copyOfRange(A, 1, A.length));   
            }
        }
    }
    
    // moveAtobeginningmoveZtoEnd
    // Inputs: String s
    // Output: String
    // Takes in a String and recursively moves all ‘a’s to the front of the string and ‘z’s to the end of the string. 
    // Ex. ‘zzzaaabb’ ⇒ ‘aaabbzzz’
    public static String moveAtobeginningmoveZtoEnd(String s) {
        if (s.length() == 1) {
            return s;
        } else {
            return moveZtoEnd(moveAtoBeginning(s));
        }
    }
    
    public static String moveAtoBeginning(String s) {
        if (s.length() == 1) {
            return s;
        } else if (s.charAt(s.length()-1) == 'a') {
            return 'a' + moveAtoBeginning(s.substring(0, s.length()-1));
        } else {
            return moveAtoBeginning(s.substring(0, s.length()-1)) + s.charAt(s.length()-1);
        }    
    }
    
    public static String moveZtoEnd(String s) {
        if (s.length() == 1) {
            return s;
        } else if (s.charAt(0) == 'z') {
            return moveZtoEnd(s.substring(1)) + 'z';
        } else {
            return s.charAt(0) + moveZtoEnd(s.substring(1));
        }
    }
    
    // numOpenLeftParentheses
    // Inputs: String s
    // Output: String
    // Takes in a String and recursively finds the number of ‘open’ left parentheses remaining in s. 
    // An open parentheses is one that cannot be matched up with a ‘)’ pair. 
    // Ex. ‘((())’ ⇒ 1
    // There is one open left parentheses that cannot be matched up with a corresponding right parentheses. 
    public static int numOpenLeftParentheses(String s) {
        if (s.length()==1) {
            return (s.charAt(0) == '(') ? 1 : 0;
        } else if (s.charAt(s.length()-1) == '(') {
            return 1 + numOpenLeftParentheses(s.substring(0, s.length()-1));
        } else if (s.charAt(s.length()-1) == ')') {
            return Math.max(0, numOpenLeftParentheses(s.substring(0, s.length()-1))-1);
        } else {
            return numOpenLeftParentheses(s.substring(0, s.length()-1));
        }
    }
    
    // Challenge! Here is a question that might appear on the AP test! Take this as extra practice. 
    public static int equation(int a) {
        if (a <= 5) {
            return 12;
        }
        return equation(a-2)*equation(a-1);
    }
}
