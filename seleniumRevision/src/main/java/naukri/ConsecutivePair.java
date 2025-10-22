    package naukri;

    public class ConsecutivePair {

        public static void main(String[] args) {
            String s = "AGVVVGAG";
            int count = 0, i = 0;
            while (i <= s.length()-2){
                char x = s.charAt(i);
                if (x == 'A'){
                    for (int j= i+1;j<s.length();j++){
                        char y = s.charAt(j);
                        if(y=='G'){
                            System.out.println("came here" + i + " " +j + "  " + x + "  " +y);
                            count++;
                        }
                    }
                }
                i++;
            }
            System.out.println("====" + count);
        }


    }
