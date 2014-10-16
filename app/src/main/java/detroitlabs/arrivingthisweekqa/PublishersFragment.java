package detroitlabs.arrivingthisweekqa;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class PublishersFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    String [] publisherTitles;
    int[] images = {R.drawable.boom_logo_pic, R.drawable.dark_horse_logo_pic, R.drawable.dc_logo_pic, R.drawable.idw_logo_pic, R.drawable.image_logo_pic, R.drawable.marvel_logo_pic};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publishers, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = (ListView) getView().findViewById(android.R.id.list);

        Resources res = getResources();
        publisherTitles = res.getStringArray(R.array.titles);

        ListAdapter adapter = new ListAdapter(getActivity(), publisherTitles, images );
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCompany = new Intent(getActivity(), ComicReleaseListActivity.class);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, publisherTitles[position]);
        startActivity(iCompany);
    }
}

class ListAdapter extends ArrayAdapter<String> {

    Context context;
    int[] images;
    String[] titleArray;

    ListAdapter(Context c, String[] titles, int imgs[]) {

        super(c, R.layout.single_row, R.id.textView, titles);
        this.context = c;
        this.images = imgs;
        this.titleArray = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, parent, false);
        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.textView);

        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        return row;
    }
}
