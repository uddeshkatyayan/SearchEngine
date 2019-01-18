import java.util.*;
public class MySort
{
  public MySort()
  {
  }
  public ArrayList<SearchResult> sortThisList(Myset<SearchResult> listOfSortableEntries)
  {
    try
    {
      MyLinkedList<SearchResult> listSearches = listOfSortableEntries.list;
      ArrayList<SearchResult> sr=new ArrayList<SearchResult>();
      sr.add(0,listSearches.readGiven(0));
      for(int i=1;i<listSearches.getSize();i++)
      {
        SearchResult psr=listSearches.readGiven(i);
        for(int j=0;j<i;j++)
        {
          if(psr.compareTo(sr.get(j))>=0)
          {
            if(j==0)
            {
              sr.add(0,psr);
              break;
            }
            else
            {
              sr.add(j,psr);
              break;
            }
          }
          else
          {
            if(j==sr.size()-1)
            {
              sr.add(sr.size(),psr);
            }
          }
        }
      }
      return sr;
    }catch(myException gg){
      return null;
    }
  }
  public static void main(String args[])
  {

      // int[] arr= new int[]{7,3,7,8,9,4,4,3,8,9,10,8,9,0,76,87,76};
      // ArrayList<Integer> sr=new ArrayList<Integer>();
      // sr.add(0,arr[0]);
      // for(int i=1;i<arr.length;i++)
      // {
      //   int psr=arr[i];
      //   for(int j=0;j<i;j++)
      //   {
      //     if(psr>=sr.get(j))
      //     {
      //       System.out.println(psr+" "+" "+sr.get(j));
      //       System.out.println((psr>=sr.get(j)));
      //       if(j==0)
      //       {
      //         sr.add(0,psr);
      //         break;
      //       }
      //       else
      //       {
      //         sr.add(j,psr);
      //         break;
      //       }
      //     }
      //     else
      //     {
      //
      //       if(j==sr.size()-1)
      //       {
      //         sr.add(sr.size(),psr);
      //       }
      //     }
      //   }
      // }
      // // ArrayList<Integer> sr=new ArrayList<Integer>();
      // // sr.add(98);
      // // sr.add(0,45);
      // // sr.add(1,665);
      // for(int i=0;i<sr.size();i++)
      // {
      //   System.out.println(sr.get(i));
      // }

  }

}
