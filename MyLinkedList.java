public class MyLinkedList<Object>
{
  protected Node<Object> head;
  protected Node<Object> tail;
  public int size;
  public MyLinkedList()
  {
    //System.out.println("i am here");
    head=null;
    tail =null;
    size=0;
  }
  public boolean isEmpty()
  {
    return head==null;
  }
  public int getSize()
  {
    return size;
  }
  public void insertAtStart(Object val)
  {
    Node<Object> nptr=new Node<Object>(val,null);
    size++;
    if(head==null)
    {
      head=nptr;
      tail=head;
    }
    else
    {
      nptr.setNext(head);
      head=nptr;
    }
    //System.out.println("check again"); //debugger
  }
  public void insertAtEnd(Object val)
  {
    Node<Object> node=new Node<Object>(val,null);
    size++;
    if(tail==null)
    {
      tail=node;
      head=tail;
    }
    else
    {
      tail.setNext(node);
      tail=node;
    }
  }
  public void insertAtPos(Object val, int pos)
  {
    Node<Object> node=new Node<Object>(val,null);

    Node<Object> itr= head;
    for( int i=0;i<size;i++)
    {
      if(i==pos-1)
      {
        Node<Object> swap=itr.getNext();
        node.setNext(swap);
        itr.setNext(node);
        break;
      }
      itr=itr.getNext();
    }
    size++;
  }
  public void deleteFront()
  {
    size--;
    Node<Object> temp=head;
    head=head.getNext();
    temp.setNext(null);
  }
  public Object readFirst()
  {
    return head.getData();
  }
  public void deleteGiven(Object o)
  {
    Node<Object> itr= head;
    if(itr.getData()==o)
    {
      Node<Object> temp=head;
      head=head.getNext();
      temp.setNext(null);
      size--;
      return;
    }
    else
      itr=itr.getNext();
    Node<Object> itr_1= head;

    while(itr.getNext()!=null)
    {
      if(itr.getData()==o)
      {
        itr_1.setNext(itr.getNext());
        itr.setNext(null);
        size--;
        return;
      }

      itr=itr.getNext();
      itr_1=itr_1.getNext();
    }
    if(itr.getData()==o)
    {
      itr_1.setNext(null);
      tail=itr_1;
      size--;
    }
  }
  public Object readGiven(int i)throws myException
  {
    Node<Object> itr= head;
    Object a=null;
    int k=0;
    if(i>=size)
      throw new myException();
    else
    {
      for(k=0;k<size;k++)
      {
        if(k==i)
        {
           return itr.getData();
        }
        itr=itr.getNext();
      }
      return a;
    }
  }
  public void display()
  {
        Node<Object> itr= head;
        while(itr.getNext()!=null)
        {
          System.out.println(itr.getData());
          itr=itr.getNext();
        }
        System.out.println(itr.getData());
    }
  public boolean isMember(Object o)
  {
    Node<Object> itr= head;
    while(itr.getNext()!=null)
    {
      if(itr.getData()==o)
        return true;
      itr=itr.getNext();
    }
    if(itr.getData()==o)
      return true;
    return false;
  }

}
