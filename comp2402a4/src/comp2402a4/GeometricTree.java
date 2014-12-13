package comp2402a4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}

	public void inorderDraw() {
		assignLevels();
		this.traverse();

	}

	public void traverse() {
		GeometricTreeNode u = r, 
				prev = nil,
				next;

		int c = 0;

		while (u != nil) {
			if (prev == u.parent) {

				if (u.left != nil){
					next = u.left;
				}
				else if (u.right != nil){
					next = u.right;
					u.position.x = c++;
				}
				else{
					next = u.parent;
					u.position.x = c++;
				}

			} else if (prev == u.left) {
				u.position.x = c++;
				if (u.right != nil)
					next = u.right;
				else
					next = u.parent;
			} 
			else{
				next = u.parent;
			}

			prev = u;
			u = next;
		}
	}

	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level 
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		bfTraverse();

	}

	public void bfTraverse() {
		Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
		Map<Integer,Integer> count = new HashMap<Integer,Integer>();
		q.add(r);
		while (!q.isEmpty()) {
			GeometricTreeNode u = q.remove();

			count.put(u.position.y, count.containsKey(u.position.y) ? count.get(u.position.y) + 1: 0);
			if (u.left != nil) q.add(u.left);
			if (u.right != nil) q.add(u.right);

			u.position.x = count.get(u.position.y);
		}
	}


	int maxWidth = 0;
	Map<GeometricTreeNode, Integer> nodeMap;


	public void balancedDraw() {
		assignLevels();
		nodeMap = mapNodeTreeSize(r);
		adjustChildNodes(r, 0, 0);
		maxWidth = 0;
	}
	/**
	 * Function: adjustChildNodes
	 * Actually makes use of pre-order traversal.
	 * Performs tree operations on current node, and traverses all the way
	 * left then right
	 * @param u
	 * @param x
	 * @param y
	 */
	public void adjustChildNodes(GeometricTreeNode u, int x, int y) {
		if (u == nil) return;
		
		if(x > maxWidth)
			maxWidth = x;
		
		u.position.x = x;
		u.position.y = y;

		if(u.left != nil && u.right == nil){
			this.adjustChildNodes(u.left, maxWidth+1, y);
		}
		else if(u.left != nil && u.right != nil){
			if(nodeMap.get(u.left) > nodeMap.get(u.right)){
				this.adjustChildNodes(u.right, x, y+1);
				this.adjustChildNodes(u.left, maxWidth+1, y);
			}
			else{
				this.adjustChildNodes(u.left, x, y+1);
				this.adjustChildNodes(u.right, maxWidth+1, y);
			}
		}
		else{
			this.adjustChildNodes(u.right, maxWidth+1, y);
		}	

	
	}



	public HashMap<GeometricTreeNode, Integer> mapNodeTreeSize(GeometricTreeNode t) {
		HashMap<GeometricTreeNode, Integer> nodeMap = new HashMap<GeometricTreeNode, Integer>();
		GeometricTreeNode u=t,
				prev = nil,
				next;

		while (u != null) {
			if (prev == u.parent) {

				if (u.left != null)
					next = u.left;

				else if (u.right != null)
					next = u.right;

				else{ //no children
					nodeMap.put(u,( u.left != null ? nodeMap.get(u.left) : 1) + (u.right != null ? nodeMap.get(u.right) :1) + 1);
					next = u.parent;
				}
			} 
			else if (prev == u.left) {
				if (u.right != null)
					next = u.right;
				else{
					nodeMap.put(u,( u.left != null ? nodeMap.get(u.left) : 1) + (u.right != null ? nodeMap.get(u.right) :1) + 1);
					next = u.parent;
				}
			} 
			else{
				nodeMap.put(u,( u.left != null ? nodeMap.get(u.left) : 1) + (u.right != null ? nodeMap.get(u.right) :1) + 1);
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return nodeMap;
	}
	/**
	 * Begins assign levels, by beginning with head of tree, and setting
	 * the level to zero.
	 */
	protected void assignLevels() {
		assignLevels(r, 0);
	}
	/**
	 * Recursively assigns all the levels of a specific node,
	 * setting its y position to be equal to parameter 'i'.
	 * @param u
	 * @param i
	 */
	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}

	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}

}
