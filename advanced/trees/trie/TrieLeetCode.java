package advanced.trees.trie;

import java.util.Arrays;
import java.util.HashSet;

import advanced.trees.trie.TrieLeetCode.Trie;
import advanced.trees.trie.TrieLeetCode.TrieNode;

public class TrieLeetCode {

    // 2416. Sum of Prefix Scores of Strings
    // Hard
    // Topics
    // Companies
    // Hint
    // You are given an array words of size n consisting of non-empty strings.

    // We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].

    // For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
    // Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].

    // Note that a string is considered as a prefix of itself.

    // Example 1:

    // Input: words = ["abc","ab","bc","b"]
    // Output: [5,4,3,2]
    // Explanation: The answer for each string is the following:
    // - "abc" has 3 prefixes: "a", "ab", and "abc".
    // - There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
    // The total is answer[0] = 2 + 2 + 1 = 5.
    // - "ab" has 2 prefixes: "a" and "ab".
    // - There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
    // The total is answer[1] = 2 + 2 = 4.
    // - "bc" has 2 prefixes: "b" and "bc".
    // - There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
    // The total is answer[2] = 2 + 1 = 3.
    // - "b" has 1 prefix: "b".
    // - There are 2 strings with the prefix "b".
    // The total is answer[3] = 2.
    // Example 2:

    // Input: words = ["abcd"]
    // Output: [4]
    // Explanation:
    // "abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
    // Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
    

    // Constraints:

