import java.lang.*;
public class AVL
{
  protected AvlNode root;
  protected String wordName;
  protected Myset<Position> setp;
  public AVL()
  {
    root=null;
    this.wordName="";
    setp=new Myset<Position>();
  }
  public AVL(String s)
  {
    root=null;
    this.wordName=s;
    setp=new Myset<Position>();
  }
  public String getWord()
  {
    return this.wordName;
  }
  public void addElement(Position p)
  {
    //System.out.println(this.wordName+" is being added with position "+p.getWordIndex());
    this.BinaryAddition(this.root,p);
  }
  public void BinaryAddition(AvlNode current, Position p)
  {
    AvlNode node= new AvlNode(p);
    int value= p.getWordIndex();
    if(value<=current.data.getWordIndex())
    {
      current.height++;
      if(current.left==null)
      {
        node.parent=current;
        current.left=node;
        this.checkAVLproperty(current);
      }
      else
        this.BinaryAddition(current.left, p);
    }
    else
    {
      current.height++;
      if(current.right==null)
      {
        node.parent=current;
        current.right=node;
        this.checkAVLproperty(current);
      }
      else
        this.BinaryAddition(current.right,p);
    }
  }
  public void checkAVLproperty(AvlNode a)
  {
    if(a!=null)
    {
      if(a.left!=null && a.right!=null)
      {
        int dif=Math.abs(a.left.height-a.right.height);
        if(dif>=2)
          this.transform(a);
        else
          this.checkAVLproperty(a.parent);
      }
      // adding is to be done here
      else if(a.left==null)
      {

        if(a.height>1)
        {
          AvlNode def=a.parent;
          this.transform(a);
          //this.transform(def);
        }
        else
          this.checkAVLproperty(a.parent);
      }
      else
      {
        if(a.height>1)
          this.transform(a);
        else
          this.checkAVLproperty(a.parent);
      }
    }
  }
  public void transform(AvlNode a)
  {
    AvlNode b= null, c=null, c1=null, c2= null, c3=null, c4=null, x=null, y=null, z=null;
    if(a.left!=null && a.right!=null)
    {
      if(a.left.height>a.right.height)
      {
        b=a.left;
        c4=a.right;
        if(b.left!=null && b.right!= null)
        {
            if(b.left.height>b.right.height)
            {
              //done
              c=b.left;
              c1=c.left;
              c2=c.right;
              c3=b.right;
              b.parent=a.parent;
              if(a==this.root)
                this.root=b;
              else
                a.parent.left=b;
              b.left=c;
              b.right=a;
              a.parent=b;
              c.parent=b;
              x=b;
              y=c;
              z=a;
              this.arrange(x,y,z,c1,c2,c3,c4);
            }
            else
            {
              c=b.right;
                  c1=b.left;
                  c2=c.left;
                  c3=c.right;
                  c.parent=a.parent;
                  if(a==this.root)
                    this.root=c;
                  else
                    a.parent.left=c;
                  c.left=b;
                  c.right=a;
                  b.parent=c;
                  a.parent=c;
                  x=c;
                  y=b;
                  z=a;
                  this.arrange(x,y,z,c1,c2,c3,c4);
            }
        }
        else if(b.left==null)
        {
          c=b.right;
                c1=b.left;
                c2=c.left;
                c3=c.right;
                c.parent=a.parent;
                if(a==this.root)
                  this.root=c;
                else
                  a.parent.left=c;
                c.left=b;
                c.right=a;
                b.parent=c;
                a.parent=c;
                x=c;
                y=b;
                z=a;
                this.arrange(x,y,z,c1,c2,c3,c4);
        }
        else if(b.right == null)
        {
          c=b.left;
              c1=c.left;
              c2=c.right;
              c3=b.right;
              b.parent=a.parent;
              if(a==this.root)
                this.root=b;
              else
                a.parent.left=b;
              b.left=c;
              b.right=a;
              a.parent=b;
              c.parent=b;
              x=b;
              y=c;
              z=a;
              this.arrange(x,y,z,c1,c2,c3,c4);
        }
      }
      else
      {
        b=a.right;
        c1=a.left;
        if(b.left!=null && b.right!=null)
        {
          if(b.left.height>b.right.height)
          {
            c=b.left;
                  c2=c.left;
                  c3=c.right;
                  c4=b.right;
                  c.parent=a.parent;
                  if(a==this.root)
                    this.root=c;
                  else
                    a.parent.right=c;
                  c.left=a;
                  c.right=b;
                  a.parent=c;
                  b.parent=c;
                  x=c;
                  y=a;
                  z=b;
                  this.arrange(x,y,z,c1,c2,c3,c4);
          }
          else
          {
            c=b.right;
                c2=b.left;
                c3=c.left;
                c4=c.right;
                b.parent=a.parent;
                if(a==this.root)
                  this.root=b;
                  else
                    a.parent.right=b;
                b.left=a;
                b.right=c;
                a.parent=b;
                c.parent=b;
                x=b;
                y=a;
                z=c;
                this.arrange(x,y,z,c1,c2,c3,c4);
          }
        }
        else if(b.left==null)
        {
          c=b.right;
              c2=b.left;
              c3=c.left;
              c4=c.right;
              b.parent=a.parent;
              if(a==this.root)
                this.root=b;
                else
                  a.parent.right=b;
              b.left=a;
              b.right=c;
              a.parent=b;
              c.parent=b;
              x=b;
              y=a;
              z=c;
              this.arrange(x,y,z,c1,c2,c3,c4);
        }
        else if(b.right==null)
        {
          c=b.left;
              c2=c.left;
              c3=c.right;
              c4=b.right;
              c.parent=a.parent;
              if(a==this.root)
                this.root=c;
                else
                  a.parent.right=c;
              c.left=a;
              c.right=b;
              a.parent=c;
              b.parent=c;
              x=c;
              y=a;
              z=b;
              this.arrange(x,y,z,c1,c2,c3,c4);
        }
      }
    }
    else if(a.left==null && a.right!= null)
    {
      b=a.right;
      c1=a.left;
      if(b.left!=null && b.right!=null)
      {
        if(b.left.height>b.right.height)
        {
          c=b.left;
                c2=c.left;
                c3=c.right;
                c4=b.right;
                c.parent=a.parent;
                if(a==this.root)
                  this.root=c;
                  else
                    a.parent.right=c;
                c.left=a;
                c.right=b;
                a.parent=c;
                b.parent=c;
                x=c;
                y=a;
                z=b;
                this.arrange(x,y,z,c1,c2,c3,c4);
        }
        else
        {
          c=b.right;
                c2=b.left;
                c3=c.left;
                c4=c.right;
                b.parent=a.parent;
                if(a==this.root)
                  this.root=b;
                  else
                    a.parent.right=b;
                b.left=a;
                b.right=c;
                a.parent=b;
                c.parent=b;
                x=b;
                y=a;
                z=c;
                this.arrange(x,y,z,c1,c2,c3,c4);
        }
      }
      else if(b.left==null)
      {
        c=b.right;
            c2=b.left;
            c3=c.left;
            c4=c.right;
            b.parent=a.parent;
            if(a==this.root)
              this.root=b;
              else
                a.parent.right=b;
            b.left=a;
            b.right=c;
            a.parent=b;
            c.parent=b;
            x=b;
            y=a;
            z=c;
            this.arrange(x,y,z,c1,c2,c3,c4);
      }
      else if(b.right==null)
      {
        a.right.left.parent=a.parent;
          if(a==this.root)
              this.root=a.right.left;
              else
                a.parent.right=a.right.left;
          a.right.left.left=a;
          a.right.left.right=a.right;
          a.parent=a.right.left;
          b.parent=a.right.left;
          // System.out.println(a.right.left.data);
          // System.out.println(a.data);
          // System.out.println(a.right.data);
          //System.out.println("root "+a.data);
          this.arrange(a.right.left ,a ,a.right ,a.left ,a.right.left.left,a.right.left.right,a.right.right);

      }
    }
    else if(a.right==null)
    {
      b=a.left;
      c4=a.right;
      if(b.left!=null && b.right!= null)
      {
        if(b.left.height>b.right.height)
        {
          c=b.left;
            c1=c.left;
            c2=c.right;
            c3=b.right;
            b.parent=a.parent;
            if(a==this.root)
              this.root=b;
              else
                a.parent.left=b;
            b.left=c;
            b.right=a;
            a.parent=b;
            c.parent=b;
            x=b;
            y=c;
            z=a;
            this.arrange(x,y,z,c1,c2,c3,c4);
        }
        else
        {
          c=b.right;
              c1=b.left;
              c2=c.left;
              c3=c.right;
              c.parent=a.parent;
              if(a==this.root)
                this.root=c;
                else
                  a.parent.left=c;
              c.left=b;
              c.right=a;
              b.parent=c;
              a.parent=c;
              x=c;
              y=b;
              z=a;
              this.arrange(x,y,z,c1,c2,c3,c4);
        }
      }
      else if(b.left==null)
      {
        c=b.right;
            c1=b.left;
            c2=c.left;
            c3=c.right;
            c.parent=a.parent;
            if(a==this.root)
              this.root=c;
              else
                a.parent.left=c;
            c.left=b;
            c.right=a;
            b.parent=c;
            a.parent=c;
            x=c;
            y=b;
            z=a;
            this.arrange(x,y,z,c1,c2,c3,c4);
      }
      else if(b.right==null)
      {
        c=b.left;
            c1=c.left;
            c2=c.right;
            c3=b.right;
            b.parent=a.parent;
            if(a==this.root)
              this.root=b;
              else
                a.parent.left=b;
            b.left=c;
            b.right=a;
            a.parent=b;
            c.parent=b;
            x=b;
            y=c;
            z=a;
            this.arrange(x,y,z,c1,c2,c3,c4);
      }
    }

  }
  public void arrange(AvlNode x,AvlNode y, AvlNode z,AvlNode c1, AvlNode c2, AvlNode c3, AvlNode c4)
  {

    int hy1=0, hy2=0;
    int hz1=0 , hz2=0;
    y.left=c1;
    y.right=c2;
    z.left=c3;
    z.right=c4;
    if(c1!=null)
    {
      c1.parent=y;
      hy1=c1.height+1;
    }
    else
      hy1=0;

    if(c2!=null)
    {
      c2.parent=y;
      hy2=c2.height+1;
    }
    else
      hy2=0;

    if(c3!=null)
    {
      c3.parent=z;
      hz1=c3.height+1;
    }
    else
      hz1=0;

    if(c4!=null)
    {
      c4.parent=z;
      hz2=c4.height+1;
    }
    else
      hz2=0;
      y.height=(hy1>=hy2)?hy1:hy2;
      z.height=(hz1>=hz2)?hz1:hz2;
      x.height=(y.height>=z.height)?y.height+1:z.height+1;
  }
  public boolean find(AvlNode avl ,int index)
  {
    while(avl!=null)
    {
      if(avl.getPosition().getWordIndex()<index)
        avl=avl.getRight();
      else if(avl.getPosition().getWordIndex()>index)
        avl=avl.getLeft();
      else
        return true;
    }
    return false;
  }
  public void inOrder(AvlNode node)
  {
      //Myset<Position> setp=new Myset<Position>();
      if (node == null)
       { //System.out.println("yep");
         return; }
      this.inOrder(node.getLeft());
      this.setp.addElement(node.getPosition());
      //System.out.printf("%s ", node.data.getWordIndex());
      this.inOrder(node.getRight());
  }
  public Myset<Position> getPositionsInAscendingOrder()
  {
    inOrder(this.root);
    //System.out.println("i m here"+this.wordName+ " "+this.setp.size() );
    return this.setp;
  }






  public static void main(String[] args)
  {
  //  System.out.println("ggvghvvvhgvhvhjvhjvhjvjv");
  }
}
