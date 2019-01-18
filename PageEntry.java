import java . util .*;
import java .io .*;
public class PageEntry
{
  protected PageIndex pIndex;
  protected String name;
  protected int wordsTotal;
  static String Delimiter=", ;/.?(){}[]=<>#!-:\'!\"";
  public PageEntry(String name)
  {
    this.name=name;
    wordsTotal=0;
    pIndex=new PageIndex();
    readFile(name);
  }
  public PageIndex getPageIndex()
  {
    return pIndex;
  }
  public void readFile(String name )
  {

    try {
      int i=1;
      int j=0;
        FileInputStream fstream =new FileInputStream ("./webpages/"+name);
        Scanner s = new Scanner ( fstream );
        while (s.hasNextLine ())
        {
          StringTokenizer tokenizer = new StringTokenizer(s.nextLine(),Delimiter);
          while (tokenizer.hasMoreTokens())
          {


            String temp=tokenizer.nextToken();
            temp=temp.toLowerCase();
            if(temp.equals("stacks"))
              temp="stack";
            if(temp.equals("structures"))
              temp="structure";
            if(temp.equals("applications"))
              temp="application";
            if(checkStopWords(temp)==false)
            {
              //System.out.println("fcuk");
              wordsTotal++;

              Position pos1=new Position(this,j);
              Position  pos= new Position(this, i);
              pIndex.addPositionForWords(temp,pos, pos1);
              j++;
              //pIndex.addAVLtreesPositionForWords(temp,pos1);
            }
            i++;
          }
        }
      } catch ( FileNotFoundException e)
      {
          System.out.println (" File not found ");
      }
  }
  boolean checkStopWords(String w)
  {
    if(w.equalsIgnoreCase("a") || w.equalsIgnoreCase("an") || w.equalsIgnoreCase("the") || w.equalsIgnoreCase("they")
    || w.equalsIgnoreCase("these") || w.equalsIgnoreCase("this") || w.equalsIgnoreCase("for") || w.equalsIgnoreCase("is")
    || w.equalsIgnoreCase("are") || w.equalsIgnoreCase("was") || w.equalsIgnoreCase("of") || w.equalsIgnoreCase("or")
    || w.equalsIgnoreCase("and") || w.equalsIgnoreCase("does") || w.equalsIgnoreCase("will") || w.equalsIgnoreCase("whose"))
      return true;
    else
      return false;
  }
  public float getRelevanceOfPage(String str[],boolean doTheseWordsRepresentAPhrase)
  {
    if(doTheseWordsRepresentAPhrase==true)
    {
      int m=this.NoOfPhrasesContained(str);
      int wp=this.wordsTotal;
      int k=str.length;
      return (float)m/(float)(wp-(k-1)*m);
    }
    else
    {
      return 0.0f;
    }

  }
  public boolean ContainsPhrase(String[] str)
  {
    try
    {
      boolean k3=true, k2=true;
      int count=0;
      boolean checkk=true;
      WordEntry w=this.getPageIndex().getWordEntry(str[0]);
      if(w!=null)
      {
        Myset<Position> mp1=w.tree.getPositionsInAscendingOrder();
        for(int m=1;m<str.length;m++)
        {
          if(this.getPageIndex().check(str[m])==true)
            continue;
          else
          {
            checkk=false;
          }
        }
        if(checkk==true)
        {
          for(int i=0;i<mp1.size();i++)
          {

            int wi=mp1.list.readGiven(i).getWordIndex();
            k3=true;
            for(int m=1;m<str.length;m++)
            {
              k3=true;
              for(int j=0;j<this.getPageIndex().avltrees.size();j++)
              {
                AVL itrTree=this.getPageIndex().avltrees.get(j);
                if(itrTree.find(itrTree.root,wi+m))
                {
                  //System.out.println("vbhvjvjhvhvvghjvhn nccfgghcghv");
                  if(itrTree.getWord().equalsIgnoreCase(str[m]))
                  {
                    break;
                  }
                  else
                  {
                    k3=false;
                  }
                }
                if(k3==false)
                break;

              }
              if(k3==false)
                break;
            }
            if(k3==false)
              continue;
            else
              count++;

          }
        //  System.out.println(count+" here is total");
          if(count>0)
            return true;
          else
          return false;
        }
        else
          return false;
      }
      else
        return false;
    }catch(myException hj){ return false;}

  }
  public int NoOfPhrasesContained(String[] str)
  {
    try
    {
      boolean k3=true, k2=true;
      int count=0;
      boolean checkk=true;
      if(this.ContainsPhrase(str)==true)
      {
        WordEntry w=this.getPageIndex().getWordEntry(str[0]);
        Myset<Position> mp1=w.tree.getPositionsInAscendingOrder();
        for(int m=1;m<str.length;m++)
        {
          if(this.getPageIndex().check(str[m])==true)
            continue;
          else
          {
            checkk=false;
          }
        }
        if(checkk==true)
        {
          for(int i=0;i<mp1.size();i++)
          {

            int wi=mp1.list.readGiven(i).getWordIndex();
            k3=true;
            for(int m=1;m<str.length;m++)
            {
              k3=true;
              for(int j=0;j<this.getPageIndex().avltrees.size();j++)
              {
                AVL itrTree=this.getPageIndex().avltrees.get(j);
                if(itrTree.find(itrTree.root,wi+m))
                {
                  //System.out.println("vbhvjvjhvhvvghjvhn nccfgghcghv");
                  if(itrTree.getWord().equalsIgnoreCase(str[m]))
                  {
                    break;
                  }
                  else
                  {
                    k3=false;
                  }
                }
                if(k3==false)
                break;

              }
              if(k3==false)
                break;
            }
            if(k3==false)
              continue;
            else
              count++;

          }
        //  System.out.println(count+" here is total");
          return count;
        }
        else
          return 0;
      }
      else
        return 0;
    }catch(myException hj){ return 0;}
  }
  public static void main(String[] args)
  {
    PageEntry pg= new PageEntry("check1");
    String[] str=new String[]{"coloi"};

    System.out.println(pg.ContainsPhrase(str));
    System.out.println(pg.NoOfPhrasesContained(str));
    System.out.println((float)5/(float)(4+7));
  }
}
