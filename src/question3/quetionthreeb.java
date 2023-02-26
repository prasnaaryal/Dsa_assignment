package question3;

public class quetionthreeb {
    // A recursive function to check if a string matches a given pattern

    public boolean solve(String str, String pattern, int i, int j ){
        // base case: if both strings have been processed, return true

        if(i<0&&j<0){
                return true;
            }
        // if the pattern string has been processed but not the input string,
        // return false as we can't match the remaining input characters to anything
        if(i>=0&&j<0){
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
            // if none of the above conditions are true, return false as there is no match


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
