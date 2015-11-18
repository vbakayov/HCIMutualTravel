package samples.exoguru.materialtabss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


public class Tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

        String[] posts = {"Glasgow -> London", "Mezdra -> Sofia", "Varna -> Bourgas", "Vratza -> ASDAsdaxaad12w3sadas213asdsadsadasdasdadasdasdasdasdasdad"};
        ListAdapter adapter = new CustomListViewAdapter(v.getContext(), posts);
        ListView mPostList = (ListView) v.findViewById(R.id.mPostList);
        mPostList.setAdapter(adapter);



        return v;
    }

}
