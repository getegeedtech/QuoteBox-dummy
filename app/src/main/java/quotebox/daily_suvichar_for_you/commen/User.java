package quotebox.daily_suvichar_for_you.commen;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import quotebox.daily_suvichar_for_you.R;

public class User {
    Context context;
    public void removeUser(){
        sharedPreferences.edit().clear().commit();
    }

    public String isNull(String value){
        if(value.equals("null")){return "";}
        return value;
    }
    public int randnum(int min,int max){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
    public int dpToPx(int dp){
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }
    public String capitalizeWords(String input) {
        StringBuilder capitalized = new StringBuilder();
        boolean capitalizeNext = true;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '\'') {
                capitalizeNext = true;
            } else if (Character.isWhitespace(currentChar)) {
                capitalized.append(currentChar);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                capitalized.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                capitalized.append(Character.toLowerCase(currentChar));
            }
        }
        return capitalized.toString().trim();
    }
    public void sendError(String message){

    }

    public void sendEmail(String email,String subject,String message) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.makdfs.com/sendallmail", null, null){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("from", "getegetechnology@gmail.com");
                params.put("password", "plzgpksofvaqgmga");
                params.put("name", "GETEGE");
                params.put("to", email);
                params.put("subject", subject);
                params.put("message", message);
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public String getName() {
        name = sharedPreferences.getString("name","");
        return name;
    }
    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("name",name).commit();
    }
    public String getemail() {
        email = sharedPreferences.getString("email","");
        return email;
    }
    public void setemail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("email",email).commit();
    }
    public String getContact() {
        contact = sharedPreferences.getString("contact","");
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
        sharedPreferences.edit().putString("contact",contact).commit();
    }
    public String getuserid() {
        userid = sharedPreferences.getString("userid","");
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
        sharedPreferences.edit().putString("userid",userid).commit();
    }
    public String getNoad() {
        noad = sharedPreferences.getString("noad","");
        return noad;
    }
    public void setnNoad(String noad) {
        this.noad = noad;
        sharedPreferences.edit().putString("noad",noad).commit();
    }
    public String getPick() {
        pick = sharedPreferences.getString("pick","https://www.1clicksolve.com/assets/img/quote/profile.jpg");
        return pick;
    }
    public void setPick(String pick) {
        this.pick = pick;
        sharedPreferences.edit().putString("pick",pick).commit();
    }
    public String getUserdata() {
        userdata = sharedPreferences.getString("userdata","");
        return userdata;
    }
    public void setUserdata(String userdata) {
        this.userdata = userdata;
        sharedPreferences.edit().putString("userdata",userdata).commit();
    }
    public String getShowContact() {
        showContact = sharedPreferences.getString("showContact","");
        return showContact;
    }
    public void setShowContact(String showContact) {
        this.showContact = showContact;
        sharedPreferences.edit().putString("showContact",showContact).commit();
    }
    public String getRefer() {
        refer = sharedPreferences.getString("refer","");
        return refer;
    }
    public void setRefer(String refer) {
        this.refer = refer;
        sharedPreferences.edit().putString("refer",refer).commit();
    }
    public String getReferAmount() {
        referAmount = sharedPreferences.getString("referAmount","");
        return referAmount;
    }
    public void setReferAmount(String referAmount) {
        this.referAmount = referAmount;
        sharedPreferences.edit().putString("referAmount",referAmount).commit();
    }
    public String getCurrentDate() {
        currentDate = sharedPreferences.getString("currentDate","");
        return currentDate;
    }
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
        sharedPreferences.edit().putString("currentDate",currentDate).commit();
    }
    public String getRegDate() {
        regDate = sharedPreferences.getString("regDate","");
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
        sharedPreferences.edit().putString("regDate",regDate).commit();
    }
    public String getShowAd() {
        showAd = sharedPreferences.getString("showAd","");
        return showAd;
    }
    public void setShowAd(String showAd) {
        this.showAd = showAd;
        sharedPreferences.edit().putString("showAd",showAd).commit();
    }
    public String getUpdate() {
        update = sharedPreferences.getString("update","");
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
        sharedPreferences.edit().putString("update",update).commit();
    }
    public String getPremium() {
        premium = sharedPreferences.getString("premium","");
        return premium;
    }
    public void setPremium(String premium) {
        this.premium = premium;
        sharedPreferences.edit().putString("premium",premium).commit();
    }
    public int getCurrentPlay() {
        curentplay = sharedPreferences.getInt("curentplay",-1);
        return curentplay;
    }
    public void setCurrentPlay(int curentplay) {
        this.curentplay = curentplay;
        sharedPreferences.edit().putInt("curentplay",curentplay).commit();
    }

    public void saveBitmapForOneDay(Bitmap bitmap) {
        try (FileOutputStream fos = context.openFileOutput("bitmap_image", Context.MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (IOException ignored) {}

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        SharedPreferences prefs = context.getSharedPreferences("BitmapPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("bitmap_saved_date", currentDate);
        editor.apply();
    }
    public boolean isBitmapValid() {
        SharedPreferences prefs = context.getSharedPreferences("BitmapPrefs", Context.MODE_PRIVATE);
        String savedDate = prefs.getString("bitmap_saved_date", null);
        if (savedDate == null) {return false;}

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        return savedDate.equals(currentDate);
    }
    public Bitmap getValidBitmap() {
        if (isBitmapValid()) {
            try {
                return BitmapFactory.decodeStream(context.openFileInput("bitmap_image"));
            } catch (IOException ignored) {}
        }
        return null;
    }

    private String userid;
    private String name;
    private String email;
    private String contact;
    private String noad;
    private String pick;
    private String userdata;
    private String showContact;
    private String refer;
    private String referAmount;
    SharedPreferences sharedPreferences;
    private String currentDate;
    private String showAd;
    private String regDate;
    private String update;
    private String premium;
    private int curentplay;

    public User(Context context){
        this.context=context;
        sharedPreferences= context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }

}
