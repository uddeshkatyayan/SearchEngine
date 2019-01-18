public class Storage
{
  protected MyLinkedList<WordEntry> mlist;
  public Storage()
  {
    mlist=new MyLinkedList<WordEntry>();
  }
  public boolean isEmpty()
  {
    return mlist.isEmpty();
  }
  public int getSize()
  {
    return mlist.getSize();
  }
  public void insertAtStart(WordEntry val)
  {
    mlist.insertAtStart(val);
  }
  public void insertAtEnd(WordEntry val)
  {
    mlist.insertAtEnd(val);
  }
  public boolean isMember(WordEntry o)
  {
    for(int i=0;i<this.getSize();i++)
    {
      if(o.str.equalsIgnoreCase(this.readGiven(i).str))
        return true;
    }
    return false;
  }
  public WordEntry readGiven(int i)
  {
    try{
      return mlist.readGiven(i);
    }catch(myException dbj){return null;}
  }
}
