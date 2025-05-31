package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 *
 * The syntax for these expressions is given as follows.
 *
 * 1) An expression is either an integer, a let-expression, an add-expression, a mult-expression,
 *      or an assigned variable. Expressions always evaluate to a single integer.
 *
 * 2) An integer could be positive or negative
 *
 * 3) A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let",
 *      then there are 1 or more pairs of alternating variables and expressions, meaning that the first variable v1
 *      is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2,
 *      and so on *sequentially*; and then the value of this let-expression is the value of the expression expr.
 *
 * 4) An add-expression takes the form (add e1 e2) where add is always the string "add",
 *      there are always two expressions e1, e2, and this expression evaluates to the addition
 *      of the evaluation of e1 and the evaluation of e2.
 *
 * 5) A mult-expression takes the form (mult e1 e2) where mult is always the string "mult",
 *      there are always two expressions e1, e2, and this expression evaluates to the multiplication
 *      of the evaluation of e1 and the evaluation of e2.
 *
 * 6) For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a
 *      lowercase letter, then zero or more lowercase letters or digits. Additionally for your convenience,
 *      the names "add", "let", or "mult" are protected and will never be used as variable names.
 *
 * 7) Finally, there is the concept of scope. When an expression of a variable name is evaluated,
 *      within the context of that evaluation, the innermost scope (in terms of parentheses)
 *      is checked first for the value of that variable, and then outer scopes are checked sequentially.
 *      It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 *
 *
 *  Examples:
 *
 * Input: (add 1 2)
 * Output: 3
 *
 * Input: (mult 3 (add 2 3))
 * Output: 15
 *
 * Input: (let x 2 (mult x 5))
 * Output: 10
 *
 * Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
 * Output: 14
 * Explanation: In the expression (add x y), when checking for the value of the variable x,
 * we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
 * Since x = 3 is found first, the value of x is 3.
 *
 * Input: (let x 3 x 2 x)
 * Output: 2
 * Explanation: Assignment in let statements is processed sequentially.
 *
 * Input: (let x 1 y 2 x (add x y) (add x y))
 * Output: 5
 * Explanation: The first (add x y) evaluates as 3, and is assigned to x.
 * The second (add x y) evaluates as 3+2 = 5.
 *
 * Input: (let x 2 (add (let x 3 (let x 4 x)) x))
 * Output: 6
 * Explanation: Even though (let x 4 x) has a deeper scope, it is outside the context
 * of the final x in the add-expression.  That final x will equal 2.
 *
 * Input: (let a1 3 b2 (add a1 1) b2)
 * Output 4
 * Explanation: Variable names can contain digits after the first character.
 *
 */


public class ParsingListExpressions {

    public int evaluate(String expression) {
        return evaluate(expression, new HashMap<>());
    }

    private int evaluate(String expr, Map<String, Integer> parent) {
        // assumes expr always starts with open bracket '('
        if(expr.charAt(0) != '(') {

            // positive or negative integer
            if(Character.isDigit(expr.charAt(0)) || expr.charAt(0) == '-') {
                return Integer.parseInt(expr);
            }

            // expr = single variable
            return parent.get(expr);
        }

        // create a new scope, add add all the previous values to it
        Map<String, Integer> map = new HashMap<>();
        map.putAll(parent);

        // assuming each expr has ( and ) bracket, exclude the first and last characters from the string
        expr = expr.substring(1, expr.length() - 1);
        List<String> tokens = parseExpr(expr);

        if(tokens.get(0).equals("add")) {

            return evaluate(tokens.get(1), map) + evaluate(tokens.get(2), map);


        } else if(tokens.get(0).equals("mult")) {

            return evaluate(tokens.get(1), map) * evaluate(tokens.get(2), map);

        } else if(tokens.get(0).equals("let")) {

            for(int i = 1; i < tokens.size() - 2; i += 2) {
                map.put(tokens.get(i), evaluate(tokens.get(i + 1), map));
            }

            return evaluate(tokens.get(tokens.size() - 1), map);

        } else {
            // error
        }
        // should not come here
        return 0;
    }

