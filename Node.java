public class Node<Object>
{
  protected Object data;
  protected Node<Object> next;
  public Node()
  {
    next=null;

  }
  public Node(Object d, Node<Object> n)
  {
    data=d;
    next=n;
  }
  public void setNext(Node<Object> n)
  {
    next=n;
  }
  public void setData(Object d)
  {
    data=d;
  }
  public Node<Object> getNext()
  {
    return next;
  }
  public Object getData()
  {
    return data;
  }
}
