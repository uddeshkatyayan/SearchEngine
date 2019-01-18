public class InvertedPageIndex
{
  protected MyHashTable table;
  protected Myset<PageEntry> listOfPageEntries;
  public InvertedPageIndex()
  {
    table= new MyHashTable();
    listOfPageEntries=new Myset<PageEntry>();
  }
  public int getTotalNo_ofPages()
  {
    return this.listOfPageEntries.size();
  }
  public void addPage(PageEntry p)
  {
    listOfPageEntries.addElement(p);
    try
    {
      PageIndex pI=p.getPageIndex();
      for(int i=0;i<pI.listWordEntry.getSize();i++)
      {
        table.addPositionsForWord(pI.listWordEntry.readGiven(i));
      }
    }catch(myException vjv){}
  }
  public Myset<PageEntry> getPagesWhichContainWord(String sr)
  {
    sr=sr.toLowerCase();
    Myset<PageEntry> setOfPageEntry =new Myset<PageEntry>();
    try{
      int hashValue=table.getHashIndex(sr);
      if(table.sets[hashValue]!=null)
      {
        int length=table.sets[hashValue].getSize();
        for(int i=0;i<length;i++)
        {
          if(table.sets[hashValue].readGiven(i).str.equals(sr))
          {

            WordEntry w=table.sets[hashValue].readGiven(i);
            for(int j=0;j<w.positions.getSize();j++)
            {
                setOfPageEntry.addElement(w.positions.readGiven(j).p);
            }

          }
        }
      }
      return setOfPageEntry;
    }catch(myException bi){
     return setOfPageEntry;
   }

  }
  public int getNo_ofPagesWhichContainWord(String sr)
  {
    Myset<PageEntry> pen= this.getPagesWhichContainWord(sr);
    return pen.size();
  }
  public Myset<PageEntry> pagesContained()
  {
    return listOfPageEntries;
  }
  public Myset<PageEntry> getPagesWhichContainPhrase(String[] sr)
  {
    Myset<PageEntry> setOfPageEntry =new Myset<PageEntry>();
    try{

      for(int i=0;i<listOfPageEntries.size();i++)
      {
        if(listOfPageEntries.list.readGiven(i).ContainsPhrase(sr)==true)
          setOfPageEntry.addElement(listOfPageEntries.list.readGiven(i));
      }
      return setOfPageEntry;
    }catch(myException vj){return setOfPageEntry;}
  }
  public float GetRelevanceOfPhrase(PageEntry p,String[] str)
  {
    float tf= p.getRelevanceOfPage(str,true);
    int N=listOfPageEntries.size();
    Myset<PageEntry> cc= this.getPagesWhichContainPhrase(str);
    int nw= cc.size();
    float idf=(float)(Math.log((float)N/(float)nw));
    System.out.println("term frequency="+tf+" idf is="+idf);
    return tf*idf;
  }
}