    private List<String> parseExpr(String expr) {
        // separate the values between two parentheses
        List<String> result = new ArrayList<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < expr.length(); ++i) {

            char c = expr.charAt(i);

            if(c == '(') {
                ++count;
            } else if(c == ')') {
                --count;
            }

            // assuming single space as delimiter
            if(count == 0 && c == ' ') {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        if(sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }


    public int evaluateWithLexer(String expression) {

        String[] tokens = {
                "\\(",
                "\\)",
                "add",
                "let",
                "mult",
                "[a-z][a-z0-9]*",
                "\\d+|\\-\\d+"
        };

        Pattern lispPattern = Pattern.compile(String.join("|", tokens));
        Matcher matcher = lispPattern.matcher(expression);
        List<String> lexerOutput = new ArrayList<>();
        while(matcher.find()) {
           lexerOutput.add(matcher.group());
        }

        LispParser lispParser = new LispParser(0, lexerOutput);
        Grammar g = lispParser.parse();

        return g.eval(new HashMap<>());
    }

    class Pair<T,V> {
        T t;
        V v;
        public Pair(T t, V v) {
            this.t = t;
            this.v = v;
        }

        public T getItem1() {
            return this.t;
        }

        public V getItem2() {
            return this.v;
        }

    }


    class Grammar {

        public int eval(Map<String, Integer> map) {
            return 0;
        }
    }

    class Add extends Grammar {
        Grammar op1;
        Grammar op2;

        public Add(Grammar a, Grammar b) {
            this.op1 = a;
            this.op2 = b;
        }

        public int eval(Map<String, Integer> map) {
            return op1.eval(map) + op2.eval(map);
        }
    }

    class Mult extends Grammar {
        Grammar op1;
        Grammar op2;

        public Mult(Grammar a, Grammar b) {
            this.op1 = a;
            this.op2 = b;
        }

        public int eval(Map<String, Integer> map) {
            return op1.eval(map) * op2.eval(map);
        }
    }

    class Let extends Grammar {
        List<Pair<String, Grammar>> vars;
        Grammar childExpr;

        public Let(List<Pair<String, Grammar>> vars, Grammar childExpr) {
            this.vars = vars;
            this.childExpr = childExpr;
        }

        public int eval(Map<String, Integer> map) {
            Map<String, Integer> newMap = new HashMap<>();
            newMap.putAll(map);

            for (Pair<String, Grammar> var : vars) {
                newMap.put(var.getItem1(), var.getItem2().eval(newMap));
            }

            return this.childExpr.eval(newMap);
        }
    }

    class Number extends Grammar {
        int num;
        public Number(String num) {
            this.num = Integer.parseInt(num);
        }

        public int eval(Map<String, Integer> map) {
            return this.num;
        }
    }

    class Variable extends Grammar {

        String id;
        public Variable(String var) {
            this.id = var;
        }

        public int eval(Map<String, Integer> map) {
            return map.get(this.id);
        }
    }

    class LispParser {
        /**
         * expr          := let_expr | add_expr | mult_expr | var | int
         * let_expr      := '(' 'let' var_expr_list expr ')'
         * var_expr_list := var expr var_expr_list | var expr
         * add_expr      := '(' 'add' expr expr ')'
         * mult_expr     := '(' 'mult' expr expr ')'
         * var           := [a-z]([a-zA-Z]|\d)*
         * int           := \d+
         */

        int tokenIndex;
        List<String> tokens;

        public LispParser(int tokenIndex, List<String> tokens) {
            this.tokenIndex = tokenIndex;
            this.tokens = tokens;
        }

        public Grammar parse() {
            String curToken = tokens.get(tokenIndex);

            if(curToken.equals("(")) {

                curToken = tokens.get(tokenIndex + 1);

                if(curToken.equals("let")) {

                    return parseLetExpr();

                } else if(curToken.equals("mult")) {

                    return parseMultExpr();

                } else if(curToken.equals("add")) {

                    return parseAddExpr();

                } else {
                    // error
                }

            } else if (isNum(curToken)) {

                return new Number(consume());

            } else {

                return new Variable(consume());
            }

            return null;
        }

        private String consume() {
            String token = tokens.get(tokenIndex);
            ++tokenIndex;
            return token;
        }

        private boolean isNum(String curToken) {
            try {
                Integer.parseInt(curToken);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }

        private Add parseAddExpr() {
            consume(); // (
            consume(); // add

            Grammar a = parse();
            Grammar b = parse();

            consume(); // )

            return new Add(a, b);
        }

        private Mult parseMultExpr() {
            consume(); // (
            consume(); // mult

            Grammar a = parse();
            Grammar b = parse();

            consume(); // )

            return new Mult(a, b);
        }

        private Let parseLetExpr() {
            consume(); // (
            consume(); // let

            List<Pair<String, Grammar>> vars = parseVarExpList();
            Grammar childExpr = parse();

            consume(); // ')'
            return new Let(vars, childExpr);
        }

        private List<Pair<String, Grammar>> parseVarExpList() {

            // note this has to be a list and not a map as we want to store all <var> <expression> occurrences
            // without overwriting and then dequeue them while evaluation
            List<Pair<String, Grammar>> vars = new ArrayList<>();

            while (!tokens.get(tokenIndex).equals("(") && tokenIndex + 1 < tokens.size()
                    && !tokens.get(tokenIndex + 1).equals(")")) {
                Variable var = new Variable(consume());
                Grammar expr = parse();
                vars.add(new Pair(var.id, expr));
            }
            return vars;
        }
    }



    public static void main(String[] args) {

        String[] testCases = {
                "(add 1 2)",
                "(mult 3 (add 2 3))",
                "(let x 2 (mult x 5))",
                "(let x 2 (mult x (let x 3 y 4 (add x y))))",
                "(let x 3 x 2 x)",
                "(let x 1 y 2 x (add x y) (add x y))",
                "(let x 2 (add (let x 3 (let x 4 x)) x))",
                "(let a1 3 b2 (add a1 1) b2)",
                "(let ab12x -3 b2 (add ab12x 1) b2)"
        };

        ParsingListExpressions p = new ParsingListExpressions();

        for(String exp : testCases) {
            System.out.println(p.evaluate(exp));
        }

//        System.out.println(p.evaluate(testCases[3]));
    }




}
