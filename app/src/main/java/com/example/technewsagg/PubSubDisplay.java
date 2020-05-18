package com.example.technewsagg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PubSubDisplay {

    private static List<String> names= Arrays.asList("The Verge","Tom's Hardware","VentureBeat","Zona IT","YODA","PlayTech","The Indian Express","Slash Gear","Tech Radar");
    private static List<Integer> logos=Arrays.asList(R.drawable.the_verge_logo_2016,R.drawable.toms_hardware_logo,
            R.drawable.venture_beat_logo,R.drawable.zona_it_logo,R.drawable.yoda_logo,R.drawable.play_tech_logo
            ,R.drawable.indian_express_logo,R.drawable.slash_gear_logo,R.drawable.tech_radar_logo);

    public String publicationName;
    public int logoID;
    public boolean subscribed;

    private PubSubDisplay(String name,int logo,boolean subscribed)
    {
        this.publicationName=name;
        this.logoID=logo;
        this.subscribed=subscribed;
    }

    public static ArrayList<PubSubDisplay> getPublications(UserSettings settings)
    {
        ArrayList<PubSubDisplay> publications=new ArrayList<PubSubDisplay>();
        for(int i=0;i<names.size();i++)
        {

            publications.add(new PubSubDisplay(names.get(i),logos.get(i),settings.getValue(names.get(i))));
        }
        return publications;
    }

    public void flip()
    {
        if(this.subscribed)
        {
            this.subscribed=false;
        }
        else
        {
            this.subscribed=true;
        }
    }


}
