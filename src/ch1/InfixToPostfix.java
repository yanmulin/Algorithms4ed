import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> opt = new Stack<>();
        Stack<String> opr = new Stack<>();
        while (!StdIn.isEmpty()) {
            String in = StdIn.readString();
            if (in.equals("+") || in.equals("-")) {
                while (!opt.isEmpty()) {
                    String opt1 = opt.pop();
                    if (opt1.equals("(")) {
                        opt.push(opt1);
                        break;
                    }
                    String opr2 = opr.pop();
                    String opr1 = opr.pop();
                    opr.push(opr1 + " " + opr2 + " " + opt1);
                }
                opt.push(in);
            } else if (in.equals("*") || in.equals("/")) {
                opt.push(in);
            } else if (in.equals("(")) {
                opt.push(in);
            } else if (in.equals(")")) {
                String opt1 = opt.pop();
                while (!opt1.equals("(")) {
                    String opr2 = opr.pop();
                    String opr1 = opr.pop();
                    opr.push(opr1 + " " + opr2 + " " + opt1);
                    opt1 = opt.pop();
                }
            } else { // number
                opr.push(in);
            }
        }
        while (!opt.isEmpty()) {
            String opr2 = opr.pop();
            String opr1 = opr.pop();
            String opt1 = opt.pop();
            opr.push(opr1 + " " + opr2 + " " + opt1);
        }
        StdOut.println(opr.pop());
    }
}
