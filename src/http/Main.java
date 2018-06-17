package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		Main program = new Main();
		program.start();
	}

	private void start() throws IOException {
		System.out.println("URLを入力してください");
		String urlStr = input();
		URL url = new URL(urlStr);
		//コネクション生成
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setInstanceFollowRedirects(false);
		httpURLConnection.setRequestProperty("Accept-Language", "jp");
		httpURLConnection.connect();
		Map<String, List<String>> headers = httpURLConnection.getHeaderFields();
		Iterator<String> iterator = headers.keySet().iterator();
		//INFO出力
		System.out.println("-----INFO-----");
		while(iterator.hasNext()) {
			String key = (String)iterator.next();
			System.out.println(key + ":" + headers.get(key));
		}
		//html出力
		System.out.println("-----html-----");
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
		httpURLConnection.disconnect();
	}

	public String input() throws IOException {
		//コンソール入力
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
}
