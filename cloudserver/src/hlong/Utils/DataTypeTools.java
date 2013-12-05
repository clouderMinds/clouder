package hlong.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 该类主要用来可以查询数据包的类型都有哪些，包括byte类型以及String类型
 * @author wojiaolongyinong
 *
 */
public class DataTypeTools {
	private static Map<String,Byte> type_map = new HashMap<String,Byte>();
	
	/**
	 * 静态块，在加载类的时候，将配置文件中的数据放到Map中
	 */
	static{
		File file = new File("DataType");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String text = reader.readLine();
			while(text != null){//说明读到了内容
				StringTokenizer st = new StringTokenizer(text);
				//拿到这一行字符内容被分为几个部分
				int number = st.countTokens();
				if(number == 3){//说明为类型内容
					st.nextToken();//将行号读掉
					String typeName = st.nextToken();
					String value = st.nextToken();
					Log.println(value);
					Integer.parseInt(value);
					int va = Integer.getInteger(value);
					type_map.put(typeName, (byte)va);
				}
				text = reader.readLine();
			}
			reader.close();
			fis.close();
		} catch (FileNotFoundException e) {
			Log.println("读取配置文件出错！");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 用来判断是否存在相应的数据包的字符串表示
	 * @param type	数据包类型名
	 * @return	如果存在则返回true，否则返回false
	 */
	public static boolean isHaved(String type){
		return type_map.containsKey(type);
	}
	
	/**
	 * 用来判断是否存在相应的数据包的标示值
	 * @param value	 数据包表示值
	 * @return	如果存在则返回true，否则返回false
	 */
	public static boolean isHaved(byte value){
		return type_map.containsValue(value);
	}
	
	public static void main(String[] args){
		Log.println("存在：" + isHaved("LoginRequest"));
	}
}
