package com.example.boxyz.fragment.heat;

import com.example.boxyz.MainActivity;
import com.example.boxyz.SocketClient;
import com.example.boxyz.models.TimeSlotItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Heat {

    private List<TimeSlotItem> timeSlotList = new ArrayList<>();
    MainActivity mainActivity = new MainActivity();

    public List<TimeSlotItem> setList(){
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                int listNum = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").length();
                for (int i = 0; i < listNum-1; i++) {
                    i++;
                    String name = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(i)).getString("name");
                    String temp = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(i)).getString("temp");
                    //boolean state = Boolean.parseBoolean(jsonObject.getJSONObject("heat").getJSONObject("timeSlot").getJSONObject(String.valueOf(Boolean.valueOf(String.valueOf(i)))).getString("state"));
                    i--;
                    timeSlotList.add(new TimeSlotItem(temp + "°C", name));
                }
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return timeSlotList;
    }

    public String getName(int position) {
        String reponse = null;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getString("name");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = "erreur";
        }
        return reponse;
    }

    public JSONArray getDay(int position) {
        JSONArray reponse = null;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getJSONArray("day");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return reponse;
    }

    public String getHourStart(int position) {
        String reponse = null;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getString("hourStart");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = "erreur";
        }
        return reponse;
    }

    public String getHourEnd(int position) {
        String reponse = null;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getString("hourEnd");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = "erreur";
        }
        return reponse;
    }

    public int getTemp(int position) {
        int reponse = 15;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = Integer.parseInt(jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getString("temp"));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = 15;
        }
        return reponse;
    }

    public boolean getState(int position) {
        String reponse = null;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("clock").getJSONObject("timeSlot").getJSONObject(String.valueOf(position)).getString("state");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = "erreur";
        }
        return Boolean.parseBoolean(reponse);
    }

    public int getHeat() {
        int reponse = 15;
        try {
            SocketClient socket = new SocketClient();
            socket.execute("getJson");
            String get = socket.get();
            if (get != null) {
                JSONObject jsonObject = new JSONObject(get);
                reponse = jsonObject.getJSONObject("thermostas").getInt("temperature");
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            reponse = 15;
        }
        return reponse;
    }


}
