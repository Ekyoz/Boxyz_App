package com.example.boxyz.fragment.heat;

import android.util.Log;

import com.example.boxyz.SocketClient;
import com.example.boxyz.models.TimeSlotItem;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Heat {

    private SocketClient socket = new SocketClient();
    private List<TimeSlotItem> timeSlotList = new ArrayList<>();
    private String reponse;

    public String getJson() throws ExecutionException, InterruptedException, JSONException {
        socket.execute("getTimeSlotJson");
        reponse = socket.get();
        socket.isCancelled();
        return reponse;
    }

    public String getDay(int id) throws ExecutionException, InterruptedException, JSONException {
        socket.execute("getHeatDay");
        reponse = socket.get();
        socket.isCancelled();
        return reponse;
    }

    public String getHour(int id) throws ExecutionException, InterruptedException, JSONException {
        socket.execute("getHeatHour");
        reponse = socket.get();
        socket.isCancelled();
        return reponse;
    }

    public String getTemp(int id) throws ExecutionException, InterruptedException, JSONException {
        socket.execute("getHeatTemp");
        reponse = socket.get();
        socket.isCancelled();
        return reponse;
    }

    public Boolean getState(int id) throws ExecutionException, InterruptedException, JSONException {
        socket.execute("getHeatState");
        reponse = socket.get();
        socket.isCancelled();
        return Boolean.valueOf(reponse);
    }

    public List<TimeSlotItem> getList(){
        try {
            socket.execute("getTimeSlotList");
            reponse = socket.get();
            socket.isCancelled();
            int listNum = Integer.parseInt(reponse);
            for (int i = 0; i < listNum; i++){
                timeSlotList.add(new TimeSlotItem(getDay(i), getHour(i),getTemp(i), getState(i)));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            Log.e("ERROR", String.valueOf(e));
        }
        return timeSlotList;
    }

}
