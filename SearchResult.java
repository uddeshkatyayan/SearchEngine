public class SearchResult implements Comparable<SearchResult>
{
  protected PageEntry p;
  protected float r;
  public SearchResult(PageEntry p, float r)
  {
    this.p=p;
    this.r=r;
  }
  public PageEntry getPageEntry()
  {
    return this.p;
  }
  public float getRelevance()
  {
    return this.r;
  }
  public int compareTo(SearchResult otherObj)
  {
    //return Float.compare(this.r,otherObj.r);
    if(this.r>otherObj.r)
      return 1;
    else if(this.r<otherObj.r)
      return -1;
    else
      return 0;
  }
}
