package algos;

public class PalindromeCheck {

    public static void main(String[] args) {
        String input = "kacdefgbgfedcak";
        System.out.println("Input:"+ input);
        for(int i = 0; i <= input.length()/2 -1; i++) {
            int j = input.length() - 1 -i;
            System.out.println(input.charAt(i)+"_"+input.charAt(j));
            if(input.charAt(i) != input.charAt(j)) {
                System.out.println("Not a palindrome");
                break;
            }
            if(i == input.length()/2 -1)
                System.out.println("Yes a palindrome");
        }
    }
}
