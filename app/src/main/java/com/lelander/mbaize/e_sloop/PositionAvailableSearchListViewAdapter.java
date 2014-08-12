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
public class PositionAvailableSearchListViewAdapter extends BaseAdapter {


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<PositionAvailable> positionAvailableList = null;
    private ArrayList<PositionAvailable> arraylist;

    public PositionAvailableSearchListViewAdapter(Context context,
                                                  List<PositionAvailable> positionAvailableList) {
        this.context = context;
        this.positionAvailableList = positionAvailableList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<PositionAvailable>();
        this.arraylist.addAll(positionAvailableList);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        ImageView boatImage;
        TextView boatingActivity;
        TextView positions;
        TextView experiencePref;
        TextView departure;
        TextView destination;
        TextView boatType;
        TextView email;
    }

    @Override
    public int getCount() {
        return positionAvailableList.size();
    }

    @Override
    public Object getItem(int position) {
        return positionAvailableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_position_available_search, null);
            // Locate the ImageView in rounded_corner.xml
            holder.boatImage = (ImageView) view.findViewById(R.id.boatImage);
            // Locate the TextViews in rounded_cornerr.xml
            holder.boatingActivity = (TextView) view.findViewById(R.id.boatingActivityAvailablePlaceholder);
            holder.positions = (TextView) view.findViewById(R.id.crewWanted);
            holder.experiencePref = (TextView) view.findViewById(R.id.experiencePref);
            holder.destination = (TextView) view.findViewById(R.id.destination);
            holder.departure = (TextView) view.findViewById(R.id.departure);
            holder.boatType = (TextView) view.findViewById(R.id.boatType);
            holder.email = (TextView) view.findViewById(R.id.email);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into ImageView
        imageLoader.DisplayImage(positionAvailableList.get(position).getBoatImage(),
                holder.boatImage);
        // Set the results into TextViews
        holder.boatingActivity.setText(positionAvailableList.get(position).getBoatingActivity());
        holder.positions.setText(positionAvailableList.get(position).getPositions());
        holder.experiencePref.setText(positionAvailableList.get(position)
                .getExperiencePref());
        holder.destination.setText(positionAvailableList.get(position).getDestination());
        holder.departure.setText(positionAvailableList.get(position).getDeparture());
        holder.boatType.setText(positionAvailableList.get(position).getBoatType());
        holder.email.setText(positionAvailableList.get(position).getEmail());
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                    // Send single item click data to SingleItemView Class

                    Intent intent = new Intent(context, PositionAvailableView.class);
                    // Pass all data boatImage
                    intent.putExtra("boatImage",
                        (positionAvailableList.get(position).getBoatImage()));
                    // Pass all data positions
                    intent.putExtra("positions",
                            (positionAvailableList.get(position).getPositions()));
                    // Pass all data experiencePref
                    intent.putExtra("experiencePref",
                            (positionAvailableList.get(position).getExperiencePref()));
                    // Pass all data destination
                    intent.putExtra("destination",
                            (positionAvailableList.get(position).getDestination()));
                    // Pass all data departure
                    intent.putExtra("departure",
                            (positionAvailableList.get(position).getDeparture()));
                    // Pass all data boatType
                    intent.putExtra("boatType",
                            (positionAvailableList.get(position).getBoatType()));
                    // Pass all data email
                    intent.putExtra("email",
                        (positionAvailableList.get(position).getEmail()));
                    // Start SingleItemView Class
                    context.startActivity(intent);

            }
        });
        return view;
    }

}

