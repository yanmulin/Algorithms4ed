package ch1;

public class DijkstraEvaluate{
    public static String evaluate(String[] expr){
        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        for (String s: expr){
            if (s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals("+") || s.equals("-")) ops.push(s);
            else if (s.equals("(")) ;//ignore
            else if (s.equals(")")){
                switch (ops.pop()){
                    case "+": vals.push(vals.pop() + vals.pop());break;
                    case "-": vals.push(vals.pop() - vals.pop());break;
                    case "*": vals.push(vals.pop() * vals.pop());break;
                    case "/": vals.push(vals.pop() / vals.pop());break;
                }
            }else {//number
                vals.push(Integer.parseInt(s));
            }
        }
        return vals.pop();
    }
}