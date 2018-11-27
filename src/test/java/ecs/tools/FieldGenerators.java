package ecs.tools;

public class FieldGenerators {
	
	public enum Mode{ ALPHA, ALPHA_CAPS, ALPHA_NUMERIC, NUMERIC, ALPHA_SYMBOL}
	

	/**
	 * Will generate a random String based on a given Mode and a provided length. <br>
	 * {@link FieldGenerators.Mode}
	 * @param length
	 * @param mode
	 * @return
	 */
	public static String generateRandomString(int length, Mode mode) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (mode) {

		case ALPHA_CAPS:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
		case ALPHA:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			break;
		case ALPHA_NUMERIC:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
			break;
		case NUMERIC:
			characters = "1234567890";
			break;
		case ALPHA_SYMBOL:
			characters = "abc\'hurt{}!@£$%^&*()[]/?><;:";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		

		return buffer.toString();
	}

}
