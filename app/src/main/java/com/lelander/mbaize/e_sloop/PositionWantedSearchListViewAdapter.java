package com.lelander.mbaize.e_sloop;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Micah on 8/1/2014.
 */
public class PositionWantedSearchListViewAdapter extends BaseAdapter {


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<PositionWanted> positionWantedList = null;
    private ArrayList<PositionWanted> arraylist;

    public PositionWantedSearchListViewAdapter(Context context,
                                                  List<PositionWanted> positionWantedList) {
        this.context = context;
        this.positionWantedList = positionWantedList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<PositionWanted>();
        this.arraylist.addAll(positionWantedList);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        ImageView profileImage;
        TextView boatingActivity;
        TextView positions;
        TextView experienceHad;
        TextView boatTypes;
        TextView email;
    }

    @Override
    public int getCount() {
        return positionWantedList.size();
    }

    @Override
    public Object getItem(int position) {
        return positionWantedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_position_wanted_search, null);
            // Locate the ImageView in rounded_corner.xml
            holder.profileImage = (ImageView) view.findViewById(R.id.positionWantedProfileImage);
            // Locate the TextViews in rounded_cornerr.xml
            holder.boatingActivity = (TextView) view.findViewById(R.id.listItemWantedBoatType);
            holder.positions = (TextView) view.findViewById(R.id.list_item_position_wanted);
            holder.experienceHad = (TextView) view.findViewById(R.id.listItemExperienceHad);
            holder.boatTypes = (TextView) view.findViewById(R.id.listItemWantedBoatType);
            holder.email = (TextView) view.findViewById(R.id.listItemWantedEmail);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into ImageView
        imageLoader.DisplayImage(positionWantedList.get(position).getProfileImage(),
                holder.profileImage);
        // Set the results into TextViews
        holder.boatingActivity.setText(positionWantedList.get(position).getBoatingActivity());
        holder.positions.setText(positionWantedList.get(position).getPositions());
        holder.experienceHad.setText(positionWantedList.get(position)
                .getExperienceHad());
        holder.boatTypes.setText(positionWantedList.get(position).getBoatType());
        holder.email.setText(positionWantedList.get(position).getEmail());
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class

                Intent intent = new Intent(context, PositionWantedView.class);
                // Pass all data boatImage
                intent.putExtra("boatImage",
                        (positionWantedList.get(position).getProfileImage()));
                // Pass all data positions
                intent.putExtra("positions",
                        (positionWantedList.get(position).getPositions()));
                // Pass all data experiencePref
                intent.putExtra("experiencePref",
                        (positionWantedList.get(position).getExperienceHad()));
                // Pass all data boatType
                intent.putExtra("boatType",
                        (positionWantedList.get(position).getBoatType()));
                // Pass all data email
                intent.putExtra("email",
                        (positionWantedList.get(position).getEmail()));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return view;
    }

}

