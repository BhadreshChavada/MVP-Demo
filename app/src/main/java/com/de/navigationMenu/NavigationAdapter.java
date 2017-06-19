package com.de.navigationMenu;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.de.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Bhadresh on 12/5/17.
 */

public class NavigationAdapter extends BaseExpandableListAdapter {

    LinkedHashMap<String, ArrayList<String>> NavigationItem;
    Context context;
    FragmentManager fm;

    ArrayList<String> HeaderItem;

    public NavigationAdapter(Context context, LinkedHashMap<String, ArrayList<String>> NavigationItem, FragmentManager fm) {
        this.NavigationItem = NavigationItem;
        this.context = context;
        this.fm = fm;

        HeaderItem = new ArrayList<>(NavigationItem.keySet());
    }

    @Override
    public int getGroupCount() {
        return HeaderItem.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

//        Toast.makeText(context, "" + HeaderItem.get(groupPosition), Toast.LENGTH_SHORT).show();
        return NavigationItem.get(HeaderItem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return HeaderItem.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.layout_navigation_item_header, null);
        TextView tv_navigation_header = (TextView) convertView.findViewById(R.id.tv_navigation_header);
        tv_navigation_header.setText(HeaderItem.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.layout_navigation_item_child, null);
        TextView tv_navigation_child = (TextView) convertView.findViewById(R.id.tv_navigation_child);
        tv_navigation_child.setText(" - " + NavigationItem.get(HeaderItem.get(groupPosition)).get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
