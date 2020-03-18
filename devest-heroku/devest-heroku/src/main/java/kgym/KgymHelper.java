package kgym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 画面系の処理や表示を簡略化するためのヘルパークラス。定数なども保存されます
 * @author hayashi-s
 */
public class KgymHelper {

    //トップへのリンクを定数として設定
    private final String homeURL = "/Login";

    public static KgymHelper getInstance(){
        return new KgymHelper();
    }

    //トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">ログイン</a>";
    }

    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output +="パスワード";
                }
                if(val.equals("mail")){
                    output +="メール";
                }
                if(val.equals("address")){
                    output +="住所";
                }
                output +="が未記入です<br>";
            }
        return output;
    }

    /**
     * 種別は数字で取り扱っているので画面に表示するときは日本語に変換
     * @param i
     * @return
     */
    public void common() {
    	/** @mainpage
    	 * Yahoo!ショッピングWeb APISDK共通関数
    	 */

    	/**
    	 * @file
    	 * @brief Yahoo!ショッピングWeb APISDK共通関数
    	 *
    	 * Yahoo!ショッピングWeb APISDKで、共通で使用する設定・関数を集めたファイルです。
    	 *
    	 * PHP version 5
    	 *
    	 */

    	/**
    	 * @brief アプリケーションID
    	 *
    	 * Yahoo! JAPANが提供するWeb APIを利用するアプリケーションには、アプリケーションIDが必要です。
    	 * Yahoo!デベロッパーネットワークで取得したアプリケーションIDを設定してください。
    	 * アプリケーションIDの取得については
    	 * http://e.developer.yahoo.co.jp/webservices/register_application
    	 * をご覧ください。
    	 *
    	 * @var string
    	 */
    	String appid = "あなたのアプリケーションID";//取得したアプリケーションIDを設定

    	/**
    	 * @brief カテゴリーID一覧
    	 *
    	 * 商品カテゴリの一覧です。
    	 * キーにカテゴリID、値にカテゴリ名が入っています。
    	 * @var array
    	 */
    	HashMap<String, String> categories = new HashMap<String,String>(){
    		{
    			put("1" ,"すべてのカテゴリから");
    	        put("13457","ファッション");
    	        put("2498","食品");
    	        put("2500","ダイエット、健康");
    	        put("2501","コスメ、香水");
    	        put("2502","パソコン、周辺機器");
    	        put("2504","AV機器、カメラ");
    	        put("2505", "家電");
    	        put("2506", "家具、インテリア");
    	        put("2507","花、ガーデニング");
    	        put("2508","キッチン、生活雑貨、日用品");
    	        put("2503","DIY、工具、文具");
    	        put("2509", "ペット用品、生き物");
    	        put("2510","楽器、趣味、学習");
    	        put("2511","ゲーム、おもちゃ");
    	        put("2497","ベビー、キッズ、マタニティ");
    	        put("2512","スポーツ");
    	        put("2513","レジャー、アウトドア");
    	        put("2514","自転車、車、バイク用品");
    	        put("2516","CD、音楽ソフト");
    	        put("2517","DVD、映像ソフト");
    	        put("10002","本、雑誌、コミック");
    		}
    	};
    	/**
    	 * @brief ソート方法一覧
    	 *
    	 * 検索結果のソート方法の一覧です。
    	 * キーに検索用パラメータ、値にソート方法が入っています。
    	 * @access private
    	 * @var array
    	 *
    	 */
    	Map<String,String> sortOrder =new HashMap<String,String>(){
    		{
    			put("-score" , "おすすめ順");
    	        put("+price" ,"商品価格が安い順");
    	        put("-price","商品価格が高い順");
    	        put("+name","ストア名昇順");
    	        put("-name","ストア名降順");
    	        put("-sold","売れ筋順");
    		}
    	};

    	/**
    	 * @brief 特殊文字を HTML エンティティに変換する
    	 *
    	 * これは、htmlspecialchars()を使いやすくするための関数です。
    	 * htmlspecialchars() http://jp.php.net/htmlspecialcharsより
    	 *   文字の中には HTML において特殊な意味を持つものがあり、
    	 *   それらの本来の値を表示したければ HTML の表現形式に変換してやらなければなりません。
    	 *   この関数は、これらの変換を行った結果の文字列を返します。
    	 *
    	 *   '&' (アンパサンド) は '&amp;' になります。
    	 *   ENT_QUOTES が設定されている場合のみ、 ''' (シングルクオート) は '&#039;'になります。
    	 *   '<' (小なり) は '&lt;' になります。
    	 *   '>' (大なり) は '&gt;' になります。
    	 *   ''' (シングルクオート) は '&#039;'になります。
    	 *
    	 * echo h("<>&'\""); //&lt;&gt;&amp;&#039;&quotと出力します。
    	 *
    	 * @param string $str 変換したい文字列
    	 *
    	 * @return string html用に変換した文字列
    	 *
    	 */

    }

//    public void h(String str){
//	    return htmlspecialchars(str, ENT_QUOTES);
//	}
}
