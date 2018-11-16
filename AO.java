package com.company.AO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AO {



   public static boolean algorithmAO(NodeAO root){


       if(root.operators==null)return false; //node leaf and not final
       if(!root.visited){
           root.visited=true;
           // calc all operators in this node
           root=calcOperators(root);
           // update parent
           root=modifyAncestor(root);

           Operators curr=root.operatorsQueue.peek();
           // subProblem
           //System.out.println(curr.cost+" "+curr.staticCost);
           for (NodeAO node:curr.nodeAOS) {
               node.parent=root;
               node.parentOp=curr;
               if(!node._final)
                    return algorithmAO(node);
           }
       }
       else {
           Operators curr=root.operatorsQueue.peek();
           for (NodeAO node:curr.nodeAOS) {
               if(!node._final)
                    return algorithmAO(node);
           }
       }
       return true;
   }

    private static NodeAO calcOperators(NodeAO root) {
        for (Operators op:root.operators) {
            if(!op.isItAnd){
                op.cost=op.staticCost+root.heristic;
            }
            else {
                op.cost+=op.staticCost;
                for (NodeAO n:op.nodeAOS) {
                    op.cost+=n.heristic;
                }
            }
            root.operatorsQueue.add(op);
        }
        return root;
    }

    private static NodeAO modifyAncestor(NodeAO root) {
       while (root.parent!=null) {
           NodeAO parent = root.parent;
           Operators perantOp = root.parentOp;
           parent.operatorsQueue.remove(perantOp);
           perantOp.cost =perantOp.staticCost+root.operatorsQueue.peek().cost;
           parent.operatorsQueue.add(perantOp);
           root=parent;
       }
       return root;
    }




}
