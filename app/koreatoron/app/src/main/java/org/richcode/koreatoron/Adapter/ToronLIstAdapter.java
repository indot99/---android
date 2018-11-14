package org.richcode.koreatoron.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.richcode.koreatoron.ModelClass.ToronModel;
import org.richcode.koreatoron.R;

import java.util.ArrayList;

public class ToronLIstAdapter extends BaseAdapter {

    ArrayList<ToronModel> ToronArrayList = new ArrayList<ToronModel>();

    public void add(ToronModel input){
        ToronArrayList.add(input);
    }

    @Override
    public int getCount() {
        return ToronArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return ToronArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_toron,parent,false);
        }

        TextView TrueText = (TextView)convertView.findViewById(R.id.item_true);
        TextView FalseText = (TextView)convertView.findViewById(R.id.item_false);
        TextView TitleText = (TextView)convertView.findViewById(R.id.item_title);
        TextView DateText = (TextView)convertView.findViewById(R.id.item_date);
        TextView ContentText = (TextView)convertView.findViewById(R.id.item_content);
        TextView CommentCount = (TextView)convertView.findViewById(R.id.item_comment_count);

        ToronModel item = ToronArrayList.get(pos);

        TrueText.setText(item.getString_true());
        FalseText.setText(item.getString_false());
        TitleText.setText(item.getTitle());
        DateText.setText(item.getDate());
        ContentText.setText(item.getContent());
        CommentCount.setText(item.getComment_count());

        return convertView;
    }

    public String getHref(int pos){
        return ToronArrayList.get(pos).getHref();
    }



}
