package com.example.apple.interninternational.Adapters;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.R;

import java.security.Permission;

public class SkillsListAdapter extends BaseExpandableListAdapter {

    // Static variables that can be accessed by any class
    public static String phoneNumber;

    // Properties of this adapter
    private Context context;

    public SkillsListAdapter(Context context) {
        this.context = context;
    }

    /**
     * Returns the number of groups in the list
     * @return
     */
    @Override
    public int getGroupCount() {
        return 10;
    }

    /**
     * Returns the number of children in the current group
     * @param groupPosition: current group
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * Returns the object used for the group at current position
     * @param groupPosition: current position group
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return "Skill Name";
    }

    /**
     * Returns the object used for the child at the current group and current child position
     * @param groupPosition: current group position
     * @param childPosition: current child position
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "SPOC";
    }

    /**
     * Returns the group's id at current position
     * @param groupPosition: current position group
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Returns the child's id at current position and current group
     * @param groupPosition: current group position
     * @param childPosition: current child position
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (groupPosition+childPosition)*2+2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Returns the view for the current group position
     * @param groupPosition: current group position
     * @param isExpanded: is the current group expanded
     * @param convertView: the view that will be used for the current group
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater();
        convertView = inflater.inflate(R.layout.skills_dev_list_group_cell,parent,false);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpandableListView expandableListView = (ExpandableListView) parent;
                if (expandableListView.isGroupExpanded(groupPosition)){
                    expandableListView.collapseGroup(groupPosition);
                }
                else {
                    expandableListView.expandGroup(groupPosition);
                }
            }
        });
        TextView dummyView = new TextView(context);
        dummyView.setText("Group Name");
//        return dummyView;
        return convertView;
    }

    /**
     * Returns the view for the current group position and child position
     * @param groupPosition: current group position
     * @param childPosition: current child position
     * @param isLastChild: is this the last child of the group
     * @param convertView: view that will be used for the current child
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater();
        convertView = inflater.inflate(R.layout.skills_dev_list_child_cell,parent,false);
        // Applying underline style to a textview
        String str = "Show Address";
        SpannableString span = new SpannableString(str);
        span.setSpan(new UnderlineSpan(),0,str.length(),0);
        ((TextView) convertView.findViewById(R.id.skills_dev_list_child_cell_tv_address_link)).setText(span);
        String str1 = "9629776226";
        SpannableString span1 = new SpannableString(str1);
        span1.setSpan(new UnderlineSpan(),0,str1.length(),0);
        ((TextView) convertView.findViewById(R.id.skills_dev_list_child_cell_tv_number)).setText(span1);
        final TextView spocContact = (TextView) convertView.findViewById(R.id.skills_dev_list_child_cell_tv_number);
        // Setting listener to the child view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate alert box with some layout xml file
                View addressView = inflater.inflate(R.layout.ngo_list_address_popover,null);
                AlertDialog.Builder addressDialog = new AlertDialog.Builder(context);
                addressDialog.setView(addressView);
                addressDialog.create().show();
            }
        });
        /**
         * This is called only when the textview is clicked on the child view
         * This will trigger the phone app from the which the user can directly call the number
         */
        spocContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Create an intent to call the phone app and make a call
                 */
                // Format the number in indian style
                String contactString = spocContact.getText().toString();
                phoneNumber = PhoneNumberUtils.formatNumber(contactString,"IN");
                System.out.println("Real Contact: "+phoneNumber);
                HomeScreen.makeACall(phoneNumber);
            }
        });
        TextView dummyView = new TextView(context);
        dummyView.setText("Child Name");
//        return dummyView;
        return convertView;
    }

    /**
     * Tells if this child is selectable or not
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
