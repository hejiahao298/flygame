import java.util.Random;

public class EP extends Flyprojact {
int HP;                          //血量
int SP;                          //速度
int kind;                        //EP种类

public EP() {
	   HP=3;                     //初始血量为3
	Random rand=new Random();
	int index=rand.nextInt(15)+1;         //随机一个数
	kind=index;                          //将这个数设为EP的种类
	String path="/img/ep"+(index<10?"0":"")+index+".png";      //Path为EP的图片（index<0?"0":""）3目运算当index小于10取0；否则取空
	img=APP.getImg(path);                     //获取图片
	x=rand.nextInt(512-W);                    //窗体上方随机出现
	//if(x)
	y=0;
	H=img.getHeight();                        //获取图片的高度
	W=img.getWidth();                         //获取图片的宽度
	SP=17-index;                             //图片越大速度越慢
	HP=index;
} 


/*
 * 控制EP的血量
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
 * EP的移动和速度
 */
public void Move() {                       
	if(kind==5) {                //当为EP05时往左下飞
		x-=5;
		y+=5;
	}else if(kind==6) {          //当为EP06时往右下飞
		x+=5;
    	y+=5;
	}
	 if(SP>12) {
		 SP=10;
		 y+=SP;
	 }else if(kind==15) {
			y+=13;
		}else {
	y+=SP;                        //其他直线向下
	 }
}


/*
 * 判断EP是否被Fire打中
 */
public boolean shootBy(Fire f) {
	boolean hit=x<=f.x+f.W&&
			x>=f.x-W&&
			y<=f.y+f.H&&
			y>=f.y-H;
	return hit;
}


/*
 * 判断EP是否撞击hero
 */
public boolean hitBY(Hero f) {
	boolean hit=x<=f.x+f.W&&
			x>=f.x-W&&
			y<=f.y+f.H&&
			y>=f.y-H; 
	return hit;
}
}
