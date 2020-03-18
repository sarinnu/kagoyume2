package kgym;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

        try(PrintWriter out=response.getWriter()){

            //アクセスルートチェック
//            String accesschk = request.getParameter("ac");
//            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
//                throw new Exception("不正なアクセスです");
//            }

            String appid="dj00aiZpPVF2NnkxbWpIeTBPQSZzPWNvbnN1bWVyc2VjcmV0Jng9ZmU-";
            String item=request.getParameter("item");

            String url="https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid="+appid+"&query="+item;

            URL con_url=new URL(url);

            HttpURLConnection conn=(HttpURLConnection)con_url.openConnection();

            conn.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String json="";
            String receiver="";
            while((receiver=br.readLine())!=null) {
            	json+=receiver;
            }

            br.close();

            ObjectMapper mapper=new ObjectMapper();
            JsonNode root=mapper.readTree(json);

            String keyword=root.get("ResultSet").get(0).get("Result").get(0).get("Query").textValue();
            ArrayList<String> items=new ArrayList<String>();
            HashMap<String, String> image=new HashMap<String,String>();
            HashMap<String, Integer> price=new HashMap<String, Integer>();
            for(int i=0;i<20;i++) {
            	if(root.get("ResultSet").get(0).get("Result").get(0).get("Hit").get(i).get("Name").textValue()=="") {
            		break;
            	}
            	String name=root.get("ResultSet").get(0).get("Result").get(0).get("Hit").get(i).get("Name").textValue();
            	items.add(name);
            	image.put(name,root.get("ResultSet").get(0).get("Result").get(0).get("Hit").get(i).get("Image").get(0).get("Small").textValue());
            	price.put(name,root.get("ResultSet").get(0).get("Result").get(0).get("Hit").get(i).get("Price").intValue());
            }

            request.setAttribute("keyword", keyword);
            request.setAttribute("items",items);
            request.setAttribute("image", image);
            request.setAttribute("price",price);

            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
