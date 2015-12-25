package com.example.neozhang.circle;

import java.lang.reflect.Array;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;
>>>>>>> refs/heads/master

/**
 * Created by Huang on 11/28/2015.
 */
public interface IuserRepository {
    public ArrayList<String> getClosestEvents();                  //get the closest events to the user's current location
<<<<<<< HEAD
=======
    public ArrayList<LatLng> getClosestLocations();
>>>>>>> refs/heads/master
    public void changePassword(String newPassword);               //change the user's password
    public void joinEvent(String event);                          //allows the user to join an event
    public void leaveEvent(String event);                         //allows the user to leave an event
    public void inviteEvent(String event, String name);           //allows the user to send an invitation for an event

}
