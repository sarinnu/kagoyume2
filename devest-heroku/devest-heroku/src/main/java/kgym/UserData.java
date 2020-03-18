package kgym;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBeans。DTOと違い画面表示系への結びつきが強い
 * DTOへの変換メソッド、入力チェックリストを出力するメソッドも準備されている←ちょっと仕事しすぎかも
 * @author hayashi-s
 */
public class UserData implements Serializable{
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;


    public UserData(){
        this.name = "";
        this.password="";
        this.mail="";
        this.address= "";
        this.total= 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if(password.trim().length()==0){
            this.password= "";
        }else{
            this.password= password;
        }
    }
    public String getMail(){
		return mail;
    }
    public void setMail(String mail) {
        if(mail.trim().length()== 0){
            this.mail = "";
        }else{
            this.mail = mail;
        }

    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if(address.trim().length()==0){
            this.address = "";
        }else{
            this.address = address;
        }
    }

    public int getTotal() {
    	return total;
    }
    public void setTotal(String total) {
    	if(total.trim().length()==0) {
    		this.total=0;
    	}else {
    		this.total=Integer.parseInt(total);
    	}
    }

    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }


        return chkList;
    }

    public void UD2DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
    }

    public Cookie CookieMapping() throws UnsupportedEncodingException {
    	this.name=URLEncoder.encode(this.name,"UTF-8");

    	Cookie user=new Cookie("userName",this.name);
    	return user;
    }
}
