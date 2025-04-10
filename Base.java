import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

import ll.stack;

import java.awt.List;
import java.util.*;

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
    static class Info{
        int diam;
        int hei;
        Info(int d,int h){
            this.diam=d;
            this.hei=h;

        }

    }
    static class NodeInfo{
        int hd;
        Node node;
        NodeInfo(int hd,Node node){
            this.hd=hd;
            this.node=node;


        }

    }

    
    static class BinaryTree {
        static int idx=-1;
public static Node buildTree(int arr[]){
    idx=-1;
   return helperbuildTree(arr);
}
        public static Node helperbuildTree(int[] node){ //o(n) idx goes to  length of node[]
            idx++;
            if(node[idx]==-1){
                return null;
            }
                Node newnode=new Node(node[idx]);
                newnode.left=helperbuildTree(node);
                newnode.right=helperbuildTree(node);
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



        public static void levelOrder(Node root){ //o(n)
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
                        
                    }else{
                        break;
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

        public static int height(Node root){
            if(root==null){
                return 0;

            }
            int lh=height(root.left);
            int rh=height(root.right);
            return Math.max(lh,rh)+1;



        }
        
    
    
    // public static int diameter(Node root){
    //     if(root==null){
    //         return 0;

    //     }

    //     int ld=diameter(root.left);  
    //     int rd=diameter(root.right);
    //     int lh=height(root.left);
    //     int rh=height(root.right);
    //     int sd=rh+lh+1;
    //     return Math.max(Math.max(ld,rd),sd);
        
        
    //         }
            
        
        
           
        public static Info diameter(Node root){ //O(n)
            if(root==null){
                return new Info(0,0);
               
            }

            Info ld=diameter(root.left);
            Info rd=diameter(root.right);

            int sd=(ld.hei+rd.hei+1);
            int fdiam=Math.max(Math.max(ld.diam,rd.diam),sd);
            int h=Math.max(ld.hei,rd.hei)+1;
            return new Info(fdiam,h);

        }
    
        

      public static boolean isIdentical(Node root,Node subroot){

        if(root ==null && subroot== null){
            return true;
        }
        if(root==null || subroot==null){
        return false;
        }        
        if(root.data!=subroot.data){
            return false;
        }
        if(!isIdentical(root.left,subroot.left)){
            return false;
        }
        if(!isIdentical(root.right,subroot.right)){
            return false;
        }
        return true;
    }

    public static Boolean subtree(Node root, Node subroot){

        if(root==null){
            return false;
        }
        if(root.data==subroot.data){
            if(isIdentical(root,subroot)){
                return true;
            }
        }

        return subtree(root.left,subroot)|| subtree(root.right,subroot);
    }
        

    public static void topView(Node root){
        if(root==null){
            return ;
        }
        int max=0;
        int min=0;
        Queue <NodeInfo> q=new LinkedList<>();
        HashMap<Integer,Node> map=new HashMap<>();
        q.add(new NodeInfo(0, root));
        q.add(null);

        while (!q.isEmpty()) {

             NodeInfo curr=q.remove();
            if(curr==null){
                
                
                if (!q.isEmpty()) {
                    q.add(null);
                    
                }else{
                    break;
                }
            }
            else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd, curr.node);
                }


               
                
                if(curr.node.left!=null){
                    min=Math.min(min, curr.hd-1);
                  
                    q.add(new NodeInfo(curr.hd-1, curr.node.left));}
                    if(curr.node.right!=null){
                  
                        max=Math.max(max, curr.hd+1);
              
                        q.add(new NodeInfo(curr.hd+1, curr.node.right));}

            }

            
        }
        System.out.println((map));
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data);
        }




    }

    public static void BottomViewHelper(Node root,HashMap<Integer,Node> map,int hd,int arr[]){
        if(root==null){
            return ;
        }

        
        
        BottomViewHelper(root.left,map,hd-1,arr);
        BottomViewHelper(root.right,map,hd+1,arr);
        if(!map.containsKey(hd)){
            arr[0]=Math.min(arr[0],hd);
            arr[1]=Math.max(arr[1],hd);
            map.put(hd,root);
        }




    }
    public static void BottomView(Node root){
        HashMap<Integer,Node> map=new HashMap<>();
        int arr[]=new int[2];
        BottomViewHelper(root, map, 0,arr);
        for(int i=arr[0];i<=arr[1];i++){
            System.out.print(map.get(i).data);
        }



    }
//     public static void printKthLevel(Node root,int k){
//         Queue<Node> q=new LinkedList<>();
//         q.add(root);
//         q.add(null);
// int lvl=1;
//         while (!q.isEmpty()) {

