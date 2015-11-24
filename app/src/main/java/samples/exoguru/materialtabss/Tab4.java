package samples.exoguru.materialtabss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Viktor on 11/5/2015.
 */
public class Tab4 extends Fragment {
    final FragmentTransaction ft=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_4, container, false);
        final Button bt1 = (Button) v.findViewById(R.id.buttonSeatsPost);
        final Button bt2 = (Button) v.findViewById(R.id.buttonLuggagePost);

        //post offering seats
        v.findViewById(R.id.buttonSeatsPost).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.postFragment, new SeatPostFragment());
                ft.addToBackStack(null);
                bt2.setVisibility(View.INVISIBLE);
                bt1.setVisibility(View.INVISIBLE);

                ft.commit();

            }
        });
        //post offering luggage
        v.findViewById(R.id.buttonLuggagePost).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(((ViewGroup)(getView())).getId(), new PostLuggageFragment());
                ft.addToBackStack(null);
                bt1.setVisibility(View.INVISIBLE);
                bt2.setVisibility(View.INVISIBLE);
                ft.commit();
            }
        });
        return v;
    }
}
