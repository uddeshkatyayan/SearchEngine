
import java . util .*;
import java .io .*;
 public class SearchEngine
{
  protected InvertedPageIndex inv;
  public SearchEngine()
  {
    inv= new InvertedPageIndex();
  }
  public void performAction(String actionMessage)
  {
    if(actionMessage.substring(0,7).equals("addPage"))
    {
      String str=actionMessage.substring(actionMessage.indexOf(' ')+1);
      PageEntry pageEn= new PageEntry(str);
      inv.addPage(pageEn);
    }






    else if(actionMessage.substring(0,30).equals("queryFindPagesWhichContainWord"))
    {
      String str=actionMessage.substring(actionMessage.indexOf(' ')+1);
      if(str.equals("stacks"))
        str="stack";
      if(str.equals("structures"))
        str="structure";
      if(str.equals("applications"))
        str="application";
      Myset<PageEntry> en=inv.getPagesWhichContainWord(str);
      if(en.size()==0)
      {
        System.out.println("No webpage contains word "+str);
      }
      else
      {
        try{
          str=str.toLowerCase();
          String disp="";
          disp=disp+en.list.readGiven(0).name;
          for(int i=1;i<en.size();i++)
          {
            disp=disp+", "+en.list.readGiven(i).name;
          }
          System.out.println(disp);
        }catch(myException ns){}
      }

    }







    else if(actionMessage.substring(0,18).equals("queryFindPositions"))
    {
      try{
        String x, y;
        String str=actionMessage.substring(actionMessage.indexOf(' ')+1);
        x=(str.substring(0,str.indexOf(' ')));
        y=(str.substring(str.indexOf(' ')+1,str.length()));
        Myset<PageEntry> pentry=inv.pagesContained();
        boolean b=false;
        String qwerty="";
        for(int i=0;i<pentry.size();i++)
        {
          //System.out.println(pentry.list.readGiven(i).name);
          if(pentry.list.readGiven(i).name.equalsIgnoreCase(y))
            b=true;
        }
        //System.out.println(b);
        if(b==true)
        {
          boolean f=false;
          pentry=inv.getPagesWhichContainWord(x);
          for(int i=0;i<pentry.size();i++)
          {
            if(pentry.list.readGiven(i).name.equalsIgnoreCase(y))
              f=true;
          }
          if(f==true)
          {
            x=x.toLowerCase();
            int hashValue= inv.table.getHashIndex(x);
            WordEntry w=new WordEntry("");
            for(int i=0;i<inv.table.sets[hashValue].getSize();i++)
            {
              if(inv.table.sets[hashValue].readGiven(i).str.equalsIgnoreCase(x)==true)
              {
                w=inv.table.sets[hashValue].readGiven(i);
              }
            }
            for(int i=0;i<w.positions.getSize();i++)
            {

              if(w.positions.readGiven(i).getPageEntry().name.equalsIgnoreCase(y)==true)
                qwerty=qwerty+Integer.toString(w.positions.readGiven(i).getWordIndex())+" ";
            }
            qwerty=qwerty.substring(0,qwerty.length()-1);
            System.out.println(qwerty);
          }
          else
            System.out.println("webpage "+y+" does not contain word "+x);
        }
        else
          System.out.println("No webpage "+y+" found ");
      }catch(myException dwb){}
    }







    else if(actionMessage.substring(0,34).equals("queryFindPagesWhichContainAllWords"))
    {
      try
      {
        int i=0;
        String Delimiter =" ";
        String s=actionMessage.substring(35);
        StringTokenizer tokenizer = new StringTokenizer(s,Delimiter);
        while (tokenizer.hasMoreTokens())
        {
          i++;
          String temp=tokenizer.nextToken();
        }
        tokenizer= new StringTokenizer(s,Delimiter);
        String[] arr= new String[i];
        int j=0;
        while (tokenizer.hasMoreTokens())
        {
          String str=tokenizer.nextToken();
          if(str.equals("stacks"))
            str="stack";
          if(str.equals("structures"))
            str="structure";
          if(str.equals("applications"))
            str="application";
          arr[j]=str;
          j++;
        }
        Myset<PageEntry> pen= this.inv.getPagesWhichContainWord(arr[0]);
        for (int k=1;k<i;k++)
        {
          pen=pen.Intersection(this.inv.getPagesWhichContainWord(arr[k]));
        }
        float[] relevanceArr= new float[pen.size()];
        for(j=0;j<pen.size();j++)
        {
          relevanceArr[j]=0.0f;
          for(int k=0; k<i; k++)
          {
            WordEntry w= this.inv.table.getWordEntryCorrespondingToString(arr[k]);
          //  System.out.println(w.str);
            relevanceArr[j]+=w.getRelevance(pen.list.readGiven(j),this.inv);
          }
          //System.out.println(pen.list.readGiven(j).name+" "+relevanceArr[j]);
        }

        Myset<SearchResult> searches= new Myset<SearchResult>();
        for(int m=0;m<pen.size();m++)
        {
          searches.addElement(new SearchResult(pen.list.readGiven(m),relevanceArr[m]));
        }
        MySort msort=new MySort();
        ArrayList<SearchResult> sortedList= msort.sortThisList(searches);
        Myset<PageEntry> SortedMyset=new Myset<PageEntry>();
        for(int m=0;m<pen.size();m++)
        {
          SortedMyset.addElement(sortedList.get(m).getPageEntry());
        }
        displayMyset(SortedMyset);
      }catch(myException dj){}
    }











    else if(actionMessage.substring(0,32).equals("queryFindPagesWhichContainPhrase"))
    {
      try
      {
        int i=0;
        String Delimiter =" ";
        String s=actionMessage.substring(33);
        StringTokenizer tokenizer = new StringTokenizer(s,Delimiter);
        while (tokenizer.hasMoreTokens())
        {
          i++;
          String temp=tokenizer.nextToken();
        }
        tokenizer= new StringTokenizer(s,Delimiter);
        String[] arr= new String[i];
        int j=0;
        while (tokenizer.hasMoreTokens())
        {
          String str=tokenizer.nextToken();
          if(str.equals("stacks"))
            str="stack";
          if(str.equals("structures"))
            str="structure";
          if(str.equals("applications"))
            str="application";
          arr[j]=str;
          arr[j]=str.toLowerCase();
          //System.out.println(str);
          j++;
        }
        Myset<PageEntry> pen=this.inv.getPagesWhichContainPhrase(arr);
        //System.out.println(pen.size());
        float[] relevanceArr= new float[pen.size()];
        for(int k=0;k<pen.size();k++)
        {
          relevanceArr[k]=this.inv.GetRelevanceOfPhrase(pen.list.readGiven(k),arr);
          System.out.println(relevanceArr[k]+" "+pen.list.readGiven(k).name);
        }
        Myset<SearchResult> searches= new Myset<SearchResult>();
        for(int m=0;m<pen.size();m++)
        {
          searches.addElement(new SearchResult(pen.list.readGiven(m),relevanceArr[m]));
        }
        MySort msort=new MySort();
        ArrayList<SearchResult> sortedList= msort.sortThisList(searches);
        Myset<PageEntry> SortedMyset=new Myset<PageEntry>();
        for(int m=0;m<pen.size();m++)
        {
          SortedMyset.addElement(sortedList.get(m).getPageEntry());
        }
        displayMyset(SortedMyset);



      }catch(myException vbjvb){}
    }



   else if(actionMessage.substring(0,41).equals("queryFindPagesWhichContainAnyOfTheseWords"))
   {
     try
     {
       int i=0;
       String Delimiter =" ";
       String s=actionMessage.substring(42);
       StringTokenizer tokenizer = new StringTokenizer(s,Delimiter);
       while (tokenizer.hasMoreTokens())
       {
         i++;
         String temp=tokenizer.nextToken();
       }
       tokenizer= new StringTokenizer(s,Delimiter);
       String[] arr= new String[i];
       int j=0;
       while (tokenizer.hasMoreTokens())
       {
         String str=tokenizer.nextToken();
         if(str.equals("stacks"))
           str="stack";
         if(str.equals("structures"))
           str="structure";
         if(str.equals("applications"))
           str="application";
         arr[j]=str.toLowerCase();
         j++;
       }
       Myset<PageEntry> pen= this.inv.getPagesWhichContainWord(arr[0]);
       for (int k=1;k<i;k++)
       {
         pen=pen.union(this.inv.getPagesWhichContainWord(arr[k]));
       }
       float[] relevanceArr= new float[pen.size()];
       for(j=0;j<pen.size();j++)
       {
         relevanceArr[j]=0.0f;
         for(int k=0;k<i;k++)
         {
           WordEntry w= this.inv.table.getWordEntryCorrespondingToString(arr[k]);
           relevanceArr[j]+=w.getRelevance(pen.list.readGiven(j),this.inv);
         }
         //System.out.println(pen.list.readGiven(j).name+" "+relevanceArr[j]);
       }
       Myset<SearchResult> searches= new Myset<SearchResult>();
       for(int m=0;m<pen.size();m++)
       {
         searches.addElement(new SearchResult(pen.list.readGiven(m),relevanceArr[m]));
       }
       MySort msort=new MySort();
       ArrayList<SearchResult> sortedList= msort.sortThisList(searches);
       Myset<PageEntry> SortedMyset=new Myset<PageEntry>();
       for(int m=0;m<pen.size();m++)
       {
         SortedMyset.addElement(sortedList.get(m).getPageEntry());
       }
       displayMyset(SortedMyset);




     }catch(myException dj){}
   }
 }



