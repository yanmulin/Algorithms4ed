package ch1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixParen {
    public static void main(String[] args) {
        Stack<String> opt = new Stack<>();
        Stack<String> opr = new Stack<>();
        while (!StdIn.isEmpty()) {
            String in = StdIn.readString();
            if (in.equals("+") || in.equals("-")) {
                opt.push(in);
            } else if (in.equals("*") || in.equals("/")) {
                opt.push(in);
            } else if (in.equals(")")) { //这里括号里只有两个运算单位
                String opt1 = opt.pop();
                String opr2 = opr.pop();
                String opr1 = opr.pop();
                opr.push("( " + opr1 + " " + opt1 + " " + opr2 + " " + ") ");
            } else { // number
                opr.push(in);
            }
        }
        while (!opt.isEmpty()) {
            String opr2 = opr.pop();
            String opr1 = opr.pop();
            String opt1 = opt.pop();
            opr.push(opr1 + " " + opt1 + " " + opr2 + " ");
        }
        StdOut.println(opr.pop());
    }
}
