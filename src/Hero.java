

public class Hero extends Flyprojact{
int HP;
public Hero() {
	HP=3;
	img=APP.getImg("/img/own0.png");
	x=200;
	y=500;
	H=img.getHeight();
	W=img.getWidth();
			
}
public void moveToMouse(int mx,int my) {           //mx�ͣ�my�ֱ�Ϊ����x��y
	y=my-H/2;                                      //������Ƶ��ɻ�����
	x=mx-W/2;
}
}
