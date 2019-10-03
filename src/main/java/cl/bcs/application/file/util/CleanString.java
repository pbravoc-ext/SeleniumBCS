package cl.bcs.application.file.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class CleanString {
	private static byte[] REMOVE = new byte[32];
	private final int FFFE = 0xFFFE;
	private final char FFFF = 0xFFFF;
	private final char SURROGATE_START = 0xD800;
	private final char SURROGATE_END = 0xDFFF;

	static {
		REMOVE[0x00] = 1;
		REMOVE[0x01] = 1;
		REMOVE[0x02] = 1;
		REMOVE[0x03] = 1;
		REMOVE[0x04] = 1;
		REMOVE[0x05] = 1;
		REMOVE[0x06] = 1;
		REMOVE[0x07] = 1;
		REMOVE[0x08] = 1;
		REMOVE[0x0B] = 1;
		REMOVE[0x0C] = 1;
		REMOVE[0x0E] = 1;
		REMOVE[0x0F] = 1;
		REMOVE[0x10] = 1;
		REMOVE[0x11] = 1;
		REMOVE[0x12] = 1;
		REMOVE[0x13] = 1;
		REMOVE[0x14] = 1;
		REMOVE[0x15] = 1;
		REMOVE[0x16] = 1;
		REMOVE[0x17] = 1;
		REMOVE[0x18] = 1;
		REMOVE[0x19] = 1;
		REMOVE[0x1A] = 1;
		REMOVE[0x1B] = 1;
		REMOVE[0x1C] = 1;
		REMOVE[0x1D] = 1;
		REMOVE[0x1E] = 1;
		REMOVE[0x1F] = 1;
	}
	
	protected String cleanData(String value) {
		char[] buffer = null;
		int len = value.length();
		int srcI = 0;
		int tgtI = 0;
		int copyLength = 0;
		int i = 0;
		while (i < len) {
			int cp = value.codePointAt(i);
			if (cp > FFFF) {
				i = i + 2;
				copyLength = copyLength + 2;
			} else {
				if ((cp < 0x20 && (REMOVE[cp] > 0))
						|| (cp >= SURROGATE_START && cp <= SURROGATE_END)
						|| (cp == FFFF || cp == FFFE)) {
					if (buffer == null) {
						buffer = value.toCharArray();
					}
					System.arraycopy(buffer, srcI, buffer, tgtI, copyLength);
					tgtI = tgtI + copyLength;
					srcI = i + 1;
					copyLength = 0;
				} else {
					copyLength = copyLength + 1;
				}
				i = i + 1;
			}
		}

		if (buffer == null) {
			return value;
		} else {
			System.arraycopy(buffer, srcI, buffer, tgtI, copyLength);
			String newValue = new String(buffer, 0, tgtI + copyLength);
			return limpiaBasura(newValue);
		}
	}


	/**
	 * 
	 * @param token
	 * @return
	 */
	public static String limpiaBasura(String token) {

		StringBuilder stringBuilder = new StringBuilder(token.length());
		CharacterIterator it = new StringCharacterIterator(token);
		
		for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
			
			switch (ch) {
			case 'ì':
				stringBuilder.append("ó");
				break;
			case '©':
				stringBuilder.append("é");
				break;
			case '‡':
				stringBuilder.append("ó");
				break;
			case 'Ù':
				stringBuilder.append("é");
				break;
			case 'Å':
				stringBuilder.append("á");
				break;
			case '¥':
				stringBuilder.append("í");
				break;
			case '—':
				stringBuilder.append("í");
				break;
			case '£':
				stringBuilder.append("ú");
				break;
			case 'Î':
				stringBuilder.append("ú");
				break;
			case '':
				stringBuilder.append("á");
				break;
			case 'Œ':
				stringBuilder.append("°");
				break;
			case '‹':
				stringBuilder.append("ñ");
				break;
			case '§':
				stringBuilder.append("ú");
				break;
			case 'ä':
				stringBuilder.append("Ó");
				break;
			case 'Ý':
				stringBuilder.append("Á");
				break;
			case 'ÿ':
				stringBuilder.append("-");
				break;
			case '“':
				stringBuilder.append("ñ");
				break;
			case '²':
				stringBuilder.append("Í");
				break;
			case ',':
				stringBuilder.append(" ");
				break;
			default:
				stringBuilder.append(ch);
				break;
			}
		}
		return stringBuilder.toString();
	}

}
