package kgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import base.DBManager;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {

    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }

    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public UserDataDTO search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            //
            String sql = "SELECT * FROM user_t";
            boolean flag = false;
            if (!ud.getName().equals("")) {
                sql += " WHERE name like ?";
                flag = true;
            }
            if (!ud.getPassword().equals("")) {
                if(!flag){
                    sql += " WHERE password like ?";
                    flag = true;
                }else{
                    sql += " AND password like ?";
                }
            }
            if (!ud.getMail().equals("")) {
                if(!flag){
                    sql += " WHERE mail like ?";
                }else{
                    sql += " AND mail like ?";
                }
            }
            st =  con.prepareStatement(sql);
            st.setString(1, "%"+ud.getName()+"%");
            st.setString(2,  ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());

            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));

            System.out.println("search completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
//
//    /**
//     * ユーザーIDによる1件のデータの検索処理を行う。
//     * @param ud 対応したデータを保持しているJavaBeans
//     * @throws SQLException 呼び出し元にcatchさせるためにスロー
//     * @return 検索結果
//     */
//    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
//        Connection con = null;
//        PreparedStatement st = null;
//        try{
//            con = DBManager.getConnection();
//
//            String sql = "SELECT * FROM user_t WHERE userID = ?";
//
//            st =  con.prepareStatement(sql);
//            st.setInt(1, ud.getUserID());
//
//            ResultSet rs = st.executeQuery();
//            rs.next();
//            UserDataDTO resultUd = new UserDataDTO();
//            resultUd.setUserID(rs.getInt(1));
//            resultUd.setName(rs.getString(2));
//            resultUd.setBirthday(rs.getDate(3));
//            resultUd.setTel(rs.getString(4));
//            resultUd.setType(rs.getInt(5));
//            resultUd.setComment(rs.getString(6));
//            resultUd.setNewDate(rs.getTimestamp(7));
//
//            System.out.println("searchByID completed");
//
//            return resultUd;
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//            throw new SQLException(e);
//        }finally{
//            if(con != null){
//                con.close();
//            }
//        }
//
//    }

    public UserDataDTO update(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            //
            String sql = "UPDATE user_t SET";
            boolean flag = false;
            if (!ud.getName().equals("")) {
                sql += "name=?";
                flag = true;
            }
            if (!ud.getPassword().equals("")) {
                if(!flag){
                    sql += "password=?";
                    flag = true;
                }else{
                    sql += ",password=?";
                }
            }
            if (!ud.getMail().equals("")) {
                if(!flag){
                    sql += "mail=?";
                    flag=true;
                }else{
                    sql += ",mail=?";
                }
            }

            if (!ud.getAddress().equals("")) {
                if(!flag){
                    sql += "address=?";
                    flag=true;
                }else{
                    sql += ",address=?";
                }
            }

            if (ud.getTotal()!=0) {
                if(!flag){
                    sql += "comment=?";
                    flag=true;
                }else{
                    sql += ",comment=?";
                }
            }

            sql+="WHWRE name=?;";
            st =  con.prepareStatement(sql);
            st.setString(1, ud.getName().trim());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4,ud.getAddress().trim());
            st.setInt(5, ud.getTotal());

            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));

            System.out.println("search completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

}
