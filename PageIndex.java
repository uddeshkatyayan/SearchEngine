import java.util.*;
public class PageIndex
{
  protected MyLinkedList<WordEntry> listWordEntry;
  protected ArrayList<AVL> avltrees;
  public PageIndex()
  {
    listWordEntry=new MyLinkedList<WordEntry>();
    avltrees=new ArrayList<AVL>();
  }
  public void addPositionForWords(String a, Position p, Position q)
  {
    if(listWordEntry.isEmpty()==true)
    {
      WordEntry entry= new WordEntry(a);
      entry.addPosition(p);
      entry.addAvlPosition(q);
      listWordEntry.insertAtEnd(entry);
      avltrees.add(entry.tree);
    }
    else
    {
      if(this.check(a)==true)
      {
        this.getWordEntry(a).addPosition(p);
        this.getWordEntry(a).addAvlPosition(q);
      }
      else
      {
        WordEntry entry= new WordEntry(a);
        entry.addPosition(p);
        entry.addAvlPosition(q);
        listWordEntry.insertAtEnd(entry);
        avltrees.add(entry.tree);

      }
    }
  }
  public MyLinkedList<WordEntry> getWordEntries()
  {
    return listWordEntry;
  }

  // public void addAVLtreesPositionForWords(String a,Position p)
  // {
  //   boolean k=false;
  //   for(int i=0;i<avltrees.size();i++)
  //   {
  //     if(avltrees.get(i).getWord().equalsIgnoreCase(a))
  //     {
  //       k=true;
  //       avltrees.get(i).getWord().addAvlPosition(p);
  //     }
  //   }
  //   if(k==false)
  //   {
  //     WordEntry n= new WordEntry(a);
  //     n.tree=new AVL(p,a);
  //     avltrees.add(n.tree);
  //   }
  // }




  public boolean check(String a)
  {
    try{
    for(int i=0;i<listWordEntry.getSize();i++)
    {
      if(listWordEntry.readGiven(i).str.equalsIgnoreCase(a))
        return true;
    }
    return false;
    }catch(myException me)
    {
      return false;
    }
  }
  public WordEntry getWordEntry(String a)
  {
    try{
    if(this.check(a)==false)
      return null;
    else
      for(int i=0;i<listWordEntry.getSize();i++)
      {
        if(listWordEntry.readGiven(i).str.equalsIgnoreCase(a))
          return listWordEntry.readGiven(i) ;
      }
      return null;
    }catch(myException bjd){
      return null;
    }
  }
}
