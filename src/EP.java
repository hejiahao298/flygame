import java.util.Random;

public class EP extends Flyprojact {
int HP;                          //Ѫ��
int SP;                          //�ٶ�
int kind;                        //EP����

public EP() {
	   HP=3;                     //��ʼѪ��Ϊ3
	Random rand=new Random();
	int index=rand.nextInt(15)+1;         //���һ����
	kind=index;                          //���������ΪEP������
	String path="/img/ep"+(index<10?"0":"")+index+".png";      //PathΪEP��ͼƬ��index<0?"0":""��3Ŀ���㵱indexС��10ȡ0������ȡ��
	img=APP.getImg(path);                     //��ȡͼƬ
	x=rand.nextInt(512-W);                    //�����Ϸ��������
	//if(x)
	y=0;
	H=img.getHeight();                        //��ȡͼƬ�ĸ߶�
	W=img.getWidth();                         //��ȡͼƬ�Ŀ��
	SP=17-index;                             //ͼƬԽ���ٶ�Խ��
	HP=index;
} 


/*
 * ����EP��Ѫ��
 */
public void ephp(){
	if(HP>10&&HP<15) {
		HP=4;
	}else if(HP<=10) {
		HP=2;
	}else if(HP>15) {
		HP=8;
	}
}


/*
 * EP���ƶ����ٶ�
 */
public void Move() {                       
	if(kind==5) {                //��ΪEP05ʱ�����·�
		x-=5;
		y+=5;
	}else if(kind==6) {          //��ΪEP06ʱ�����·�
		x+=5;
    	y+=5;
	}
	 if(SP>12) {
		 SP=10;
		 y+=SP;
	 }else if(kind==15) {
			y+=13;
		}else {
	y+=SP;                        //����ֱ������
	 }
}


/*
 * �ж�EP�Ƿ�Fire����
 */
public boolean shootBy(Fire f) {
	boolean hit=x<=f.x+f.W&&
			x>=f.x-W&&
			y<=f.y+f.H&&
			y>=f.y-H;
	return hit;
}


/*
 * �ж�EP�Ƿ�ײ��hero
 */
public boolean hitBY(Hero f) {
	boolean hit=x<=f.x+f.W&&
			x>=f.x-W&&
			y<=f.y+f.H&&
			y>=f.y-H; 
	return hit;
}
}
