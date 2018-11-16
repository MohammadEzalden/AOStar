package com.company.AO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NodeAO {
    public String name ;
    public int heristic;
    public boolean _final;
    public boolean visited=false;
    public NodeAO parent=null;
    public Operators parentOp=null;
    public ArrayList<Operators> operators;
    public PriorityQueue<Operators>operatorsQueue=new PriorityQueue<>(operatorsComparator);

    public static Comparator<Operators> operatorsComparator=new Comparator<Operators>() {
        @Override
        public int compare(Operators o1, Operators o2) {
            if(o1.cost>o2.cost)return 1;
            if(o1.cost<o2.cost)return -1;
            return 0;
        }
    };


    public NodeAO(String name, int heristic, boolean _final) {
        this.name = name;
        this.heristic = heristic;
        this._final = _final;
    }
}
