package com.e.vasialeleka.earthqueke;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter <Eearthquake>{
    public EarthquakeAdapter(Activity context, ArrayList<Eearthquake> quake){
        super (context,0, (List<Eearthquake>) quake);
    }

    private static final String SEPARATOR = " of ";

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
       View list = convertView;
       if (convertView== null){
           list= LayoutInflater.from(getContext()).inflate(R.layout.list_earthquake,parent,false);
       }

       Eearthquake current = getItem(position);

        Date dateObject = new Date(current.getDate());
        String formattedDate = formatDate(dateObject);
        String formatedTime = formatTime(dateObject);
        String primaryLOcation;
        String secondLocation;

         String location = current.getPlace();
          if (location.contains(SEPARATOR)){
              String[] loc = location.split(SEPARATOR);
              primaryLOcation = loc[1] ;
              secondLocation = loc[0]+SEPARATOR;

          }else{
              secondLocation = getContext().getString(R.string.near);
              primaryLOcation = location;
          }





        TextView mag =(TextView) list.findViewById(R.id.mag);

        {// color
            GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(current.getmag());
        magnitudeCircle.setColor(magnitudeColor);}

        mag.setText(current.getMag());

        TextView place = (TextView) list.findViewById(R.id.place);
        place.setText(secondLocation);

        TextView place2 =(TextView) list.findViewById(R.id.place2);
        place2.setText(primaryLOcation);

        TextView time = (TextView) list.findViewById(R.id.time) ;
        time.setText(formatedTime);

TextView date = (TextView)list.findViewById(R.id.date);
        date.setText(formattedDate);
        return list;
    }

    public String formatDate ( Date formate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(formate);
    }
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
