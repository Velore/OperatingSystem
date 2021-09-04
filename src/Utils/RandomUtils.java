package Utils;

/**
 * @author chenzhuohong
 */
public class RandomUtils {

	/**
	 * @return 生成随机数字
	 */
	public static int randomInt() {
		return (int)(Math.random()*5+1);
	}

	public static int randomInt(int n) {
		return (int)(Math.random()*n+1);
	}

	public static int randomInt(int min, int max){
		return (int)(Math.random()*(max-min)+min);
	}

	/**
	 * @return 生成随机小写字母
	 */
	public static char randomChar() {
		return (char)(Math.random()*26+97);
	}

	/**
	 * @return 生成随机一个小写字母加一个数字
	 */
	public static String randomName() {
		return String.valueOf(randomChar())+ randomChar() + randomInt() + randomInt();
	}

	public static void main(String[] args) {
	}

}
