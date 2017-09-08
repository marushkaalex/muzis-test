package ru.muzis.muzistest.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.muzis.muzistest.R;
import ru.muzis.muzistest.model.ArtistModel;
import ru.muzis.muzistest.model.BaseModel;
import ru.muzis.muzistest.model.TrackModel;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.InvalidatableViewHolder> {
    private static final int TYPE_ARTIST = 0;
    private static final int TYPE_TRACK = 1;
    private static final int TYPE_BOTTOM_SHADOW = 2;

    private Context mContext;
    private List<Item> mItems;
    private LayoutInflater mInflater;

    public ChartAdapter(Context context, List<ArtistModel> artistList) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mItems = prepareItems(artistList);
    }

    private List<Item> prepareItems(List<ArtistModel> artistList) {
        List<Item> items = new ArrayList<>();
        for (ArtistModel model : artistList) {
            items.add(new Item(model, TYPE_ARTIST));
            if (model.getTracks() != null) {
                for (TrackModel trackModel : model.getTracks()) {
                    items.add(new Item(trackModel, TYPE_TRACK));
                }
            }
            items.add(new Item(null, TYPE_BOTTOM_SHADOW));
        }
        return items;
    }

    @Override
    public ChartAdapter.InvalidatableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ARTIST:
                return new ArtistViewHolder(mInflater.inflate(R.layout.item_artist, parent, false));
            case TYPE_TRACK:
                return new TrackViewHolder(mInflater.inflate(R.layout.item_track, parent, false));
            case TYPE_BOTTOM_SHADOW:
                return new ShadowViewHolder(
                        mInflater.inflate(R.layout.item_shadow_bottom, parent, false));
        }
        throw new IllegalArgumentException("Unknown viewType");
    }

    @Override
    public void onBindViewHolder(ChartAdapter.InvalidatableViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_BOTTOM_SHADOW) {
            holder.invalidate(mItems.get(position).model);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).type;
    }

    public static abstract class InvalidatableViewHolder<T> extends RecyclerView.ViewHolder {
        public InvalidatableViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void invalidate(T model);
    }

    public static class ArtistViewHolder extends InvalidatableViewHolder<ArtistModel> {
        @BindView(R.id.item_artist_title)
        public TextView title;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void invalidate(ArtistModel model) {
            title.setText(model.getName());
        }
    }

    public static class TrackViewHolder extends InvalidatableViewHolder<TrackModel> {
        @BindView(R.id.item_track_title)
        public TextView title;

        public TrackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void invalidate(TrackModel model) {
            title.setText(model.getName());
        }
    }

    public static class ShadowViewHolder extends InvalidatableViewHolder<Void> {
        public ShadowViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void invalidate(Void model) {
        }
    }

    private static class Item {
        public int type;
        public BaseModel model;

        public Item(BaseModel model, int type) {
            this.model = model;
            this.type = type;
        }
    }
}
