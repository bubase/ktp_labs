import java.util.ArrayList;

// поиск простых чисел от 2 до 100
public class Primes
{
    public static void main(String[] args)
    {
        System.out.println(createPrimes());
    }

    // определяет, является ли аргумент простым числом
    public static boolean isPrime(int n)
    {
        for(int i = 2; i < n; ++i)
        {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    // поиск простых чисел от 1 до 100
    public static ArrayList<Integer> createPrimes()
    {
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = 2; i < 100; ++i)
        {
            if(isPrime(i))
                primes.add(i);
        }
        return primes;
    }
}