    // 1 <= words.length <= 1000
    // 1 <= words[i].length <= 1000
    // words[i] consists of lowercase English letters.
    
    
    // Editorial Solution - 290 ms (Trie)

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int cnt = 0;
    }

    class Solution {

        // Initialize the root node of the trie.
        TrieNode root = new TrieNode();

        // Insert function for the word.
        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                // If new prefix, create a new trie node.
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                // Increment the count of the current prefix.
                node.next[c - 'a'].cnt++;
                node = node.next[c - 'a'];
            }
        }

        // Calculate the prefix count using this function.
        int count(String s) {
            TrieNode node = root;
            int ans = 0;
            // The ans would store the total sum of counts.
            for (char c : s.toCharArray()) {
                ans += node.next[c - 'a'].cnt;
                node = node.next[c - 'a'];
            }
            return ans;
        }

        public int[] sumPrefixScores(String[] words) {
            int N = words.length;
            // Insert words in trie.
            for (int i = 0; i < N; i++) {
                insert(words[i]);
            }
            int[] scores = new int[N];
            for (int i = 0; i < N; i++) {
                // Get the count of all prefixes of given string.
                scores[i] = count(words[i]);
            }
            return scores;
        }
    }

    // Fastest Solution - 34 ms (KDS Approach)

    public int[] sumPrefixScores(String[] words) {
        int wordCount = words.length;
        Integer[] sortedIndices = new Integer[wordCount];
        for (int i = 0; i < wordCount; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, (a, b) -> words[a].compareTo(words[b]));
        
        int[] commonPrefixLengths = calculateCommonPrefixLengths(words, sortedIndices);
        int[] scores = calculateScores(words, sortedIndices, commonPrefixLengths);
        return scores;
    }

    private int[] calculateCommonPrefixLengths(String[] words, Integer[] sortedIndices) {
        int[] commonPrefixLengths = new int[words.length];
        for (int i = 1; i < words.length; i++) {
            String prevWord = words[sortedIndices[i - 1]];
            String currWord = words[sortedIndices[i]];
            int commonLength = 0;
            while (commonLength < prevWord.length() && 
                   commonLength < currWord.length() && 
                   prevWord.charAt(commonLength) == currWord.charAt(commonLength)) {
                commonLength++;
            }
            commonPrefixLengths[i] = commonLength;
        }
        return commonPrefixLengths;
    }

    private int[] calculateScores(String[] words, Integer[] sortedIndices, int[] commonPrefixLengths) {
        int[] scores = new int[words.length];
        for (int i = 0; i < sortedIndices.length; i++) {
            int wordIndex = sortedIndices[i];
            int wordLength = words[wordIndex].length();
            scores[wordIndex] += wordLength;
            int j = i + 1;
            int commonLength = wordLength;
            while (j < words.length) {
                commonLength = Math.min(commonLength, commonPrefixLengths[j]);
                if (commonLength == 0) {
                    break;
                }
                scores[wordIndex] += commonLength;
                scores[sortedIndices[j]] += commonLength;
                j++;
            }
        }
        return scores;
    }


    // -------------------------------------------------------

    // 3043. Find the Length of the Longest Common Prefix
    // Attempted
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given two arrays with positive integers arr1 and arr2.

    // A prefix of a positive integer is an integer formed by one or more of its digits, starting from its leftmost digit. For example, 123 is a prefix of the integer 12345, while 234 is not.

    // A common prefix of two integers a and b is an integer c, such that c is a prefix of both a and b. For example, 5655359 and 56554 have a common prefix 565 while 1223 and 43456 do not have a common prefix.

    // You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x belongs to arr1 and y belongs to arr2.

    // Return the length of the longest common prefix among all pairs. If no common prefix exists among them, return 0.
    
    // Example 1:

    // Input: arr1 = [1,10,100], arr2 = [1000]
    // Output: 3
    // Explanation: There are 3 pairs (arr1[i], arr2[j]):
    // - The longest common prefix of (1, 1000) is 1.
    // - The longest common prefix of (10, 1000) is 10.
    // - The longest common prefix of (100, 1000) is 100.
    // The longest common prefix is 100 with a length of 3.
    // Example 2:

    // Input: arr1 = [1,2,3], arr2 = [4,4,4]
    // Output: 0
    // Explanation: There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
    // Note that common prefixes between elements of the same array do not count.
    
    // Constraints:

    // 1 <= arr1.length, arr2.length <= 5 * 104
    // 1 <= arr1[i], arr2[i] <= 108

    // Solution by chat gpt - 312 ms

    // Helper method to generate all prefixes of a given number
    private HashSet<String> generatePrefixes(int num) {
        HashSet<String> prefixes = new HashSet<>();
        String str = String.valueOf(num);
        for (int i = 1; i <= str.length(); i++) {
            prefixes.add(str.substring(0, i)); // Add each prefix from left
        }
        return prefixes;
    }
    
    public int findLongestCommonPrefixLength(int[] arr1, int[] arr2) {
        HashSet<String> prefixSet = new HashSet<>();

        // Add all prefixes of numbers in arr1 to the HashSet
        for (int num : arr1) {
            prefixSet.addAll(generatePrefixes(num));
        }

        int maxPrefixLength = 0;

        // Check for longest common prefix in arr2
        for (int num : arr2) {
            String str = String.valueOf(num);
            for (int i = 1; i <= str.length(); i++) {
                String prefix = str.substring(0, i);
                if (prefixSet.contains(prefix)) {
                    maxPrefixLength = Math.max(maxPrefixLength, i);
                }
            }
        }

        return maxPrefixLength;
    }


    // Editorial hashset solution - 50 ms


    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> arr1Prefixes = new HashSet<Integer>(); // Set to store all prefixes from arr1

        // Step 1: Build all possible prefixes from arr1
        for (int val : arr1) {
            while (!arr1Prefixes.contains(val) && val > 0) {
                // Insert current value as a prefix
                arr1Prefixes.add(val);
                // Generate the next shorter prefix by removing the last digit
                val /= 10;
            }
        }

        int longestPrefix = 0;

        // Step 2: Check each number in arr2 for the longest matching prefix
        for (int val : arr2) {
            while (!arr1Prefixes.contains(val) && val > 0) {
                // Reduce val by removing the last digit if not found in the prefix set
                val /= 10;
            }
            if (val > 0) {
                // Length of the matched prefix using log10 to determine the number of digits
                longestPrefix = Math.max(
                    longestPrefix,
                    (int) Math.log10(val) + 1
                );
            }
        }

        return longestPrefix;
    }

    // Editorial Solution Trie - 35 ms

    class TrieNode {

        // Each node has up to 10 possible children (digits 0-9)
        TrieNode[] children = new TrieNode[10];
    }
    
    class Trie {
    
        TrieNode root = new TrieNode();
    
        // Insert a number into the Trie by treating it as a string of digits
        void insert(int num) {
            TrieNode node = root;
            String numStr = Integer.toString(num);
            for (char digit : numStr.toCharArray()) {
                int idx = digit - '0';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
        }
    
        // Find the longest common prefix for a number in arr2 with the Trie
        int findLongestPrefix(int num) {
            TrieNode node = root;
            String numStr = Integer.toString(num);
            int len = 0;
    
            for (char digit : numStr.toCharArray()) {
                int idx = digit - '0';
                if (node.children[idx] != null) {
                    // Increase length if the current digit matches
                    len++;
                    node = node.children[idx];
                } else {
                    // Stop if no match for the current digit
                    break;
                }
            }
            return len;
        }
    }
    
    public int longestCommonPrefixTrie(int[] arr1, int[] arr2) {
        Trie trie = new Trie();

        // Step 1: Insert all numbers from arr1 into the Trie
        for (int num : arr1) {
            trie.insert(num);
        }

        int longestPrefix = 0;

        // Step 2: Find the longest prefix match for each number in arr2
        for (int num : arr2) {
            int len = trie.findLongestPrefix(num);
            longestPrefix = Math.max(longestPrefix, len);
        }

        return longestPrefix;
    }



    // Fastest Solution - 22 ms

    class Solution {
        private class Trie {
            Trie[] children = new Trie[10];
        }
        public int longestCommonPrefix(int[] arr1, int[] arr2) {
            Trie root = new Trie();
            for (int n : arr1) {
                int multi = multi(n);
                Trie parent = root;
                while(multi > 0) {
                    int d = (n/multi) % 10;
                    if (parent.children[d] == null) parent.children[d] = new Trie();
                    parent = parent.children[d];
                    multi /= 10;
                }
            }
    
            int maxLen = 0;
            for (int n : arr2) {
                int multi = multi(n);
                int len = 0;
                Trie parent = root;
                while (multi > 0) {
                    int d = (n/multi) % 10;
                    parent = parent.children[d];
                    if (parent == null) break;
                    len++;
                    maxLen = Math.max(maxLen, len);
                    multi /= 10;
                }
            }
            return maxLen;
        }
    
        private int multi(int n) {
            int mult = 1;
            while (mult * 10 <= n) {
                mult = mult * 10;
            }
            return mult;
        }
    }
}
