package com.app.riddle.scenes.feed;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.riddle.R;
import com.app.riddle.datasources.AnonimousPostDataSource;
import com.app.riddle.helpers.Callback;
import com.app.riddle.models.AnonimousPostModel;
import com.app.riddle.scenes.feed.interfaces.IFeedActivity;
import com.app.riddle.scenes.feed.interfaces.IFeedActivityPresenter;

import java.util.ArrayList;

public class FeedActivityPresenter extends BaseAdapter implements IFeedActivityPresenter {

    private final FeedActivity view;
    ArrayList<AnonimousPostModel> postList = new ArrayList<AnonimousPostModel>();

    public FeedActivityPresenter(FeedActivity view){
        this.view = view;
    }

    @Override
    public void fetchFeed() {
        this.view.showSpinner();


        AnonimousPostDataSource.shared.fetch(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {

                FeedActivityPresenter.this.postList = (ArrayList<AnonimousPostModel>) responseObject;
                FeedActivityPresenter.this.view.presentContent();
                FeedActivityPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError(String error) {
                FeedActivityPresenter.this.view.presentError(error);
                FeedActivityPresenter.this.view.hideSpinner();
            }
        });
    }

    @Override
    public int getCount() {
        return this.postList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) FeedActivityPresenter.this.view.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            view = inflater.inflate(R.layout.post_item_layout, viewGroup, false);
        }

        final AnonimousPostModel model = (AnonimousPostModel) getItem(i);

        //Setting the texts
        ((TextView) view.findViewById(R.id.titleItem)).setText(model.title);
        ((TextView) view.findViewById(R.id.descItem)).setText(model.body);
        ((LinearLayout) view.findViewById(R.id.backgoundOfaPost)).setBackgroundColor(Color.parseColor(model.color));

        return view;

    }
}
