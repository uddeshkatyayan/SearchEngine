public class Position
{
  protected PageEntry p;
  protected int wordIndex;
  public Position(PageEntry pe,int Index)
  {
    p= pe;
    this.wordIndex=Index;
  }
  public PageEntry getPageEntry()
  {
    return p;
  }
  public int getWordIndex()
  {
    return wordIndex;
  }
}
