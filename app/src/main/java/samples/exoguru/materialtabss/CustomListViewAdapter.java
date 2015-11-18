package samples.exoguru.materialtabss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nikolay on 18/11/2015.
 */
public class CustomListViewAdapter extends ArrayAdapter<String> {

    private static final String TAG = "Active posts list view";

    public CustomListViewAdapter(Context context, ArrayList<String> posts){
        super(context, R.layout.custom_post_row_layout, posts);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_post_row_layout, parent, false);

        String postItem = getItem(position);
        TextView mPostText = (TextView) customView.findViewById(R.id.mPostText);
        ImageView mEditIcon = (ImageView) customView.findViewById(R.id.mEditIcon);
        ImageView mDeleteIcon = (ImageView) customView.findViewById(R.id.mDeleteIcon);

        mPostText.setText(postItem);
        mEditIcon.setImageResource(R.drawable.edit);
        mDeleteIcon.setImageResource(R.drawable.delete);

        mEditIcon.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(TAG, "EDIT BUTTON CLICKED");
                    }
                }
        );

        mDeleteIcon.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(TAG, "Delete BUTTON CLICKED");
                    }
                }
        );

        return customView;

    }


}
