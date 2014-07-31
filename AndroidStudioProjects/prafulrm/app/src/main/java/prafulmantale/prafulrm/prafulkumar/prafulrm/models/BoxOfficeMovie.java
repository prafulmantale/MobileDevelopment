package prafulmantale.prafulrm.prafulkumar.prafulrm.models;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 7/26/14.
 */
public class BoxOfficeMovie implements Serializable{

    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private int criticsScore;
    private ArrayList<String> castList;
    private String largePosterUrl;
    private String criticsConsensus;
    private int audienceScore;


    public static BoxOfficeMovie fromJSON(JSONObject jsonObject){

        BoxOfficeMovie bom = new BoxOfficeMovie();

        try{
            bom.title = jsonObject.getString("title");
            bom.year = jsonObject.getInt("year");
            bom.synopsis = jsonObject.getString("synopsis");
            bom.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail").replace("tmb","ori");
            bom.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            bom.castList = new ArrayList<String>();

            JSONArray castObjList = jsonObject.getJSONArray("abridged_cast");
            for(int i = 0; i < castObjList.length(); i++){
                bom.castList.add(castObjList.getJSONObject(i).getString("name"));
            }

            bom.largePosterUrl = jsonObject.getJSONObject("posters").getString("detailed").replace("tmb","ori");
            bom.criticsConsensus = jsonObject.getString("critics_consensus");
            bom.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");

        }
        catch (JSONException ex){
            ex.printStackTrace();
            return null;
        }

        return bom;
    }

    public static List<BoxOfficeMovie> fromJSON(JSONArray jsonArray){
        List<BoxOfficeMovie> list = new ArrayList<BoxOfficeMovie>();


        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                 obj = jsonArray.getJSONObject(i);
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            if(obj == null){
                continue;
            }

            BoxOfficeMovie movie = fromJSON(obj);
            if(movie == null){
                continue;
            }

            list.add(movie);

        }

        return list;
    }

    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public String getSynopsis(){
        return synopsis;
    }

    public String getPosterUrl(){
        return posterUrl;
    }

    public int getCriticsScore(){
        return criticsScore;
    }

    public String getCastList(){
        return TextUtils.join(", ", castList);
    }

    public String getLargePosterUrl() {
        return largePosterUrl;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public int getAudienceScore() {
        return audienceScore;
    }
}
