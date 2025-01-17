import java.util.LinkedList;
import java.util.Queue;

public class Base {

    
static class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data=data;
        left=null;
        right=null;
        
        
    }
}
    
    static class BinaryTree {
        static int idx=-1;
        public static Node buildTree(int[] node){ //o(n) idx goes to  length of node[]
            idx++;
            if(node[idx]==-1){
                return null;
            }
                Node newnode=new Node(node[idx]);
                newnode.left=buildTree(node);
                newnode.right=buildTree(node);
                 return newnode;
            }
        
        
        public static void preorder(Node root){ //o(n)
            if(root==null){
                System.out.print("-1"+" ");
                return ;
                
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
            
            
        }

        public static void inOrder(Node root){ //o(n)
            if(root==null){
                return ;
            }
            inOrder(root.left);
            System.out.print(root.data+" ");
            inOrder(root.right);


        }

        public static void postOrder(Node root){ //O(n)
            if(root==null){
                return ;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data+" ");
        }
        public static void levelOrder(Node root){
            if(root==null){
                return ;
            }
            Queue <Node> q=new LinkedList<>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {

                Node temp=q.remove();
                if(temp==null){
                    System.out.println();
                    
                    if (!q.isEmpty()) {
                        q.add(null);
                        
                    }
                }
                else{

                    System.out.print(temp.data+" ");
                    
                    if(temp.left!=null){
                        q.add(temp.left);}
                    if(temp.right!=null){
                        q.add(temp.right);}

                }

                
            }



        }

        public  static int Height(Node root){ //o(n)
            if(root==null){
                return 0;
            }
            
            int lh=Height(root.left);
            int rh=Height(root.right);
            return Math.max(lh,rh)+1;

        }
        public  static int Count(Node root){ //o(n)
            if(root==null){
                return 0;
            }
            
            int lc=Count(root.left);
            int rc=Count(root.right);
            return  lc+rc+1;

        }
        
    
    // public  static int sum(Node root){ //o(n)
    //     if(root==null){
    //         return 0;
    //     }
        
    //     int ls=sum(root.left);
    //     int rs=sum(root.right);
    //     return  ls+rs+root.data;

    }
    
//     public  static int sum(Node root){ //o(n) iterative method using levelorder traversal
//         if(root==null){
//             return 0;
//         }
//         Queue <Node> q=new LinkedList<>();
//         q.add(root);
//         q.add(null);int sum=0;
//         while (!q.isEmpty()) {

//             Node temp=q.remove();
//             if(temp==null){
//                 System.out.println("level is changed");
                
//                 if (!q.isEmpty()) {
//                     q.add(null);
                    
//                 }
//             }
//             else{

//                 // System.out.print(temp.data+" ");
//                 sum+=temp.data;
                
//                 if(temp.left!=null){
//                     q.add(temp.left);}
//                 if(temp.right!=null){
//                     q.add(temp.right);}

//             }

            
//         }
//         return sum;
        

//     }
    
// }
    
    
    

    
    
	public static void main(String[] args) {
		
		BinaryTree bs=new BinaryTree();
		int arr[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
		
		Node root=bs.buildTree(arr);
		// System.out.println(bs.idx);
		bs.preorder(root);
        System.out.println();
		bs.inOrder(root);
        System.out.println();
        bs.postOrder(root);
        System.out.println();
        bs.levelOrder(root);
        System.out.println("Sum of Tree  "+bs.sum(root));
        

		
	}

}
