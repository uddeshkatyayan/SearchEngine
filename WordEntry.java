public class WordEntry
{
  protected String str;
  protected MyLinkedList<Position> positions;
  protected AVL tree;
  public WordEntry(String word)
  {
    str= word;
    positions=new MyLinkedList<Position>();
    tree=new AVL(this.str);
  }
  public void addPosition(Position position)
  {
    positions.insertAtEnd(position);
  //  System.out.println(str+" is being added with position "+ position.getWordIndex());
  }
  public void addAvlPosition(Position position)
  {
    if(tree.root==null)
    {
      AvlNode an=new AvlNode(position);
      tree.root=an;
      //System.out.println(str+" is being added with position "+ position.getWordIndex());
    }
    else
    {
      tree.addElement(position);
        //System.out.println(str+" is being added with position "+ position.getWordIndex());
    }
  }
  public void addPositions(MyLinkedList<Position> pos)
  {
    try
    {
        if(this.positions.head!=null)
        {
          for(int i=0;i<pos.getSize();i++)
          {
            this.positions.insertAtEnd(pos.readGiven(i));
          }
        }
    }catch(myException bjb){}
  }
  public MyLinkedList<Position> getAllPositionsForThisWord()
  {
    return positions;
  }
  public float getTermFrequency(PageEntry page)
  {
    try{
      float tf=0.0f;
      float total_no_of_words=0.0f;
      for(int i=0;i<this.positions.getSize();i++)
      {
        if(this.positions.readGiven(i).p.name.equalsIgnoreCase(page.name)==true)
        {
          total_no_of_words++;
        }
      }
        return total_no_of_words/page.wordsTotal;
    }catch(myException gigi)
    {
      return 0.0f;
    }
  }
  public float InverseDocumentFrequency(InvertedPageIndex inv)
  {
    int N=inv.getTotalNo_ofPages();
    int nw=inv.getNo_ofPagesWhichContainWord(this.str);
    //System.out.println(N+"N");
    //System.out.println(nw+"nw");
    return (float)(Math.log((float)N/nw));
  }
  public float getRelevance(PageEntry p,InvertedPageIndex inv)
  {
    float tf=this.getTermFrequency(p);
    float idf=this.InverseDocumentFrequency(inv);
    return tf*idf;
  }
}