//             Node curr=q.remove();
//             if(curr==null){
//                 lvl++;
//                 if(!q.isEmpty()){
//                     q.add(null);

//                 }else{
//                     break;
//                 }

//             }else{

//                 if(k==lvl){
                   
//                     System.out.print(curr.data+" ");

//                 }

//                 if(curr.left!=null){
//                     q.add(curr.left);

//                 }if(curr.right!=null){
//                     q.add(curr.right);

//                 }

//             }

//         }

//     }

public static void printKthLevel(Node root , int k){
    helperPrintKthLevel(root, k,1);
}

    public static void helperPrintKthLevel(Node root ,int k,int lvl){
        if(root==null){
            return ;

        }

        if(lvl==k){
            System.out.print(root.data+" ");
            return;
            

        }
        helperPrintKthLevel(root.left, k,lvl+1);


        helperPrintKthLevel(root.right, k,lvl+1);



    }
    public static Node lowestCommonAncestor(Node root, int n1,int n2){

        ArrayList <Node> path1=new ArrayList<>();
        ArrayList <Node> path2=new ArrayList<>();

        path(root, n1,path1 );

        path(root, n2,path2 );

        int idx=0;

        while(idx<path1.size()-1 && idx< path2.size()-1){
            if(path1.get(idx)!=path2.get(idx)){
                break;
            }
            idx++;


        }
        idx--;
        return path1.get(idx);


    }


    public static void path(Node root,int node,ArrayList <Node> path){
        if(root==null){
            return ;
        }
        path.add(root);
        if(root.data==node){
            return ;
        }
        path(root.left,node,path);
        path(root.right,node,path);

        
        path.remove(path.size()-1);
        

       
    }

    public static Node lcA(Node root , int n1, int n2){//O(n)
        if(root==null){
            return null;
        }
        if(root.data==n1 || root.data==n2){
            return root;
        }
        
        Node lftn= lcA(root.left, n1, n2);
        Node rgtn=lcA(root.right, n1, n2);

       if(lftn!=null && rgtn!=null){
        return root;

       }
       if(lftn==null)
           {return rgtn;}
    
    else{
        return lftn;

       }
       



    }
public static int distance(Node root,int n1){
    if(root==null){
        return-1;
    }
    if(root.data==n1){
        return 0;

    }
    int lf=distance(root.left, n1);
    if(lf!=-1){
        return lf+1;
    }
    int rt=distance(root.right, n1);
    if(rt!=-1){
        return rt+1;
    }
    return rt;

}
    public static int minDisbtwnodes( Node root,int n1,int n2){
        Node lca=lcA(root, n1, n2);

        int d1=distance(lca,n1);
        int d2=distance(lca,n2);

        return d1+d2;




    }
        
    public static int printKthAnccestor(Node root,int k, int n){

        if(root==null){
            return -1;
        }
        if(root.data==n){
            return 0;
        }
        int ld=printKthAnccestor(root.left, k, n);
        int rd=printKthAnccestor(root.right, k, n);
        int distance=0;
if(ld!=-1 || rd!=-1){

    distance=1+Math.max(ld,rd);
    if(distance==k){
        System.out.println(k+"th Ancestor : "+root.data);
    }

    return distance;
    
}

return ld;




       
    }

    public static int transformSumTree(Node root){
        if(root==null){
            return 0;
        }
        int ld=transformSumTree(root.left);
        int rd=transformSumTree(root.right);
        int data=root.data;
        root.data=ld+rd;
        return data+ld +rd;


    }
}



            public static void main(String[] args) {
		
		BinaryTree bs=new BinaryTree();
		BinaryTree bs2=new BinaryTree();
		int arr[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
                    //   1
                    //  / \
                //     2   3
                //    / \   \ 
               //    4   5   6
		
		Node root=bs.buildTree(arr);
        bs.levelOrder(root);
		// System.out.println(bs.idx);
		// System.out.println(bs2.idx);
		// Node root2=bs2.buildTree(arr);

		// bs.preorder(root);
        // System.out.println();
		// bs2.preorder(root2);
        // System.out.println();
		// bs.inOrder(root);
        // System.out.println();
        // bs.postOrder(root);
        // System.out.println();
        // System.out.println(bs.diameter(root).diam);
        // System.out.println(bs.diameter(root).hei);System.out.println();
        // bs.topView(root);
        // System.out.println();
        // bs.BottomView(root);
        // bs.printKthLevel(root,3);
        
        //    Node lca= bs.lcA(root,4,6);
        // System.out.println(bs.minDisbtwnodes(root, 5, 4));
        // System.out.println(bs.printKthAnccestor(root, 1, 5));
        bs.transformSumTree(root);
        System.out.println();
        
        bs.levelOrder(root);

        
		
	}

    
}
