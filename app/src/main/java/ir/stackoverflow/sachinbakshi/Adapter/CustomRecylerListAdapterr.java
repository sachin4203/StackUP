package ir.stackoverflow.sachinbakshi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.stackoverflow.sachinbakshi.ListItemModel;
import ir.stackoverflow.sachinbakshi.R;

/**
 * Created by SACHIN on 17-04-2016.
 */
public class CustomRecylerListAdapterr extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ListItemModel> listItem;
    public CustomRecylerListAdapterr(Activity activity, List<ListItemModel> listItem) {
        this.activity = activity;
        this.listItem = listItem;
    }@Override
     public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int location) {
        return listItem.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        }else{
            convertView = inflater.inflate(R.layout.list_row2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // getting movie data for the row
        ListItemModel m = listItem.get(position);

        // thumbnail image
        if(holder.thumbNail.getTag()==null || !holder.thumbNail.getTag().equals(m.getUserProfileImage())) {
            Picasso.with(activity).load(m.getUserProfileImage()).into(holder.thumbNail);
            holder.thumbNail.setTag(m.getUserProfileImage());
        }
        // title
        holder.title.setText(m.getQuestionTitle());

        // score
        holder.score.setText(String.valueOf(m.getScore()));

        holder.username.setText(" By "+ String.valueOf(m.getUserDisplayName()));

        // tags
       /* String genreStr = "";
        for (String str : m.getTags()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;*/
        holder.tags.setText(m.getTags());

        // release year
        holder.lastactivity.setText(String.valueOf(m.getLastActivity()) + " Minutes Ago");

        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.question_title)
        TextView title;
        @Bind(R.id.score) TextView score;
        @Bind(R.id.tags) TextView tags;
        @Bind(R.id.last_activity) TextView lastactivity;
        @Bind(R.id.user_thumbnail)
        ImageView thumbNail;
        @Bind(R.id.username)
        TextView username;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

