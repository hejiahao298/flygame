import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;



/*
 * ����ͼƬ�Ĺ�����
 */
public class APP {
	/*
	 * ��ȡָ��λ���ϵ�ͼƬ�ķ���
	 * static�ص�:���õģ����еĶ�����һ�ݣ��������ڶ�����������
	 * path ͼƬ��·��
	 */
 public static BufferedImage getImg(String path) {
	 //����ͼƬ
	 //Java�е�IO�����������ݵĹܵ�(���������)
		
		try {
			BufferedImage img = ImageIO.read(APP.class.getResource(path));
			return img;
		} catch (IOException e) {                     //�����Ҳ���ͼƬ��ԭ��
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return null;
 }
}
