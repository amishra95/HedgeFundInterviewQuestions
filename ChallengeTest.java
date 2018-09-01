import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Node
{
	int parent;
	int value;
	int right;
	int left;
}

public class ChallengeTest {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int samples[] = {30, 8, 52, 3, 20, 10, 29};
		int valueA, valueB;
		
		ArrayList<Node> list = new ArrayList<Node>();
		list.clear();
		
		for (int i = 0; i < samples.length; i++)
		{
			Node node = new Node();
			node.value = samples[i];
			node.parent = -1;
			node.left = -1;
			node.right = -1;
			list.add(node);
		}		
		
		for (int i = 1; i < samples.length; i++)
		{
			Node node = list.get(i);
			int startNode = 0;
			while(true)
			{
				if (node.value < list.get(startNode).value)
				{
					if (list.get(startNode).left == -1)
					{
						list.get(startNode).left = i;
						node.parent = startNode;
						break;
					}
					else
					{
						startNode = list.get(startNode).left;
						continue;
					}
				}
				else
				{
					if (list.get(startNode).right == -1)
					{
						list.get(startNode).right = i;
						node.parent = startNode;
						break;
					}
					else
					{
						startNode = list.get(startNode).right;
						continue;
					}
				}
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strInput = br.readLine();
		
		int nPos = strInput.indexOf(" ");
		if (nPos < 0)
		{
			System.out.println("Input Error.");
			return;
		}
		valueA = Integer.valueOf(strInput.substring(0, nPos));
		valueB = Integer.valueOf(strInput.substring(nPos + 1));
		
		int nodeA = -1, nodeB = -1;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).value == valueA)
				nodeA = i;
			if (list.get(i).value == valueB)
				nodeB = i;
		}
		
		if (nodeA == -1 || nodeB == -1)
		{
			System.out.println("Input Error.");
			return;
		}
		
		if (nodeA == nodeB)
		{
			System.out.println(list.get(nodeA).value);
			return;
		}		
		
		while(true)
		{
			int tempNodeB = nodeB;
			while(true)
			{
				if (list.get(nodeA).value == list.get(tempNodeB).value)
				{
					System.out.println(list.get(nodeA).value);
					return;
				}
				tempNodeB = list.get(tempNodeB).parent;
				if (tempNodeB == -1)
					break;
			}
			nodeA = list.get(nodeA).parent;
			if (nodeA == -1)
				break;
		}
		System.out.println(list.get(0).value);
	}

}
