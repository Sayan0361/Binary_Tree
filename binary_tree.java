
import java.util.*;

public class binary_tree {
    static Scanner sc = null;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();
        
        System.out.println("The inOrder is");
        inOrder(root);
        System.out.println();
        System.out.println("The preOrder is");
        preOrder(root);
        System.out.println();
        System.out.println("The postOrder is");
        postOrder(root);
        System.out.println();
        System.out.println("Height of tree is "+height(root));
        System.out.println();
        System.out.println("Size of tree is "+size(root));
        System.out.println();
        System.out.println("The maximum node is "+maximum(root));
        System.out.println();
        System.out.println("The minimum node is "+minimum(root));
        System.out.println();
        System.out.println("The sum of all nodes is "+sum(root));
        System.out.println();
        System.out.println("The level order traversal of tree is ");
        levelOrderTraversal(root);
        System.out.println();
        System.out.println("The left viewpoint of the tree is ");
        leftView(root);
        System.out.println();
        System.out.println("The right viewpoint of the tree is ");
        rightView(root);
        System.out.println();
        System.out.println("The top viewpoint of the tree is ");
        topView(root);
        System.out.println();
        System.out.println("The bottom viewpoint of the tree is ");
        bottomView(root);
        System.out.println();
        }
    
    public static Node createTree() {
        Node root = null;
        System.out.println("Enter data :-");
        int data = sc.nextInt();
        if(data!= -1) {
            root = new Node(data);
            System.out.println("Enter left child data of "+data);
            root.left = createTree();
            System.out.println("Enter right child data of "+data);
            root.right = createTree();
        }
        else //if(data!= -1)
            return null;
        return root;
    }
    
    public static void inOrder(Node root) {
        if(root!= null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
        else
            return;
    }
    
    public static void preOrder(Node root){
        if(root!= null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
        else
            return;
    }

    public static void postOrder(Node root){
        if(root!= null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
        else
            return;
    }

    public static int height(Node root) {
        if(root == null)
            return 0;
        /*int lheight = height(root.left);
        int rheight = height(root.right);
        if(lheight > rheight)
            return lheight+1;
        else
            return rheight+1;*/
        return (Math.max(height(root.left), height(root.right)) + 1);
    }

    public static int size(Node root) {
        if(root == null)
            return 0;
        return (size(root.left) + size(root.right) + 1);
    }

    public static int maximum(Node root) {
        if(root == null)
            return Integer.MIN_VALUE;
        return (Math.max(root.data , Math.max(maximum(root.left), maximum(root.right))));
    }

    public static int minimum(Node root) {
        if(root == null)
            return Integer.MAX_VALUE;
        return (Math.min(root.data, Math.min(minimum(root.left), minimum(root.right))));
    }

    public static int sum(Node root) {
        if(root == null)
            return 0;
        return (root.data + sum(root.left) + sum(root.right));
    }

    public static void levelOrderTraversal(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            if(temp.left!= null)
                q.add(temp.left);
            if(temp.right!= null)
                q.add(temp.right);
        }
    }

    public static void leftView(Node root) {
        ArrayList<Node> li = new ArrayList<Node>();
        leftViewOperation(root,li,0);
        for(Node curr : li) 
            System.out.print(curr.data + " ");
    }

    public static void leftViewOperation(Node root, ArrayList<Node> li, int level) {
        if(root == null)
            return;
        if(li.get(level) == null)
            li.add(root);
        leftViewOperation(root.left,li,level+1);
        leftViewOperation(root.right,li,level+1);
    }

    public static void rightView(Node root) {
        ArrayList<Node> ri = new ArrayList<Node>();
        rightViewOperation(root,ri,0);
        for(Node curr : ri) 
            System.out.print(curr.data + " ");
    }

    public static void rightViewOperation(Node root, ArrayList<Node> ri, int level) {
        if(root == null)
            return;
        if(ri.get(level) == null)
            ri.add(root);
        rightViewOperation(root.right,ri,level+1);
        rightViewOperation(root.left,ri,level+1);
    }

    public static ArrayList<Integer> topView(Node root) {
        Queue<Pair> q = new ArrayDeque<Pair>();
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        q.add(new Pair(0,root));
        while(!q.isEmpty()) {
            Pair temp = q.remove();
            if(!map.containsKey(temp.hd))
                map.put(temp.hd, temp.node.data);
            if(temp.node.left!= null)
                q.add(new Pair(temp.hd-1,temp.node.left));
            if(temp.node.right!= null)
                q.add(new Pair(temp.hd+1,temp.node.right));
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> e : map.entrySet())
            ans.add(e.getValue());
        return ans;
    }

    public static ArrayList<Integer> bottomView(Node root) {
        Queue<Pair> q = new ArrayDeque<Pair>();
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        q.add(new Pair(0,root));

        while(!q.isEmpty()) {
            Pair temp = q.remove();
            map.put(temp.hd, temp.node.data); // keep updating the map
            if(temp.node.right!= null)
                q.add(new Pair(temp.hd+1,temp.node.right));
            if(temp.node.left!= null)
                q.add(new Pair(temp.hd-1,temp.node.left));
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> e : map.entrySet())
            ans.add(e.getValue());
        return ans;
    }
}

class Node{
    Node left,right;
    int data;
    public Node(int data) {
        this.data = data;
    }
}

class Pair{
    int hd ;
    Node node;
    public Pair(int hd, Node node) {
        this.hd = hd;
        this.node = node;
    }
}
