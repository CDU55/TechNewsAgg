package com.example.technewsagg;

import java.util.Arrays;
import java.util.List;

public class UserSettings {

    private static List<String> publications= Arrays.asList("The Verge","Tom's hardware","VentureBeat","Zona IT","YODA","PlayTech","The Indian Express","Slash Gear","Tech Radar");

    public boolean theVerge;
    public boolean tomsHardware;
    public boolean ventureBeat;
    public boolean zonaIT;
    public boolean yoda;
    public boolean playTech;
    public boolean theIndianExpress;
    public boolean slashGear;
    public boolean techRadar;

    public UserSettings()
    {
        this.theVerge=false;
        this.theVerge=false;
        this.tomsHardware=false;
        this.ventureBeat=false;
        this.zonaIT=false;
        this.yoda=false;
        this.playTech=false;
        this.theIndianExpress=false;
        this.slashGear=false;
        this.techRadar=false;
    }

    public boolean validPublication(String publicationName)
    {
        if(publications.contains(publicationName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void flip(String publicationName)
    {
        if(publicationName.equals("The Verge"))
        {
            if(this.theVerge)
            {
                this.theVerge=false;
            }
            else
            {
                this.theVerge=true;
            }
        }
        else if(publicationName.equals("Tom's Hardware"))
        {
            if(this.tomsHardware)
            {
                this.tomsHardware=false;
            }
            else
            {
                this.tomsHardware=true;
            }
        }
        else if(publicationName.equals("VentureBeat"))
        {
            if(this.ventureBeat)
            {
                this.ventureBeat=false;
            }
            else
            {
                this.ventureBeat=true;
            }
        }
        else if(publicationName.equals("Zona IT"))
        {
            if(this.zonaIT)
            {
                this.zonaIT=false;
            }
            else
            {
                this.zonaIT=true;
            }
        }
        else if(publicationName.equals("YODA"))
        {
            if(this.yoda)
            {
                this.yoda=false;
            }
            else
            {
                this.yoda=true;
            }
        }
        else if(publicationName.equals("PlayTech"))
        {
            if(this.playTech)
            {
                this.playTech=false;
            }
            else
            {
                this.playTech=true;
            }
        }
        else if(publicationName.equals("The Indian Express"))
        {
            if(this.theIndianExpress)
            {
                this.theIndianExpress=false;
            }
            else
            {
                this.theIndianExpress=true;
            }
        }
        else if(publicationName.equals("Slash Gear"))
        {
            if(this.slashGear)
            {
                this.slashGear=false;
            }
            else
            {
                this.slashGear=true;
            }
        }
        else if(publicationName.equals("Tech Radar"))
        {
            if(this.techRadar)
            {
                this.techRadar=false;
            }
            else
            {
                this.techRadar=true;
            }
        }
    }

    public boolean getValue(String publicationName)
    {
        if(publicationName.equals("The Verge"))
        {
           return this.theVerge;
        }
        else if(publicationName.equals("Tom's Hardware"))
        {
            return this.tomsHardware;
        }
        else if(publicationName.equals("VentureBeat"))
        {
            return this.ventureBeat;
        }
        else if(publicationName.equals("Zona IT"))
        {
            return this.zonaIT;
        }
        else if(publicationName.equals("YODA"))
        {
            return this.yoda;
        }
        else if(publicationName.equals("PlayTech"))
        {
            return this.playTech;
        }
        else if(publicationName.equals("The Indian Express"))
        {
            return this.theIndianExpress;
        }
        else if(publicationName.equals("Slash Gear"))
        {
           return this.slashGear;
        }
        else if(publicationName.equals("Tech Radar"))
        {
            return this.techRadar;
        }
        return false;
    }

}
