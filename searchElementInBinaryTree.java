import javafx.scene.Node;

public class searchElementInBinaryTree {

	
	public static boolean search(Node root, int k) {
		if(root != null)
		{
			if(root.data == k) {
				return true;
			}
			else {
				return search(root.left, k) || search(root.right, k);
			}
			
			
		}
		
		return false;
	}
}
