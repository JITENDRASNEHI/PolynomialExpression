import java.util.HashMap;
import java.util.Map;
import java.util.*;
public class Polynomial {
    String expression1;
    String expression2;

    public Polynomial(String expression1,String expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public int[] multiply(HashMap<Integer, Integer> exp1, HashMap<Integer, Integer> exp2) {
        int size1 = getMaxDegree(exp1) + 1;
        int size2 = getMaxDegree(exp2) + 1;
        int prod[] = new int[size1 + size2 - 1];
        int pol1[] = new int[size1];
        int pol2[] = new int[size2];
        for (Map.Entry<Integer, Integer> entry : exp1.entrySet())
            pol1[entry.getKey()] = entry.getValue();
        for (Map.Entry<Integer, Integer> entry : exp2.entrySet())
            pol2[entry.getKey()] = entry.getValue();
        for (int i = 0; i < pol1.length; i++)
            for (int j = 0; j < pol2.length; j++)
                prod[i + j] = prod[i + j] + pol1[i] * pol2[j];
        return prod;
    }


    public int getMaxDegree(HashMap<Integer, Integer> exp) {
        Set<Integer> set = exp.keySet();
        int max = Integer.MIN_VALUE;
        for (int x : set)
            if (max < x) max = x;
        return max;
    }

    public String getExpression(int exp[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < exp.length; i++) {
            if(exp[i]!=0){
                if(i==0) sb.append(exp[i] + "+");
                else if(i==1) sb.append(exp[i] + "x^"+ "+");
                else sb.append(exp[i] + "x^" + i + "+");
            }
        }
        String outp = sb.toString();
        outp = outp.replaceAll("\\+\\-","-");
        return outp.substring(0,outp.length()-1);
    }

    public int[] add(HashMap<Integer, Integer> exp1, HashMap<Integer, Integer> exp2) {

        int size1 = getMaxDegree(exp1) + 1;
        int size2 = getMaxDegree(exp2) + 1;
        int addResult[] = new int[Math.max(size1, size2)];
        int pol1[] = new int[size1];
        int pol2[] = new int[size2];
        for (Map.Entry<Integer, Integer> entry : exp1.entrySet())
            pol1[entry.getKey()] = entry.getValue();

        for (Map.Entry<Integer, Integer> entry : exp2.entrySet())
            pol2[entry.getKey()] = entry.getValue();

        for (int i = 0; i < addResult.length; i++)
            addResult[i] = pol1[i] + pol2[i];

        return addResult;

    }


    public int[] subtract(HashMap<Integer, Integer> exp1, HashMap<Integer, Integer> exp2) {
        int size1 = getMaxDegree(exp1) + 1;
        int size2 = getMaxDegree(exp2) + 1;
        int subResult[] = new int[Math.max(size1, size2)];
        int pol1[] = new int[size1];
        int pol2[] = new int[size2];
        for (Map.Entry<Integer, Integer> entry : exp1.entrySet())
            pol1[entry.getKey()] = entry.getValue();

        for (Map.Entry<Integer, Integer> entry : exp2.entrySet())
            pol2[entry.getKey()] = entry.getValue();

        for (int i = 0; i < subResult.length; i++)
            subResult[i] = pol1[i] - pol2[i];

        return subResult;

    }

    private String[] modifyExpression(String expression1) {
        expression1 = expression1.replaceAll("\\s", "");
        expression1 = "+" + expression1;
        expression1 = expression1.replaceAll("\\+x", "+1x");
        expression1 = expression1.replaceAll("\\-x", "-1x");
        expression1 = expression1.replaceAll("x\\^", "P");
        expression1 = expression1.replaceAll("x", "x^1");
        expression1 = expression1.replaceAll("P", "x^");
        expression1 = expression1.replaceAll("\\-", "+-");
        String arr[] = expression1.split("\\+");
        return arr;
    }

    private HashMap<Integer, Integer> createMap(String arr[]) {

        HashMap<Integer, Integer> coeffExpoMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                if (!arr[i].contains("x^"))
                    coeffExpoMap.put(0, Integer.parseInt(arr[i]));
                else {
                    String tokens[] = arr[i].split("x\\^");
                    coeffExpoMap.put(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]));
                }
            }
        }

        return coeffExpoMap;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        String e1 = sc.nextLine();
        String e2 = sc.nextLine();
        Polynomial obj = new Polynomial(e1,e2);
        String arr1[] = obj.modifyExpression(e1);
        String arr2[] = obj.modifyExpression(e2);
        int res[] = obj.subtract(obj.createMap(arr1),obj.createMap(arr2));
//        for(int x:res)
//            System.out.println(x+" " );
         System.out.println(obj.getExpression(res));
        //System.out.print(res.toString());
//        System.out.println(obj.createMap(arr1));
//        System.out.println(obj.createMap(arr2));
    }
}