  private void displayMyset(Myset<PageEntry> mset)
  {
    try{
      String disp="";
      for(int i=0;i<mset.size();i++)
      {
        //System.out.println(mset.list.readGiven(i).name);
        disp=disp+mset.list.readGiven(i).name+", ";
      }
      if(disp.length()>0)
      {
        disp=disp.substring(0,disp.length()-2);
        System.out.println(disp);
      }
      else
        System.out.println("No page entry satisfy the the given condition");
    }
    catch(myException vhd){}
  }
   public static void main(String args[])
   {
  //   //try{
  //      SearchEngine se= new SearchEngine();
  //      // se.performAction("addPage check2");
  //      //  se.performAction("queryFindPositionsOfWordInAPage delimiter check2");
  //     se.performAction("addPage stack_datastructure_wiki");
  //     se.performAction("addPage stackmagazine");
  //     se.performAction("addPage references");
  //     //se.performAction("queryFindPositionsOfWordInAPage stacks stack_datastructure_wiki");
  //     //se.performAction("queryFindPositionsOfWordInAPage stack stack_datastructure_wiki");
  //     //se.performAction("queryFindPositionsOfWordInAPage 2 references");
  //    se.performAction("addPage stack_cprogramming");
  //    se.performAction("addPage stack_oracle");
  //    se.performAction("addPage stacklighting");
  //   //  se.performAction("queryFindPagesWhichContainWord java");
  // //   /*se.performAction("addPage stack_datastructure_wiki");
  // //   se.performAction("addPage stack_oracle");
  // //   se.performAction("addPage stacklighting");
  // //   se.performAction("addPage stackmagazine");
  //   //se.performAction("addPage stackoverflow");
  // //   //se.performAction("queryFindPagesWhichContainWord uddesh");
  // // //    se.performAction("queryFindPagesWhichContainWord delhi");
  //   // System.out.println(se.inv.table.sets[131].readGiven(0).positions.readGiven(3).p);
  //   // System.out.println(se.inv.table.sets[106].readGiven(0).str);
  //   // String Delimiter =" ";
  //   // String s="queryFindPagesWhichContainAllWords x1 x2 .. xn";
  //   // StringTokenizer tokenizer = new StringTokenizer(s,Delimiter);
  //   // while (tokenizer.hasMoreTokens())
  //   // {
  //   //   String temp=tokenizer.nextToken();
  //   //   System.out.println(temp);
  //   // }
  //   // System.out.println(("queryFindPagesWhichContainAnyOfTheseWords x1 x2 .. xn:").substring(0,41));
  //   // System.out.println(s.substring(35));
  //   se.performAction("queryFindPagesWhichContainAllWords stacks structure last");
  //   se.performAction("queryFindPagesWhichContainAnyOfTheseWords stacks last structure push");
  //   se.performAction("queryFindPagesWhichContainPhrase data structure");
  //   //}catch(myException dc){}
  }

}
