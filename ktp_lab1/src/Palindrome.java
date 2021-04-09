public class Palindrome
{
    public static void main(String[] args)
    {
        for(int i = 0; i < args.length; i++)
        {
            String s = args[i];
            System.out.println(String.format("%s это %s", s, isPalindrome(s) ? "палиндром" : "не палиндром"));
        }
    }
    // изменяет строку, меняя символы в обратном порядке
    public static String reverseString(String s)
    {
        String str = "";
        for(int i = s.length() - 1; i >= 0; --i)
            str += s.charAt(i);
        return str;
    }
    // проверяет, является ли строка палиндромом
    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));
    }
}

