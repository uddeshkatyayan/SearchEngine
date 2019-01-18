import java.util.*;
public class MyHashTable
{
  protected Storage[] sets;
  public MyHashTable()
  {
    sets=new Storage[491];
  }
  public int getHashIndex(String str)
  {
    str=str.toLowerCase();
    int hash=7;
      for(int i=0;i<str.length();i++)
      {
        hash=hash+(int)((str.charAt(i))*Math.pow(31,i));
        hash= Math.abs(hash);
        hash=hash%491;
      }
    return hash;
  }
  public void addPositionsForWord(WordEntry w)
  {

    int hashValue=this.getHashIndex(w.str);
    if(sets[hashValue]==null)
    {
      Storage stg= new Storage();
      sets[hashValue]=stg;
      sets[hashValue].insertAtStart(w);
    }
    else
    {
      if(sets[hashValue].isMember(w))
      {
        for(int i=0;i<sets[hashValue].getSize();i++)
        {
          if(sets[hashValue].readGiven(i).str.equalsIgnoreCase(w.str)==true)
          {
            sets[hashValue].readGiven(i).addPositions(w.positions);
            break;
          }
        }
      }
      else
      {
        sets[hashValue].insertAtEnd(w);
      }
    }

  }
  public WordEntry getWordEntryCorrespondingToString(String a)
  {
      int hashValue=this.getHashIndex(a);
      WordEntry w=new WordEntry(a);
      if(sets[hashValue]==null)
      {
        return null;
      }
      else
      {
        if(sets[hashValue].isMember(w))
        {
          for(int i=0;i<sets[hashValue].getSize();i++)
          {
            if(sets[hashValue].readGiven(i).str.equalsIgnoreCase(a)==true)
            {
              return sets[hashValue].readGiven(i);
            }
          }
          return null;
        }
        else
          return null;
      }
  }

  public static void main(String args[])
  {
    MyHashTable ht= new MyHashTable();
    WordEntry we= new WordEntry("uddesh");
    ht.addPositionsForWord(we);
    ht.addPositionsForWord(we);
    System.out.println(ht.sets[ht.getHashIndex("uddesh")].getSize());
    System.out.println(ht.getHashIndex("C"));
  }
}
