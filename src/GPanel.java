
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
	 * ������
	 */
	//1.���屳��ͼ
	BufferedImage bg;
	BufferedImage bg1;
	BufferedImage own ;
	BufferedImage but ;
	BufferedImage startBG ;
	

	Hero hero=new Hero();

	int Score;
	//EP ep=new EP();
	List<EP>eps=new ArrayList<EP>();              //����EP�ļ���
	List<Fire>fs=new ArrayList<Fire>();           //�����ӵ��ļ���
    boolean gameover=false;   
    boolean Start=false;   //������Ϸ�Ŀ�ʼ
    boolean db=false;    //���Ʊ�ը�Ŀ���
    boolean stop=false;
	int power=1;                                  //hero�Ļ���ֵ
	int dx;                                 //��¼EP��x
	int dy;                                 //��¼EP��y;
    
	 
	/*
	 * ��ʼ��Ϸ
	 * �߳̿���������ƶ�
	 */
	public void action() {
		new Thread() {
			
			public void run() {
				
			
				while(true) {
					if(!gameover&&!stop) {
					epEnter();             // EP���볡
					epMove();              //EP���ƶ�
					shoot();               //�����ӵ�
					FireMove();            //�ӵ����ƶ�
					hit();                 //����EP
					
					peng();               //hero��EP��ײ
					
					repaint();            //ˢ��
					}
					try {
						Thread.sleep(10);            //�����߳�
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					
				}
				}
			}.start();

		}

	
	  /*
	   * Hero��EP����ײ
	   */
		private void peng() {
				for(int i=0;i<eps.size();i++) {     //��������EP
					EP e=eps.get(i);                //��ȡ����ǰEP
					if(e.hitBY(hero)) {             //hitBy�Ƿ���ײ��
						eps.remove(e);              //��ײ��ɾ����EP
						hero.HP--;                  //Hero��HP��һ
						if(hero.HP<=0) {            //��Hero��HP��0ʱ����Ϸ����
							gameover=true;
						}
						

					}
				}
				
			}


		/*
		 * Fire�����EP�ķ���
		 */
		public  void hit() {
			for(int i=0;i<fs.size();i++) {               //��������Fire
				Fire f=fs.get(i);                        //��ȡ����ǰFire
				bang(f);                                 //Fire���
			}
		}

		
		/*
		 * Fire�����EP�ľ������
		 */
		
		public void bang(Fire f) {
			for(int i=0;i<eps.size();i++) {                 //��������EP
				EP e=eps.get(i);                            //��ȡ����ǰEP
				if(e.shootBy(f)) {                          //shootBY(f)�ж��Ƿ�����
					e.HP--;                               //����EP��EP��HP��һ
					fs.remove(f);                          //ɾ���ô�EP���ӵ�
					if(e.kind==15) {
						e.HP=e.HP;
					}
					if(e.HP<=0) {                             //��EP.HP��0ʱ
						dx=e.x;
						dy=e.y; 
						db=true;                            //��ը���ظ�Ϊtrue
						
						
							
						if(e.kind==12) {                    //���������ΪEP12���߻���ʱ��
							power++;                        //Fire�Ļ�����һ        
							if(power>3) {                   //��power����3��ʱ�������趨powerֻ��Ϊ3��
								hero.HP++;                  //hero��HP��һ
								power=3;               
							}
						}					
					eps.remove(e);                         //ɾ����EP
					Score=Score+2;                         //��������
						}
					}

				}
			}
			
		

	    
		/*
		 * �����ӵ����ƶ�
		 */
			private void FireMove() {
		
				for(int i=0;i<fs.size();i++) {                //��������Fire
					Fire f=fs.get(i);                       //��ȡ����ǰFire
				 
					f.Move();                                //�ӵ������ƶ�
				}
				}
			

          int findex=0;                                      //��¼shoot�����õĴ���
			private void shoot() {
				findex++;
				
				if(findex>=20) {                             //�������õ�20��ʱ�ͷ����ӵ�����ҪΪ�����ӵ���������ʣ�
					
					
					/*
					 * �ӵ����������ͷ���
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
             * EP���ƶ�
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
			 * EP�Ľ���
			 */
			int index;                         //EPEnter��¼���õĴ���
			private void epEnter() {
				
			 if(hero.HP>=4) {
					index+=2;
				}else {	
					index++;
				}
					
				
					
				
				
				             
				if(index>=40) {               //������20��ʱ�����һ��EP����Ҫ����EP�������ʣ�
				EP e=new EP();                //�½�EP
				
				eps.add(e);                   //���뵽eps������
				e.ephp();
				index=0;
				}
			}
		
		
	
       /*
              * ���Ĺ��췽��
        */
	public GPanel() {
		super();

		
		setBackground(Color.pink);               //set������ɫ
		bg=APP.getImg("/img/bg1.jpg");           //��ȡ����ͼƬ
		bg1=APP.getImg("/img/bg2.jpg");
		own = APP.getImg("img/ownbz.png");
		but=APP.getImg("/img/startButton.png");
		startBG=APP.getImg("/img/startBG.jpg");

		
		
		
		/*
		 * ���ִ���¼�
		 */
		MouseAdapter adapter=new MouseAdapter() {       //���ִ���¼�
			/*
			 * mouseMoved()��������ƶ��¼�
			 * mouseCliked()������굥���¼�
			 * mousePressed()������갴��ȥ���¼�
			 * mouseEntered()�������������Ϸ�����¼�
			 * mouseExited()��������Ƴ������¼�
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
			// mouseCliked()������굥���¼�
			public void mouseClicked(MouseEvent e) {

				    
			     	if(gameover||!Start) {        //����Ϸʧ��

					hero=new Hero();          //�½�hero
				    Start=true;
					gameover=false;           
					Score=0;
					eps.clear();              //���eps
					fs.clear();               //���fs
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					
					bg=APP.getImg("/img/bg"+index+".jpg");   //���¿�ʼ��һ��bg
					repaint();
				}
			}
			
			
	   	//	mouseMoved()��������ƶ��¼�
			public void mouseMoved(MouseEvent e) {
				int mx=e.getX();                      //e.getX()��ȡ���xλ��
				int my=e.getY();                       //e.getY()��ȡ���yλ��
				if(!gameover) {
				hero.moveToMouse(mx, my);	          //������x��y���ݸ�hero
			}
			repaint();
			}	
			
		};
		addMouseListener(adapter);                     //��������¼�              
		addMouseMotionListener(adapter);               //��������ƶ��¼�
		 
	}
 
	
	/*
	 *רҵ��ͼ������gΪ����
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {		
		super.paint(g);
		
		/*
		 * ��Ϸ����ʱ�Ľ���
		 */
       if(!Start) {
    	   g.drawImage(startBG, 0, 0, 512, 768, null);   
    	   g.drawImage(hero.img,230, 400, hero.W, hero.H, null); 
	   g.drawImage(but, 170, 500, but.getWidth(), but.getHeight(), null); 
	   
       }

       else{
		   g.drawImage(bg, 0, 0, 512, 768, null);                              //������
	   g.drawImage(hero.img, hero.x, hero.y, hero.W, hero.H, null);                //��hero
	   
	   
	   for(int i=0;i<eps.size();i++) {                                             //���л�
		   EP ep=eps.get(i);

	   g.drawImage(ep.img, ep.x, ep.y, ep.W, ep.H, null);
	 
	   }
	   
	   
	    
	   for(int i=0;i<fs.size();i++) {                                              //��Fire
		   Fire fire=fs.get(i);
	   g.drawImage(fire.img, fire.x, fire.y, fire.W, fire.H, null);
	   }
	   
	   
	   g.setColor(Color.white);                                                   //������
	   g.setFont(new Font("����",Font.BOLD,20));
	   g.drawString("����"+Score,10,30);
	   
	   

	   
	   for(int i=0;i<hero.HP;i++) {                                                //��Ѫ��
		   g.drawImage(hero.img, 350+i*35, 5, 30, 30, null);
	   }
	    
	   
	   /*
	    * ��EP�ı�ըЧ��
	    */
	if(db) {
       for(int i=0;i<120;i++) {
	   g.drawImage(own, dx-2, dy-5,1+i,1+i, null);
       }
	   db=false;
	   }
	
	
	/*
	 * ����ͣʱ�����Ķ���
	 */
	   if(stop) {
	   g.setColor(Color.red);
	   g.setFont(new Font("����",Font.BOLD,100));
	   g.drawString("��ͣ",160,360);
	   }
	   
	   /*
	    * ��Ϸ����ʱ����ʵ����
	    */
	   if(gameover) {
		   g.drawImage(bg1, 0, 0, 512, 768, null);
		   g.drawImage(hero.img, 210, 500, hero.W, hero.H, null); 
		   g.setColor(Color.red);
		   g.setFont(new Font("����",Font.BOLD,30));
		   g.drawString("������",200,360);
		   g.setColor(Color.white);
		   g.setFont(new Font("����",Font.BOLD,30));
		   g.drawString("�����Ļ���¿�ʼ",150,400);
		   
	   }
	   

	}

	}
	}



