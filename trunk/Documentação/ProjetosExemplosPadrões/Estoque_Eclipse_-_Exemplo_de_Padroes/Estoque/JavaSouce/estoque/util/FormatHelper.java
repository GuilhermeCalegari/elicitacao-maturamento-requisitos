package estoque.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import estoque.client.Messages;

public class FormatHelper {


	public static DecimalFormat df = new DecimalFormat("#,###,##0.00");
	static String padraoData = "dd/MM/yyyy";
	private static NumberFormat numberFormat = NumberFormat.getInstance();
	
	public static BigDecimal verificaNumero(String numero) {
		try {
			NumberFormat nf = NumberFormat.getNumberInstance(Messages.getLocale());
			nf.setMaximumFractionDigits(2);
			return new BigDecimal(nf.parse(numero).doubleValue());
		} catch (Exception e) {
		}
		return null;
	}

	
	public static String format(Double number, int decimalAmount) {
		try {
			numberFormat.setGroupingUsed(true);
			numberFormat.setMinimumFractionDigits(decimalAmount);
			numberFormat.setMaximumFractionDigits(decimalAmount);
			numberFormat.setMinimumIntegerDigits(1);
			numberFormat.setMaximumIntegerDigits(10);
			return numberFormat.format(number);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String formatInteiro(Integer number, int amount) {
		try {
			numberFormat.setGroupingUsed(false);
			numberFormat.setMinimumIntegerDigits(amount);
			numberFormat.setMaximumIntegerDigits(amount);
			numberFormat.setMaximumFractionDigits(0);
			numberFormat.setMinimumFractionDigits(0);
			return numberFormat.format(number);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 
	 * @param number
	 * @param mask ex: "00000000.0000"
	 * @return
	 */
	public static String formatCustom(String number, String mask) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat(mask);
			DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
			decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
			return decimalFormat.format(new Double(number.substring(0, number.length() - 4) + "." + number.substring(number.length() - 4, number.length())));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String formataData(Date data) {
		try {
//			Pega o formato de data do ambiente			
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Messages.getLocale());	
 			
//			Pega o formato fixo			
//			SimpleDateFormat df = new SimpleDateFormat(padraoData);
			return df.format(data);
		} catch (Exception e) {
		}
		return "";
	}
	
	public static Date verificaData(String data) {
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Messages.getLocale());	

//			SimpleDateFormat df = new SimpleDateFormat(padraoData);
			return df.parse(data);
		} catch (Exception e) {
		}
		return null;
	}

	public static byte[] fileToByte(File imagem)throws Exception{
		FileInputStream fis = new FileInputStream(imagem);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}

	public static InputStream byteToInputStream(byte[] bytes)throws Exception{
		ByteArrayInputStream bais = new ByteArrayInputStream (bytes);
		return bais;
	}
	
}
