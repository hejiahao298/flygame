

import javax.swing.JFrame;

public class GFrame extends JFrame{



	public GFrame() {
		setTitle("�ɻ���ս");                //�������ñ���
		setSize(512,768);                    //���ô��ڴ�С
		setLocationRelativeTo(null);         //����Ϊ��Ļ����λ��
		setResizable(false);                  //�����ƶ���С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               //���x��Ϊ�رճ���
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    GFrame frame=new GFrame();
    GPanel panel=new GPanel();
 
    panel.action();                        //�����߳�
    frame.setVisible(true);
    frame.add(panel);
	}

}
