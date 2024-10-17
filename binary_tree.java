import java.util.*;

public class BinaryTree {
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();

        System.out.println("The inOrder is:");
        inOrder(root);
        System.out.println();
        
        System.out.println("The preOrder is:");
        preOrder(root);
        System.out.println();
        
        System.out.println("The postOrder is:");
        postOrder(root);
        System.out.println();
        
        System.out.println("Height of tree is: " + height(root));
        System.out.println("Size of tree is: " + size(root));
        System.out.println("The maximum node is: " + maximum(root));
        System.out.println("The minimum node is: " + minimum(root));
        System.out.println("The sum of all nodes is: " + sum(root));

        System.out.println("The level order traversal of tree is:");
        levelOrderTraversal(root);
        System.out.println();

        System.out.println("The left viewpoint of the tree is:");
        leftView(root);
        System.out.println();

        System.out.println("The right viewpoint of the tree is:");
        rightView(root);
        System.out.println();

        System.out.println("The top viewpoint of the tree is:");
        ArrayList<Integer> topViewResult = topView(root);
        for (int val : topViewResult) {
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("The bottom viewpoint of the tree is:");
        ArrayList<Integer> bottomViewResult = bottomView(root);
        for (int val : bottomViewResult) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static Node createTree() {
        System.out.print("Enter data (-1 for null): ");
        int data = sc.nextInt();
        if (data == -1) {
            return null;
        }
        Node root = new Node(data);
        System.out.println("Enter left child data of " + data);
        root.left = createTree();
        System.out.println("Enter right child data of " + data);
        root.right = createTree();
        return root;
    }

    public static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int size(Node root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    public static int maximum(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(root.data, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(root.data, Math.min(minimum(root.left), minimum(root.right)));
    }

    public static int sum(Node root) {
        if (root == null) {
            return 0;
        }
        return root.data + sum(root.left) + sum(root.right);
    }

    public static void levelOrderTraversal(Node root) {
        if (root == null) return; // Handle null tree case

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
    }

    public static void leftView(Node root) {
        ArrayList<Node> li = new ArrayList<>();
        leftViewOperation(root, li, 0);
        for (Node curr : li) {
            System.out.print(curr.data + " ");
        }
    }

    public static void leftViewOperation(Node root, ArrayList<Node> li, int level) {
        if (root == null) {
            return;
        }
        // Check if the level is already represented in the left view
        if (level == li.size()) {
            li.add(root);
        }
        leftViewOperation(root.left, li, level + 1);
        leftViewOperation(root.right, li, level + 1);
    }

    public static void rightView(Node root) {
        ArrayList<Node> ri = new ArrayList<>();
        rightViewOperation(root, ri, 0);
        for (Node curr : ri) {
            System.out.print(curr.data + " ");
        }
    }

    public static void rightViewOperation(Node root, ArrayList<Node> ri, int level) {
        if (root == null) {
            return;
        }
        // Check if the level is already represented in the right view
        if (level == ri.size()) {
            ri.add(root);
        }
        rightViewOperation(root.right, ri, level + 1);
        rightViewOperation(root.left, ri, level + 1);
    }

    public static ArrayList<Integer> topView(Node root) {
        Queue<Pair> q = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();

        q.add(new Pair(0, root));
        while (!q.isEmpty()) {
            Pair temp = q.remove();
            if (!map.containsKey(temp.hd)) {
                map.put(temp.hd, temp.node.data);
            }
            if (temp.node.left != null) {
                q.add(new Pair(temp.hd - 1, temp.node.left));
            }
            if (temp.node.right != null) {
                q.add(new Pair(temp.hd + 1, temp.node.right));
            }
        }

        return new ArrayList<>(map.values());
    }

    public static ArrayList<Integer> bottomView(Node root) {
        Queue<Pair> q = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();

        q.add(new Pair(0, root));
        while (!q.isEmpty()) {
            Pair temp = q.remove();
            map.put(temp.hd, temp.node.data); // Keep updating the map
            if (temp.node.left != null) {
                q.add(new Pair(temp.hd - 1, temp.node.left));
            }
            if (temp.node.right != null) {
                q.add(new Pair(temp.hd + 1, temp.node.right));
            }
        }

        return new ArrayList<>(map.values());
    }
}

class Node {
    Node left, right;
    int data;

    public Node(int data) {
        this.data = data;
    }
}

class Pair {
    int hd;
    Node node;

    public Pair(int hd, Node node) {
        this.hd = hd;
        this.node = node;
    }
}
