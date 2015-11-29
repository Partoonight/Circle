package com.example.neozhang.circle;

import java.util.ArrayList;

/**
 * Created by Huang on 11/28/2015.
 */
public class user {
    private String username, password;
    private ArrayList<String> eventHistory;
    private ArrayList<String> upcomingEvents;
    //private LatLng userLocation; get the user location

    public user(String username, String password, ArrayList<String> eventHistory, ArrayList<String> upcomingEvents)     //constructor
    {
        this.username = username;
        this.password = password;
        this.eventHistory = eventHistory;
        this.upcomingEvents = upcomingEvents;
        //set the userLocation as well
    }

    public String getName()                                                                                             //returns the username
    {
        return this.username;
    }

    public String getPassword()                                                                                         //returns the password
    {
        return this.password;
    }

    public ArrayList<String> getEventHistory()                                                                          //returns an arrayList of the event history
    {
        return this.eventHistory;
    }

    public void setEventHistory(String event)                                                                           //moves an event to eventHistory
    {
        eventHistory.add(event);
    }

    public ArrayList<String> getUpcomingEvents()                                                                        //returns an arrayList of upcoming events
    {
        return this.upcomingEvents;
    }

    public void setUpcomingEvents(String event)                                                                         //moves an to upcomingEvents list
    {
        upcomingEvents.add(event);
    }

    //TODO: return the user's current location
}
