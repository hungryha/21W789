package edu.mit.rerun.utils;

import java.util.ArrayList;
import java.util.Comparator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import edu.mit.rerun.R;
import edu.mit.rerun.model.Filter;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.view.EditFilterActivity;
import edu.mit.rerun.view.ItemDetailActivity;

public class FilterSettingsListAdapter extends ArrayAdapter<Filter> {
    private ArrayList<Filter> filters;
    private LayoutInflater inflator;
    private Context context;
    
    // Comparator to sort filters alphabetically
    public class FilterComparator implements Comparator<Filter> {

        public int compare(Filter item1, Filter item2) {
            return item1.getFiltername().compareTo(item2.getFiltername());
        }

    }
    public FilterSettingsListAdapter(Context context, ArrayList<Filter> filters) {
        super(context, 0, filters);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.filters = filters;
        inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Filter filter = filters.get(position);
        if (filter != null) {
            view = inflator.inflate(R.layout.list_entry_filter_item, null);
            
            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            
            TextView filterName = (TextView) view.findViewById(R.id.filter_name);
            Button edit = (Button) view.findViewById(R.id.filter_edit);
            CheckBox use = (CheckBox) view.findViewById(R.id.filter_use);
            
            filterName.setText(filter.getFiltername());
            edit.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
                    intent.putExtra("filterName", filter.getFiltername());
                    context.startActivity(intent);
                }
            });

        }
        return view;
    }
}
