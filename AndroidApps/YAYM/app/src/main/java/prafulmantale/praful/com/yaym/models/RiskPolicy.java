package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.helpers.AppConstants;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class RiskPolicy {

    private static final String TAG = RiskPolicy.class.getSimpleName();

    private String name;
    private String displayName;
    private String notes;

    public RiskPolicy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static List<RiskPolicy> fromJSONArray(JSONArray jsonArray) {

        List<RiskPolicy> list = new ArrayList<RiskPolicy>();

        if (jsonArray == null || jsonArray.length() == 0) {
            return list;
        }

        int len = jsonArray.length();

        for (int i = 0; i < len; i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                RiskPolicy policy = fromJSON(jsonObject);

                if (policy == null) {
                    continue;
                }

                list.add(policy);
            } catch (JSONException ex) {
                Log.e(TAG, "Exception while extracting RiskPolicy JSONObject from JSONArray");
                ex.printStackTrace();
                continue;
            }

        }


        return list;
    }

    private static RiskPolicy fromJSON(JSONObject jsonObject) {

        RiskPolicy riskPolicy = new RiskPolicy();

        try {
            riskPolicy.name = jsonObject.getString("policyName");
            riskPolicy.displayName = jsonObject.getString("policyDisplayName");
            riskPolicy.notes = jsonObject.getString("notes");
            if(riskPolicy.notes.equals(AppConstants.NULL_STR)){
                riskPolicy.notes = "";
            }
        } catch (JSONException ex) {
            Log.e(TAG, "Exception while extracting RiskPolicy from JSON");
            ex.printStackTrace();
            riskPolicy = null;
        }

        return riskPolicy;
    }
}
