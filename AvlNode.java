public class AvlNode
{
  protected Position data;
  protected AvlNode parent;
  protected AvlNode right;
  protected AvlNode left;
  protected int height;
  public AvlNode(Position p)
  {
    this.data=p;
    this.left=null;
    this.right=null;
    this.height=0;
  }
  public AvlNode(Position p, AvlNode left, AvlNode right)
  {
    this.data=p;
    this.left=left;
    this.right=right;
  }
  public AvlNode getLeft()
  {
    return this.left;
  }
  public AvlNode getRight()
  {
    return this.right;
  }
  public Position getPosition()
  {
    return this.data;
  }
}
