import jav.util.*;
public class transposeClassic
{
  Scanner sc =new Scanner(Sustem.in);
  int n= Integer.parseInt(sc.nextLine());
  double matrix[][]=new double[n][n];
  for(int i=0;i<n;i++)
  {
    for(int j=0;j<n;j++)
    {
      matrix[i][j]Double.parseDouble(sc.nextLine());
    }
  }
  for(int i=0;i<n;i++)
  {
    for(int j=0;j<i;j++)
    {
      double temp=matrix[i][j];
      matrix[i][j]=matrix[j][i];
      matrix[j][i]=temp;
    }
  }
}
