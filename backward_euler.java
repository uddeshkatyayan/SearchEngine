import java.util.Scanner;
import java.util.*;
public class eulerback{
	public static void main(String args[]){
		Scanner s=new Scanner(System.in);
		ArrayList<Float> x=new ArrayList<Float>();
		float dt=0.1f;
		x.add(1f);
		float u=0f;
		float v=0f;
		int i=0;
		while(u<5.0f){
			u=u+dt;
			x.add((x.get(i)*(1f+0.5f*dt)+v*dt)/(1f+0.5f*dt+10f*dt*dt));
			v=(v-10f*x.get(i)*dt)/(1f+0.5f*dt+10f*dt*dt);
			i++;
		}
		for(int j=0;j<i;j++){
			System.out.println(x.get(j)+" ");
		}
	}
}
