

import javax.swing.JFrame;

public class GFrame extends JFrame{



	public GFrame() {
		setTitle("飞机大战");                //窗口设置标题
		setSize(512,768);                    //设置窗口大小
		setLocationRelativeTo(null);         //设置为屏幕中心位置
		setResizable(false);                  //不可移动大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               //点击x即为关闭程序
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    GFrame frame=new GFrame();
    GPanel panel=new GPanel();
 
    panel.action();                        //启动线程
    frame.setVisible(true);
    frame.add(panel);
	}

}
