package question3;

public class quetionthreeb {
        public boolean solve(String str, String pattern, int i, int j ){
            if(i<0&&j<0){
                return true;
            }if(i>=0&&j<0){
                return  false;
            }
            if(i<0&&j>=0){
                for(int k=0;k<=j; k++){
                    if(pattern.charAt(k)!='@'){
                        return false;
                    }
                }
            }

            if(str.charAt(i)==pattern.charAt(j)||pattern.charAt(j)=='#'){
                return solve(str,pattern,i-1,j-1);
            }
            else if(pattern.charAt(j)=='@'){
                return (solve(str,pattern,i-1,j)||solve(str,pattern,i,j-1));
            }

            else{
                return false;
            }
        }



        public static void main(String[] args) {

            quetionthreeb pm = new quetionthreeb();
            String str ="abcde";
            String pattern="a@cde";
            System.out.println(pm.solve(str,pattern,4,4));
        }
    }
