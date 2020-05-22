
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import javax.swing.JPanel;


public class GPanel extends JPanel {
	
	/*
	 * 变量区
	 */
	//1.定义背景图
	BufferedImage bg;
	BufferedImage bg1;
	BufferedImage own ;
	BufferedImage but ;
	BufferedImage startBG ;
	

	Hero hero=new Hero();

	int Score;
	//EP ep=new EP();
	List<EP>eps=new ArrayList<EP>();              //定义EP的集合
	List<Fire>fs=new ArrayList<Fire>();           //定义子弹的集合
    boolean gameover=false;   
    boolean Start=false;   //控制游戏的开始
    boolean db=false;    //控制爆炸的开关
    boolean stop=false;
	int power=1;                                  //hero的火力值
	int dx;                                 //记录EP的x
	int dy;                                 //记录EP的y;
    
	 
	/*
	 * 开始游戏
	 * 线程控制物体的移动
	 */
	public void action() {
		new Thread() {
			
			public void run() {
				
			
				while(true) {
					if(!gameover&&!stop) {
					epEnter();             // EP的入场
					epMove();              //EP的移动
					shoot();               //发射子弹
					FireMove();            //子弹的移动
					hit();                 //击打EP
					
					peng();               //hero和EP碰撞
					
					repaint();            //刷新
					}
					try {
						Thread.sleep(10);            //控制线程
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					
				}
				}
			}.start();

		}

	
	  /*
	   * Hero和EP的碰撞
	   */
		private void peng() {
				for(int i=0;i<eps.size();i++) {     //遍历所有EP
					EP e=eps.get(i);                //获取到当前EP
					if(e.hitBY(hero)) {             //hitBy是否被碰撞到
						eps.remove(e);              //别撞到删除该EP
						hero.HP--;                  //Hero的HP减一
						if(hero.HP<=0) {            //当Hero的HP到0时则游戏结束
							gameover=true;
						}
						

					}
				}
				
			}


		/*
		 * Fire打击到EP的方法
		 */
		public  void hit() {
			for(int i=0;i<fs.size();i++) {               //遍历所有Fire
				Fire f=fs.get(i);                        //获取到当前Fire
				bang(f);                                 //Fire打击
			}
		}

		
		/*
		 * Fire打击到EP的具体情况
		 */
		
		public void bang(Fire f) {
			for(int i=0;i<eps.size();i++) {                 //遍历所有EP
				EP e=eps.get(i);                            //获取到当前EP
				if(e.shootBy(f)) {                          //shootBY(f)判断是否打击到
					e.HP--;                               //到打到EP，EP的HP减一
					fs.remove(f);                          //删除该打到EP的子弹
					if(e.kind==15) {
						e.HP=e.HP;
					}
					if(e.HP<=0) {                             //当EP.HP到0时
						dx=e.x;
						dy=e.y; 
						db=true;                            //爆炸开关改为true
						
						
							
						if(e.kind==12) {                    //当打击到的为EP12道具机的时候
							power++;                        //Fire的活力加一        
							if(power>3) {                   //当power大于3的时候（我们设定power只能为3）
								hero.HP++;                  //hero的HP加一
								power=3;               
							}
						}					
					eps.remove(e);                         //删除该EP
					Score=Score+2;                         //分数增加
						}
					}

				}
			}
			
		

	    
		/*
		 * 控制子弹的移动
		 */
			private void FireMove() {
		
				for(int i=0;i<fs.size();i++) {                //遍历所有Fire
					Fire f=fs.get(i);                       //获取到当前Fire
				 
					f.Move();                                //子弹具体移动
				}
				}
			

          int findex=0;                                      //记录shoot被调用的次数
			private void shoot() {
				findex++;
				
				if(findex>=20) {                             //当被调用到20次时就发射子弹（主要为控制子弹发射的速率）
					
					
					/*
					 * 子弹具体数量和发射
					 */
					if(power==2) {
				Fire fire1=new Fire(hero.x+22,hero.y);
				Fire fire2=new Fire(hero.x+72,hero.y);
				fs.add(fire2);
				fs.add(fire1);
				}else if(power==1) {
					Fire fire1=new Fire(hero.x+22,hero.y);
				
				fs.add(fire1);
				}else if(power==3) {
					Fire fire1=new Fire(hero.x+22,hero.y);
					Fire fire2=new Fire(hero.x+72,hero.y);
					fs.add(fire2);
					fs.add(fire1);
				Fire fire3=new Fire(hero.x-22,hero.y);
				fs.add(fire3);
				}
				findex=0;
				}
				
			}


			
            /*
             * EP的移动
             */
			private void epMove() {
			
				{
				for(int i=0;i<eps.size();i++) {
					EP e=eps.get(i);
					e.Move();
					
				}
				}
			}
			
			
			/*
			 * EP的进场
			 */
			int index;                         //EPEnter记录调用的次数
			private void epEnter() {
				
			 if(hero.HP>=4) {
					index+=2;
				}else {	
					index++;
				}
					
				
					
				
				
				             
				if(index>=40) {               //当大于20的时候出动一架EP（主要控制EP出的速率）
				EP e=new EP();                //新建EP
				
				eps.add(e);                   //加入到eps集合中
				e.ephp();
				index=0;
				}
			}
		
		
	
       /*
              * 面板的构造方法
        */
	public GPanel() {
		super();

		
		setBackground(Color.pink);               //set背景颜色
		bg=APP.getImg("/img/bg1.jpg");           //获取背景图片
		bg1=APP.getImg("/img/bg2.jpg");
		own = APP.getImg("img/ownbz.png");
		but=APP.getImg("/img/startButton.png");
		startBG=APP.getImg("/img/startBG.jpg");

		
		
		
		/*
		 * 鼠标执行事件
		 */
		MouseAdapter adapter=new MouseAdapter() {       //鼠标执行事件
			/*
			 * mouseMoved()监听鼠标移动事件
			 * mouseCliked()监听鼠标单击事件
			 * mousePressed()监听鼠标按下去的事件
			 * mouseEntered()监听鼠标移入游戏界面事件
			 * mouseExited()监听鼠标移出界面事件
			 * 
			 */
              public void mouseEntered(MouseEvent e) {
            	  stop=false;
              }
			 public void mouseExited(MouseEvent e) {
				stop=true;
			}
			 
			/* public void mouseEnter(MouseEvent e) {
				 stop=false;
			 }*/
			// mouseCliked()监听鼠标单击事件
			public void mouseClicked(MouseEvent e) {

				    
			     	if(gameover||!Start) {        //当游戏失败

					hero=new Hero();          //新建hero
				    Start=true;
					gameover=false;           
					Score=0;
					eps.clear();              //清除eps
					fs.clear();               //清除fs
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					
					bg=APP.getImg("/img/bg"+index+".jpg");   //重新开始换一张bg
					repaint();
				}
			}
			
			
	   	//	mouseMoved()监听鼠标移动事件
			public void mouseMoved(MouseEvent e) {
				int mx=e.getX();                      //e.getX()获取鼠标x位置
				int my=e.getY();                       //e.getY()获取鼠标y位置
				if(!gameover) {
				hero.moveToMouse(mx, my);	          //将鼠标的x，y传递给hero
			}
			repaint();
			}	
			
		};
		addMouseListener(adapter);                     //监听鼠标事件              
		addMouseMotionListener(adapter);               //监听鼠标移动事件
		 
	}
 
	
	/*
	 *专业画图方法，g为画笔
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {		
		super.paint(g);
		
		/*
		 * 游戏进入时的界面
		 */
       if(!Start) {
    	   g.drawImage(startBG, 0, 0, 512, 768, null);   
    	   g.drawImage(hero.img,230, 400, hero.W, hero.H, null); 
	   g.drawImage(but, 170, 500, but.getWidth(), but.getHeight(), null); 
	   
       }

       else{
		   g.drawImage(bg, 0, 0, 512, 768, null);                              //画背景
	   g.drawImage(hero.img, hero.x, hero.y, hero.W, hero.H, null);                //画hero
	   
	   
	   for(int i=0;i<eps.size();i++) {                                             //画敌机
		   EP ep=eps.get(i);

	   g.drawImage(ep.img, ep.x, ep.y, ep.W, ep.H, null);
	 
	   }
	   
	   
	    
	   for(int i=0;i<fs.size();i++) {                                              //画Fire
		   Fire fire=fs.get(i);
	   g.drawImage(fire.img, fire.x, fire.y, fire.W, fire.H, null);
	   }
	   
	   
	   g.setColor(Color.white);                                                   //画分数
	   g.setFont(new Font("楷体",Font.BOLD,20));
	   g.drawString("分数"+Score,10,30);
	   
	   

	   
	   for(int i=0;i<hero.HP;i++) {                                                //画血量
		   g.drawImage(hero.img, 350+i*35, 5, 30, 30, null);
	   }
	    
	   
	   /*
	    * 画EP的爆炸效果
	    */
	if(db) {
       for(int i=0;i<120;i++) {
	   g.drawImage(own, dx-2, dy-5,1+i,1+i, null);
       }
	   db=false;
	   }
	
	
	/*
	 * 当暂停时所画的东西
	 */
	   if(stop) {
	   g.setColor(Color.red);
	   g.setFont(new Font("楷体",Font.BOLD,100));
	   g.drawString("暂停",160,360);
	   }
	   
	   /*
	    * 游戏结束时所现实的字
	    */
	   if(gameover) {
		   g.drawImage(bg1, 0, 0, 512, 768, null);
		   g.drawImage(hero.img, 210, 500, hero.W, hero.H, null); 
		   g.setColor(Color.red);
		   g.setFont(new Font("楷体",Font.BOLD,30));
		   g.drawString("你死了",200,360);
		   g.setColor(Color.white);
		   g.setFont(new Font("楷体",Font.BOLD,30));
		   g.drawString("点击屏幕重新开始",150,400);
		   
	   }
	   

	}

	}
	}



