
public class Fire extends Flyprojact{
     public Fire(int hx,int hy) {               //����hero��λ���ͷ�Fire
        img=APP.getImg("/img/fire3.png");
        x=hx;
        y=hy;
	    H=img.getHeight();
	    W=img.getWidth();
	}
     
     
     /*
      * �ӵ����ƶ�
      */
     public  void Move() {
	   y-=10;

	}
}
