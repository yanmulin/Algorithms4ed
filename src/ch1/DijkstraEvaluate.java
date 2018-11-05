package ch1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraEvaluate {
    public static int evaluate(String[] expr) {
        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        for (String s : expr) {
            if (s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals("+") || s.equals("-")) ops.push(s);
            else if (s.equals("(")) ;//ignore
            else if (s.equals(")")) {
                switch (ops.pop()) {
                    case "+":
                        vals.push(vals.pop() + vals.pop());
                        break;
                    case "-":
                        vals.push(vals.pop() - vals.pop());
                        break;
                    case "*":
                        vals.push(vals.pop() * vals.pop());
                        break;
                    case "/":
                        vals.push(vals.pop() / vals.pop());
                        break;
                }
            } else {//number
                vals.push(Integer.parseInt(s));
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        if (args.length == 0) args = StdIn.readAllStrings();
        StdOut.println(DijkstraEvaluate.evaluate(args));
    }
}
