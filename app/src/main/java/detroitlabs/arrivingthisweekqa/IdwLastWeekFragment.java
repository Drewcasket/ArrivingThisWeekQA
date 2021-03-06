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


public class IdwLastWeekFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    String [] comicTitles;
    String [] comicDescriptions;
    int[] images = {R.drawable.adventurekartel, R.drawable.blazebrothers, R.drawable.judgedredd, R.drawable.skylanders, R.drawable.teenagemutantninjaturtles};
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
        comicTitles = res.getStringArray(R.array.idwLastWeekTitles);
        comicDescriptions = res.getStringArray(R.array.idwLastWeekDescriptions);

        IDWlwComicAdapter adapter = new IDWlwComicAdapter(getActivity(), comicTitles, comicDescriptions, images, favImage );
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCompany = new Intent(getActivity(), ComicReleaseListActivity.class);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, comicTitles[position]);
        startActivity(iCompany);
    }
}

class IDWlwComicAdapter extends ArrayAdapter<String> {

    Context context;
    int[] images;
    int favImage;
    String[] titleArray;
    String[] descriptionArray;


    IDWlwComicAdapter(Context c, String[] titles, String[] descriptions, int imgs[], int favImg) {

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
                Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });

        myFavON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFavOFF.setVisibility(View.VISIBLE);
                myFavON.setVisibility(View.GONE);
                myDescriptionOFF.setVisibility(View.VISIBLE);
                myDescriptionON.setVisibility(View.GONE);
                Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT).show();
            }
        });
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Error 137: Could not...", Toast.LENGTH_SHORT).show();
            }
        });

        myDescriptionOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Error 137: Could not...", Toast.LENGTH_SHORT).show();
            }
        });

        myDescriptionON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Error 137: Could not...", Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }
}
