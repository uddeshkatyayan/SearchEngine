import java.util.*;
public class StreamingAlgorith
{
  public static void main(String argd[])
  {
    Scanner sc= new Scanner(System.in);
    int m=Integer.parseInt(sc.nextLine());
    int maj=0;
    int a[]=new int[m];
    for(int i=0;i<m;i++)
    {
      a[i]=Integer.parseInt(sc.nextLine());
    }
    int count =0;
    for(int i=0; i<m;i++ )
    {
      if(count==0)
        maj=a[i];
      if(maj==a[i])
        count++;
      else
        count--;
    }
    System.out.println("count: "+count);
    System.out.println("maj="+maj);
  }
}
