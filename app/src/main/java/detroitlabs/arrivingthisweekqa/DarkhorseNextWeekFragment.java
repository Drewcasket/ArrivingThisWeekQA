package detroitlabs.arrivingthisweekqa;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DarkhorseNextWeekFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    String [] darkHorseTitles;
    String [] darkHorseDescriptions;
    int[] images = {R.drawable.captainmidnight, R.drawable.conan, R.drawable.deepgravity, R.drawable.massive, R.drawable.mindmgmt};
    int favImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publishers, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = (ListView) getView().findViewById(android.R.id.list);

        Resources res = getResources();
        darkHorseTitles = res.getStringArray(R.array.darkhorseNextWeekTitles);
        darkHorseDescriptions = res.getStringArray(R.array.darkhorseNextWeekDescriptions);

        DHnwComicAdapter adapter = new DHnwComicAdapter(getActivity(), darkHorseTitles, darkHorseDescriptions, images, favImage );
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCompany = new Intent(getActivity(), ComicReleaseListActivity.class);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, darkHorseTitles[position]);
        startActivity(iCompany);
    }
}

class DHnwComicAdapter extends ArrayAdapter<String> {

    Context context;
    int[] images;
    int favImage;
    String[] titleArray;
    String[] descriptionArray;


    DHnwComicAdapter(Context c, String[] titles, String[] descriptions, int imgs[], int favImg) {

        super(c, R.layout.single_row_release_list, R.id.textView, titles);
        this.context = c;
        this.images = imgs;
        this.favImage = favImg;
        this.titleArray = titles;
        this.descriptionArray = descriptions;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_release_list, parent, false);
        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        final ImageButton myFavON = (ImageButton) row.findViewById(R.id.favoritesON);
        final ImageButton myFavOFF = (ImageButton) row.findViewById(R.id.favoritesOFF);
        TextView myTitle = (TextView) row.findViewById(R.id.textView);
        final TextView myDescriptionOFF = (TextView) row.findViewById(R.id.textView2a);
        final TextView myDescriptionON = (TextView) row.findViewById(R.id.textView2b);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        myDescriptionOFF.setText(descriptionArray[position]);
        myDescriptionON.setText(descriptionArray[position]);
        myDescriptionON.setVisibility(View.GONE);
        myFavOFF.setVisibility(View.VISIBLE);
        myFavON.setVisibility(View.GONE);


        myFavOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFavOFF.setVisibility(View.GONE);
                myFavON.setVisibility(View.VISIBLE);
                myDescriptionOFF.setVisibility(View.GONE);
                myDescriptionON.setVisibility(View.VISIBLE);
            }
        });

        myFavON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFavOFF.setVisibility(View.VISIBLE);
                myFavON.setVisibility(View.GONE);
                myDescriptionOFF.setVisibility(View.VISIBLE);
                myDescriptionON.setVisibility(View.GONE);
            }
        });
        return row;
    }
}
