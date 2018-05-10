package HW2;

import java.util.*;

public class Huffman {

    private String input;
    private Node huffmanTree; // the huffman tree
    private Map<Character, String> mapping; // maps characters to binary strings

    /**
     * The Huffman constructor
     * 
     */
    public Huffman(String input) {

	this.input = input;
	mapping = new HashMap<>();

	// first, we create a map from the letters in our string to their frequencies
	Map<Character, Integer> freqMap = getFreqs(input);

	// we'll be using a priority queue to store each node with its frequency,
	// as we need to continually find and merge the nodes with smallest frequency
	PriorityQueue<Node> huffman = new PriorityQueue<>();

	/*
	 * TODO: 1) add all nodes to the priority queue 2) continually merge the two
	 * lowest-frequency nodes until only one tree remains in the queue 3) Use this
	 * tree to create a mapping from characters (the leaves) to their binary strings
	 * (the path along the tree to that leaf)
	 * 
	 * Remember to store the final tree as a global variable, as you will need it to
	 * decode your encrypted string
	 */

	for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
	    Node newNode = new Node(entry.getKey(), entry.getValue(), null, null);
	    huffman.add(newNode);
	}

	while (huffman.size() > 1) {
	    Node lastFirstNode = huffman.poll();
	    Node lastSecondNode = huffman.poll();
	    Node parentNode = new Node(null, lastFirstNode.freq + lastSecondNode.freq, lastSecondNode, lastFirstNode);
	    huffman.add(parentNode);
	}
	this.huffmanTree = huffman.peek();

	treeToMap(huffmanTree, "");
    }

    /**
     * Mapping the tree elements
     * 
     * @param actualNode
     * @param actualString
     */
    private void treeToMap(Node actualNode, String actualString) {
	// TODO Auto-generated method stub
	if (actualNode.isLeaf()) {
	    Character letter = actualNode.letter;
	    mapping.put(letter, actualString);
	} else {
	    treeToMap(actualNode.left, actualString + "0");
	    treeToMap(actualNode.right, actualString + "1");
	}
    }

    /**
     * Use the global mapping to convert your input string into a binary string
     */
    /**
     * 
     * @return encoded string
     */
    public String encode() {
	// TODO
	String tempString = "";
	for (char c : input.toCharArray()) {
	    tempString += mapping.get(c);
	}
	return tempString;
    }

    /**
     * Use the huffmanTree to decrypt the encoding back into the original input
     * 
     * You should convert each prefix-free group of binary numbers in the encoding
     * to a character
     * 
     * @param encoding
     *            - the encoded string that needs to be decrypted
     * @return the original string (should be the same as "input")
     */
    public String decode(String encoding) {
	char[] charArray = encoding.toCharArray();
	String decodedString = "";
	Node currentNode = huffmanTree;
	for (int i = 0; i <= charArray.length - 1; ++i) {
	    if (charArray[i] == '0') {
		currentNode = currentNode.left;
	    } else {
		currentNode = currentNode.right;
	    }
	    if (currentNode.isLeaf()) {
		decodedString += currentNode.letter;
		currentNode = huffmanTree;
	    }
	}

	return decodedString;
    }

    /**
     * This function tells us how well the compression algorithm worked
     * 
     * note that a char is represented internal using 8 bits
     * 
     * ex. if the string "aabc" maps to "0 0 10 11", we would have a compression
     * ratio of (6) / (8 * 4) = 0.1875
     */
    public static double compressionRatio(String input) {
	Huffman h = new Huffman(input);
	String encoding = h.encode();
	int encodingLength = encoding.length();
	int originalLength = 8 * input.length();
	return encodingLength / (double) originalLength;
    }

    /**
     * We've given you this function, which helps you create a frequency map from
     * the input string
     */
    private Map<Character, Integer> getFreqs(String input) {
	Map<Character, Integer> freqMap = new HashMap<>();
	for (char c : input.toCharArray()) {
	    if (freqMap.containsKey(c)) {
		freqMap.put(c, freqMap.get(c) + 1);
	    } else {
		freqMap.put(c, 1);
	    }
	}
	return freqMap;
    }

    /**
     * An inner Node class to build your huffman tree
     * 
     * Each node has: a frequency - the sum of the frequencies of all the node's
     * leaves a letter - the character that this node represents (only for leaves)
     * left and right children
     */
    private class Node implements Comparable<Node> {
	private Character letter; // the letter of this node (only for leaves)
	private int freq; // frequency of this node
	private Node left; // add a 0 to you string
	private Node right; // add a 1 to your string

	public Node(Character letter, int freq, Node left, Node right) {
	    this.letter = letter;
	    this.freq = freq;
	    this.left = left;
	    this.right = right;
	}

	public boolean isLeaf() {
	    return left == null && right == null;
	}

	@Override
	public int compareTo(Node o) {
	    return this.freq - o.freq;
	}
    }

}
