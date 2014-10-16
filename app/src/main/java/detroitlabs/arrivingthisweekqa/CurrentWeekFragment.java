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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class CurrentWeekFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    String [] boomTitles;
    String [] boomDescriptions;
    int[] images = {R.drawable.boomawog, R.drawable.boomee, R.drawable.boomhr, R.drawable.boomjhst, R.drawable.boomlj, R.drawable.boompeanuts, R.drawable.boomsh, R.drawable.boomsoa, R.drawable.boomsu, R.drawable.boomtlb, R.drawable.boomug, R.drawable.boomwe};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publishers, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = (ListView) getView().findViewById(android.R.id.list);

        Resources res = getResources();
        boomTitles = res.getStringArray(R.array.boomComicTitles);
        boomDescriptions = res.getStringArray(R.array.boomDescriptions);

        BoomComicAdapter adapter = new BoomComicAdapter(getActivity(), boomTitles, boomDescriptions, images );
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCompany = new Intent(getActivity(), ComicReleaseListActivity.class);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, boomTitles[position]);
        startActivity(iCompany);
    }
}

class BoomComicAdapter extends ArrayAdapter<String> {

    Context context;
    int[] images;
    String[] titleArray;
    String[] descriptionArray;

    BoomComicAdapter(Context c, String[] titles, String[] descriptions, int imgs[]) {

        super(c, R.layout.single_row_release_list, R.id.textView, titles);
        this.context = c;
        this.images = imgs;
        this.titleArray = titles;
        this.descriptionArray = descriptions;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_release_list, parent, false);
        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.textView);
        TextView myDescription = (TextView) row.findViewById(R.id.textView2);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        myDescription.setText(descriptionArray[position]);
        return row;
    }
}
