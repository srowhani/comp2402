package comp2402a5;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An unfinished implementation of an AVL tree (for exercises)
 * @author morin
 *
 * @param <T>
 */
public class AVLTree<T> extends
		BinarySearchTree<AVLTree.Node<T>, T> implements SSet<T> {
	/**
	 * A random number source
	 */
	Random rand;

	public static class Node<T> extends BSTNode<Node<T>,T> {
		int h;  // the height of the node
	}
	
	public AVLTree() {
		sampleNode = new Node<T>();
		rand = new Random();
		c = new DefaultComparator<T>();
	}

	public int height(Node<T> u) {
		return (u == null) ? 0 : u.h;
	}
	
	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.x = x;
		if (super.add(u)) {
			for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
			fixup(u);
			return true;
		}
		return false;
	}
	
	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		for (Node<T> z = u; z != nil; z = z.parent)
			z.h = Math.max(height(z.left), height(z.right)) + 1;			
		fixup(w);
	}
	

	
	public void rotateLeft(Node<T> u) {
		super.rotateLeft(u);
		/*Straight from add(T x)
  		 * Sets current height to be the max of children,
  		 * and walks up the tree
		 */
		for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
	}
	public void checkHeights(Node<T> u) {
		if (u == nil) return;
		checkHeights(u.left);
		checkHeights(u.right);
		if (height(u) != 1 + Math.max(height(u.left), height(u.right))) 
			throw new RuntimeException("Check heights shows incorrect heights");
		int dif = height(u.left) - height(u.right);
		if (dif < -1 || dif > 1)
			throw new RuntimeException("Check heights found height difference of " + dif);
	}
	private int compare(Node<T> u, Node<T> i){
		//System.out.println(height(u) - height(i));
		return height(u) - height(i);
	}
	public void rotateRight(Node<T> u) {
		super.rotateRight(u);
		for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
	}

	public void fixup(Node<T> u) {
		while (u != nil) {
			int dif = compare(u.left, u.right);
			if (dif > 1) { //LEFT CHILD HEIGHT TOO LARGE
				Node<T> leftChild = u.left;
				if(compare(leftChild.right, leftChild.left) >= 1)
					rotateLeft(leftChild); //if left child uneven, adjust. PREVENTS INFINITE RECURSION 
				/*
				 * Because we identified that the left child's height was greater than the right sides,
				 * we will make our u = u.left, and make u.left = old u. And u.right will still be u.right
				 */
				rotateRight(u);

			} else if (dif < -1) { //RIGHT CHILD HEIGHT TOO LARGE
				/*
				 * The reverse process of the left side being too large
				 */
				Node<T> rightChild = u.right;

				if(compare(rightChild.left, rightChild.right) >= 1)
					rotateRight(rightChild);
				rotateLeft(u);
			}
			u = u.parent;
		}
	}
	
	public static <T> T find(SortedSet<T> ss, T x) {
		SortedSet<T> ts = ss.tailSet(x);
		if (!ts.isEmpty()) {
			return ts.first(); 
		}
		return null;
	}
	public static void main(String[] args){
		if(AVLTreeTester.testAVLTree(new AVLTree<Integer>()))
			System.out.println("AVLTree Successfully passed all tests!");
	}

}
