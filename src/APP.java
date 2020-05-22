import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;



/*
 * 处理图片的工具类
 */
public class APP {
	/*
	 * 读取指定位置上的图片的方法
	 * static特点:公用的，所有的对象公用一份，不依赖于对象。类名调用
	 * path 图片的路径
	 */
 public static BufferedImage getImg(String path) {
	 //加载图片
	 //Java中的IO流，输送数据的管道(输入输出流)
		
		try {
			BufferedImage img = ImageIO.read(APP.class.getResource(path));
			return img;
		} catch (IOException e) {                     //捕获找不到图片的原因
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return null;
 }
}
