import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class MyData {
	public String strID;
	public float fQty;
	public float fPrice;
	
	public MyData()
	{
		this.strID = "";
		this.fQty = 0.0f;
		this.fPrice = 0.0f;
	}
}

public class PortAndBench {
	
	private static String strInputError = "Input error.\n";
	private static String strIndex1 = "PORT:";
	private static String strIndex2 = "BENCH:";
	private static String strIndex3 = "|";
	private static String strComma = ",";
	private static String strSemiColumn = ";";
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<MyData> listPort = new ArrayList<MyData>();
		ArrayList<MyData> listBench = new ArrayList<MyData>();
		String strPort, strBench;

		listPort.clear();
		listBench.clear();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strInput = br.readLine();
		int nPos = strInput.indexOf(strIndex3);
		if (nPos < 0)
		{
			strPort = strInput;
			strBench = "";
		}
		else
		{
			strPort = strInput.substring(0, nPos);
			strBench = strInput.substring(nPos + 1);
		}
		
		nPos = strPort.indexOf(strIndex1);
		if (nPos < 0)
		{
			System.out.println(strInputError);
			return;
		}
		strPort = strPort.substring(strIndex1.length());
		while(true)
		{
			if (strPort.length() == 0)
				break;
			nPos = strPort.indexOf(strSemiColumn);
			String strTemp;
			if (nPos < 0) {
				strTemp = strPort;
				strPort = "";
			} else {
				strTemp = strPort.substring(0, nPos);
				strPort = strPort.substring(strTemp.length() + 1);
			}
			nPos = strTemp.indexOf(strComma);
			if (nPos < 0)
			{
				System.out.println(strInputError);
				return;
			}
			String strID = strTemp.substring(0, nPos);
			strTemp = strTemp.substring(strID.length() + 1);
			nPos = strTemp.indexOf(strComma);
			if (nPos < 0)
			{
				System.out.println(strInputError);
				return;
			}
			String strQty = strTemp.substring(0, nPos);
			String strPrice = strTemp.substring(strQty.length() + 1);
			MyData data = new MyData();
			data.strID = strID;
			data.fQty = Float.valueOf(strQty);
			data.fPrice = Float.valueOf(strPrice);
			listPort.add(data);
		}
		strBench = strBench.substring(strIndex2.length());
		while(true)
		{
			if (strBench.length() == 0)
				break;
			nPos = strBench.indexOf(strSemiColumn);
			String strTemp;
			if (nPos < 0) {
				strTemp = strBench;
				strBench = "";
			} else {
				strTemp = strBench.substring(0, nPos);
				strBench = strBench.substring(strTemp.length() + 1);
			}
			nPos = strTemp.indexOf(strComma);
			if (nPos < 0)
			{
				System.out.println(strInputError);
				return;
			}
			String strID = strTemp.substring(0, nPos);
			strTemp = strTemp.substring(strID.length() + 1);
			nPos = strTemp.indexOf(strComma);
			if (nPos < 0)
			{
				System.out.println(strInputError);
				return;
			}
			String strQty = strTemp.substring(0, nPos);
			String strPrice = strTemp.substring(strQty.length() + 1);
			MyData data = new MyData();
			data.strID = strID;
			data.fQty = Float.valueOf(strQty);
			data.fPrice = Float.valueOf(strPrice);
			listBench.add(data);
		}
		
		float fPortNav = 0.0f;
		float fBenchNav = 0.0f;
		String strTemp = "";
		for (int i = 0; i < listPort.size(); i++)
		{
			fPortNav += listPort.get(i).fQty * listPort.get(i).fPrice; 
		}
		for (int i = 0; i < listBench.size(); i++)
		{
			fBenchNav += listBench.get(i).fQty * listBench.get(i).fPrice; 
		}
		
		String strDisp = "";
		for (int i = 0; i < listPort.size(); i++)
		{
			int j;
			MyData dataPort = listPort.get(i);
			float fTotalNav = dataPort.fPrice * dataPort.fQty * 100 / fPortNav;
			for (j = 0; j < listBench.size(); j++)
			{
				if (dataPort.strID.equals(listBench.get(j).strID))
				{
					fTotalNav -= listBench.get(j).fQty * listBench.get(j).fPrice * 100 / fBenchNav;
					listBench.remove(j);
					break;
				}
			}
			if (i == listPort.size() - 1 && listBench.size() == 0)
				System.out.printf("%s:%.2f", dataPort.strID, fTotalNav);
			else
				System.out.printf("%s:%.2f,", dataPort.strID, fTotalNav);
			strDisp += dataPort.strID + ":" + fTotalNav + ",";
		}
		
		for (int i = 0; i < listBench.size(); i++)
		{
			MyData dataPort = listBench.get(i);
			float fTotalNav = dataPort.fPrice * dataPort.fQty * 100 / fBenchNav;
			strDisp += dataPort.strID + ":-" + fTotalNav + ",";
			if (i == listBench.size() - 1)
				System.out.printf("%s:-%.2f", dataPort.strID, fTotalNav);
			else
				System.out.printf("%s:-%.2f,", dataPort.strID, fTotalNav);
		}
		System.out.println();
	}
}
