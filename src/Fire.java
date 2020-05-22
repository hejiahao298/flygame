
public class Fire extends Flyprojact{
     public Fire(int hx,int hy) {               //根据hero的位置释放Fire
        img=APP.getImg("/img/fire3.png");
        x=hx;
        y=hy;
	    H=img.getHeight();
	    W=img.getWidth();
	}
     
     
     /*
      * 子弹的移动
      */
     public  void Move() {
	   y-=10;

	}
}
