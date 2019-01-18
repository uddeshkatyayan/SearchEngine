public class Myset<Object>
{
  protected MyLinkedList<Object> list;
  public Myset()
  {
    list= new MyLinkedList<Object>();
  }
  public int size()
  {
    return list.getSize();
  }
  public void addElement(Object o)
  {
    Node<Object> itr= list.head;
    int i=0;
    for(i=0;i<list.getSize();i++)
    {
      if(itr.getData()==o)
        break;
      itr=itr.getNext();
    }
    if(i==list.size)
      list.insertAtEnd(o);
  }
  public Myset<Object> union(Myset<Object> a)
  {
    Myset<Object> un= new Myset<Object>();

    Node<Object> itr=this.list.head;
    if(itr==null)
    {
      un=a;
      return un;
    }
    else
    {
      while(itr.getNext()!=null)
      {
        un.list.insertAtEnd(itr.getData());
        itr=itr.getNext();
      }
      un.list.insertAtEnd(itr.getData());

      itr=a.list.head;
      while(itr.getNext()!=null)
      {
        if(this.list.isMember(itr.getData())==false)
          un.list.insertAtEnd(itr.getData());
        itr=itr.getNext();
      }
      if(this.list.isMember(itr.getData())==false)
        un.list.insertAtEnd(itr.getData());
      return un;
    }
  }
  public Myset<Object> Intersection(Myset<Object> a)
  {
    Myset<Object> un= new Myset<Object>();
    Node<Object> itr1= a.list.head;
    while((itr1.getNext()!=null))
    {
      if(this.list.isMember(itr1.getData())==true)
        un.list.insertAtStart(itr1.getData());
      itr1=itr1.getNext();
    }
    if(this.list.isMember(itr1.getData())==true)
      un.list.insertAtStart(itr1.getData());
    return un;

  }

}
