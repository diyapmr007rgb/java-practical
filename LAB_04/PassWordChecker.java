import java.util.*;
class PassWordChecker
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the password");
        String password=sc.nextLine();
        if (isValidPassword(password))
        {
            System.out.println("Valid Password");
        }
        else
        {
            System.out.println("Invalid Password");
        }

    }
    public static boolean isValidPassword(String password)
    {
        if (password.length() < 8)
            return false;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
      
                for (char c : password.toCharArray())
        {
            if (Character.isUpperCase(c))
                hasUppercase = true;
            else if (Character.isLowerCase(c))
                hasLowercase = true;
            else if (Character.isDigit(c))
                hasDigit = true;
            else if (!Character.isLetterOrDigit(c))
                hasSpecialChar = true;
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    
       


    }

}
   