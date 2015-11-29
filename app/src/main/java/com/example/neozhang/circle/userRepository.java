package com.example.neozhang.circle;


        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import com.google.android.gms.maps.*;
        import com.google.android.gms.maps.model.*;
        import android.app.Activity;
        import android.os.Bundle;

/**
 * Created by Huang on 11/28/2015.
 */
public class userRepository {
    user newUser;
    public userRepository(user newUser)
    {
        this.newUser = newUser;
    }
    public ArrayList<String> getClosestEvents(){
        return new ArrayList<String>();
    }


}
